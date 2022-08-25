package com.revature.taskmaster;

import com.revature.taskmaster.common.datasource.ConnectionFactory;
import com.revature.taskmaster.users.User;
import com.revature.taskmaster.users.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaskmasterApp {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        User newUser = new User();
        newUser.setGivenName("Regina");
        newUser.setSurname("Marsh");
        newUser.setEmail("regina789@revature.net");
        newUser.setUsername("regina");
        newUser.setPassword("p4$$W0RD");

        String newUserId = userDAO.save(newUser);
        System.out.println(newUserId);

        System.out.println("+---------------------------------+");

        List<User> users = userDAO.getAllUsers();
        users.forEach(System.out::println);

    }
}
