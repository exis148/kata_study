package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {


    public final String TABLE_NAME = "users2";
    public final String id = "id";
    public final String name = "name";
    public final String lastName = "lastName";
    public final String age = "age";

    private final String dbhost = "localhost";
    private final String dbport = "3306";
    private final String dbuser = "root";
    private final String dbpass = "root12345";
    private final String dbname = "my_shema";

    Connection dbconnection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbname;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString, dbuser, dbpass);
        if (dbconnection != null && !dbconnection.isClosed()) {
            System.out.println("Успешное подключение к базе данных.");
        } else {
            System.out.println("Не удалось подключиться к базе данных.");
        }


        return dbconnection;
    }

}

