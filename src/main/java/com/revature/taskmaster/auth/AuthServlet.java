package com.revature.taskmaster.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.taskmaster.users.User;
import com.revature.taskmaster.users.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final UserDAO userDAO;

    public AuthServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");
        Credentials credentials = jsonMapper.readValue(req.getInputStream(), Credentials.class);
        User loggedInUser = userDAO.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                                   .orElseThrow(() -> new RuntimeException("No user found with those credentials"));

        resp.getWriter().write(jsonMapper.writeValueAsString(loggedInUser));
    }
}
