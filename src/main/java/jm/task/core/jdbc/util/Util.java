package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {


    public final String TABLE_NAME = "users2";
    public final String id = "id";
    public final String name = "name";
    public final String lastName = "lastName";
    public final String age = "age";
    //private final String email = "email";

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
    //    Statement statement = dbconnection.createStatement();
 //   statement.execute("insert into users (name, age, email) values('Molly', 32, 'polly@gmail.com' )");

        return dbconnection;
    }


//    public void userWrite(String name,  String lastName , byte age) throws SQLException{
////        String add = "INSERT INTO "+ TABLE_NAME +"("+name+","+age+","+email+")"+
////                "VALUES(?,?,?)";
//        String add = "INSERT INTO " + TABLE_NAME + "(name, lastName, age) VALUES (?, ?, ?)";
//
//        PreparedStatement prSt = dbconnection.prepareStatement(add);
//
//        prSt.setString(1, name);
//        prSt.setString(2, lastName);
//        prSt.setByte(3, age);
//        prSt.executeUpdate();
//
//
//    }

//        String connectionString = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbname;
//
//        try (Connection dbconnection = DriverManager.getConnection(connectionString, dbuser, dbpass)) {
//
//
//
//        }
//
//        {
//
//                if (!dbconnection.isClosed()) {
//                System.out.println("Успешное подключение к базе данных.");
//            } else {
//                System.out.println("Не удалось подключиться к базе данных.");
//            }
//
//
//            return dbconnection;
//        }



//    String connectionString = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbname;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//    dbconnection = DriverManager.getConnection(connectionString, dbuser, dbpass);
//    // Проверка успешности подключения
//        if (dbconnection != null && !dbconnection.isClosed()) {
//        System.out.println("Успешное подключение к базе данных.");
//    } else {
//        System.out.println("Не удалось подключиться к базе данных.");
//    }
//
//        return dbconnection;





}

