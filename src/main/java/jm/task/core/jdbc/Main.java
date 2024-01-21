package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        //Util util = new Util();
        //Connection connection = util.getConnection();
        //  User user1 = new User("Pashok", "Matros", (byte) 34);
        //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.setConnection(connection);
        //UserServiceImpl userService = new UserServiceImpl();
        //userService.setUserDao(userDaoJDBC);
        //userService.createUsersTable();
        // userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        //System.out.println(userService.getAllUsers());
        // userService.removeUserById(1);

//userService.cleanUsersTable();


        User user1 = new User("John", "Laughington", (byte) 30);
        User user2 = new User("Alice", "Jokerson", (byte) 25);
        User user3 = new User("Michael", "Gigglesworth", (byte) 35);
        User user4 = new User("Olivia", "Smirkley", (byte) 28);
        User user5 = new User("Daniel", "Chucklerson", (byte) 32);

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }


}
