package com.revature.taskmaster.common.util;

import com.revature.taskmaster.users.User;
import com.revature.taskmaster.users.UserDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppContext {

    private static boolean appRunning;

    public AppContext() {
        appRunning = true;
    }

    public void startApp() {
        while (appRunning) {
            try {
                UserDAO userDAO = new UserDAO();
                User loggedInUsed = userDAO.findUserByUsernameAndPassword("aanderson", "p4$$W0RD")
                                           .orElseThrow(() -> new RuntimeException("No user found with the provided credentials"));

                System.out.println("Successfully logged in user: " + loggedInUsed);
                appRunning = false;
            } catch (Exception e) {
                e.printStackTrace();
                appRunning = false;
            }
        }
    }

    public static void shutdown() {
        appRunning = false;
    }


}
