package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class UserDaoJDBCImpl extends Util implements UserDao {
    private final String TABLE_NAME = "users2";
    //    private Connection dbconnection;
//
//
//    public UserDaoJDBCImpl() {
//
//    }
//
//    // Сеттер для установки соединения
//    public void setConnection(Connection connection) {
//        this.dbconnection = connection;
//    }
    Connection dbconnection;

    {
        try {
            dbconnection = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void createUsersTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT NOT NULL" +
                    ")";

            try (PreparedStatement preparedStatement = dbconnection.prepareStatement(createTableSQL)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица " + TABLE_NAME + " успешно создана (или уже существует).");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + TABLE_NAME);
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        //DROP TABLE your_table_name;
        String drop= "DROP TABLE "+TABLE_NAME;

        Statement statement = null;
        try {
            statement = dbconnection.createStatement();
            int rowsAffected = statement.executeUpdate(drop);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void saveUser(String name, String lastName, byte age)  {

        String add = "INSERT INTO " + TABLE_NAME + "(name, lastname, age) VALUES (?, ?, ?)";

        PreparedStatement prSt = null;
        try {
            prSt = dbconnection.prepareStatement(add);
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
        //DELETE FROM your_table_name WHERE id = your_user_id;
        String delete = "DELETE FROM " + TABLE_NAME + " WHERE ID = " + id;


        Statement statement;
        try {
            statement = dbconnection.createStatement();
            int rowsAffected = statement.executeUpdate(delete);

        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя по id");
            e.printStackTrace();
        }


    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            String selectAllSQL = "SELECT * FROM " + TABLE_NAME;

            try (PreparedStatement preparedStatement = dbconnection.prepareStatement(selectAllSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        String lastName = resultSet.getString("lastName");
                        byte age = resultSet.getByte("age");

                        // Создаем объект User и добавляем его в список
                        User user = new User();
                        user.setId(id);
                        user.setName(name);
                        user.setLastName(lastName);
                        user.setAge(age);
                        userList.add(user);
                        //userList.add(new User(id, name, lastName, age));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех пользователей");
            e.printStackTrace();
        }

        return userList;


    }

    public void cleanUsersTable() {
String clean = "DELETE FROM " + TABLE_NAME;

        Statement statement = null;
        try {
            statement = dbconnection.createStatement();
            int rowsAffected = statement.executeUpdate(clean);
            System.out.println("Значения удалены");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

