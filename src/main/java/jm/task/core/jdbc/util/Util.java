package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {


    public final static String TABLE_NAME = "users2";
    private final static String DB_HOST = "localhost";
    private final static String DB_PORT = "3306";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "root12345";
    private final static String DB_NAME = "my_shema";

   public static Connection dbconnection;

    public static Connection getConnection() {
        try {
            String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbconnection = DriverManager.getConnection(connectionString, DB_USER, DB_PASS);
            if (dbconnection != null && !dbconnection.isClosed()) {
                System.out.println("Успешное подключение к базе данных.");
            } else {
                System.out.println("Не удалось подключиться к базе данных.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер базы данных не найден.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных.");
            e.printStackTrace();
        }

        return dbconnection;
    }


}

