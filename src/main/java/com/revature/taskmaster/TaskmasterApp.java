package com.revature.taskmaster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskmasterApp {

    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql://fake-db-url:5432/postgres?currentSchema=taskmaster";
        String dbUsername = "fake-user";
        String dbPassword = "fake-password";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (conn != null) {
                System.out.println("Connection successful!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find PostgreSQL JDBC driver. Connection attempt aborted.");
        } catch (SQLException e) {
            System.err.println("Could not establish a connection to the database.");
        }
    }
}
