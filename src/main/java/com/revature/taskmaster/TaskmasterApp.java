package com.revature.taskmaster;

import com.revature.taskmaster.common.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskmasterApp {

    public static void main(String[] args) {
        ConnectionFactory connFactory = ConnectionFactory.getInstance();

        // try-with-resources (introduced in Java 7); only works with things that implement AutoCloseable
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
