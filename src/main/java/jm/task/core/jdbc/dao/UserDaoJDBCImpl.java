package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection conn = Util.getConnection();

    public void createUsersTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + Util.TABLE_NAME + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT NOT NULL" +
                    ")";

            try (PreparedStatement preparedStatement = conn.prepareStatement(createTableSQL)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица " + Util.TABLE_NAME + " успешно создана (или уже существует).");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + Util.TABLE_NAME);
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;

        try (Statement statement = conn.createStatement()) {
            int rowsAffected = statement.executeUpdate(drop);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String add = "INSERT INTO " + Util.TABLE_NAME + "(name, lastname, age) VALUES (?, ?, ?)";

        try (PreparedStatement prSt = conn.prepareStatement(add)) {
            prSt.setString(1, name);
            prSt.setString(2, lastName);
            prSt.setByte(3, age);
            prSt.executeUpdate();
            System.out.println("Юзер с именем " + name + " успешно добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM " + Util.TABLE_NAME + " WHERE ID = " + id;

        try (Statement statement = conn.createStatement()) {
            int rowsAffected = statement.executeUpdate(delete);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя по id");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            String selectAllSQL = "SELECT * FROM " + Util.TABLE_NAME;

            try (PreparedStatement preparedStatement = conn.prepareStatement(selectAllSQL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");

                    User user = new User();
                    user.setId(id);
                    user.setName(name);
                    user.setLastName(lastName);
                    user.setAge(age);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех пользователей");
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM " + Util.TABLE_NAME;

        try (Statement statement = conn.createStatement()) {
            int rowsAffected = statement.executeUpdate(clean);
            System.out.println("Значения удалены");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
