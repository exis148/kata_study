package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.*;


public class Util {


    public static final String TABLE_NAME = "users2";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root12345";
    private static final String DB_NAME = "my_shema";


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

    public static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC")
                    .setProperty("hibernate.connection.username", DB_USER)
                    .setProperty("hibernate.connection.password", DB_PASS)
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .addAnnotatedClass(User.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

//    public static void factoryClose() {
//        sessionFactory.close();
//    }
//
}

