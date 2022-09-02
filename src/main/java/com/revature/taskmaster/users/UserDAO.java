package com.revature.taskmaster.users;

import com.revature.taskmaster.common.datasource.ConnectionFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// DAO = Data Access Object
public class UserDAO {

    public List<User> getAllUsers() {

        String sql = "SELECT au.id, au.given_name, au.surname, au.email, au.username, au.role_id, ur.role " +
                     "FROM app_users au " +
                     "JOIN user_roles ur " +
                     "ON au.role_id = ur.id";

        List<User> allUsers = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are vulnerable to SQL injection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword("***********"); // done for security purposes
                user.setRole(new Role(rs.getString("role_id"), rs.getString("role")));
                allUsers.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database");
            e.printStackTrace();
        }

        return allUsers;

    }

    public String save(User user) {

        String sql = "INSERT INTO app_users (given_name, surname, email, username, password, role_id) " +
                     "VALUES (?, ?, ?, ?, ?, '5a2e0415-ee08-440f-ab8a-778b37ff6874')";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, user.getGivenName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            user.setId(rs.getString("id"));

        } catch (SQLException e) {
           log("ERROR", e.getMessage());
        }

        log("INFO", "Successfully persisted new used with id: " + user.getId());

        return user.getId();

    }

    public void log(String level, String message) {
        try {
            File logFile = new File("logs/app.log");
            logFile.createNewFile();
            BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile));
            logWriter.write(String.format("[%s] at %s logged: [%s] %s\n", Thread.currentThread().getName(), LocalDate.now(), level.toUpperCase(), message));
            logWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
