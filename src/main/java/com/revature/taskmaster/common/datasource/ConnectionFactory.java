package com.revature.taskmaster.common.datasource;

import com.revature.taskmaster.common.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Implements the Factory and Singleton design patterns
public class ConnectionFactory {

    private static Logger logger = LogManager.getLogger(ConnectionFactory.class);

    private static ConnectionFactory connFactory;

    @Value("${db-url}")
    private String dbUrl;

    @Value("${db-username}")
    private String dbUsername;

    @Value("${db-password}")
    private String dbPassword;

    private ConnectionFactory() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            String message = "Failed to load PostgreSQL JDBC driver.";
            logger.fatal(message);
            throw new DataSourceException(message, e);
        }
    }

    public static ConnectionFactory getInstance() {
        if (connFactory == null) {
            connFactory = new ConnectionFactory();
        }
        return connFactory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

}
