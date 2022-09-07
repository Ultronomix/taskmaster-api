package com.revature.taskmaster;

import com.revature.taskmaster.auth.AuthService;
import com.revature.taskmaster.auth.AuthServlet;
import com.revature.taskmaster.users.UserDAO;
import com.revature.taskmaster.users.UserService;
import com.revature.taskmaster.users.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TaskmasterApp {

    public static void main(String[] args) throws LifecycleException {
        String docBase = System.getProperty("java.io.tmpdir");
        Tomcat webServer = new Tomcat();

        // Web server base configurations
        webServer.setBaseDir(docBase);
        webServer.setPort(5000); // defaults to 8080, but we can set it to whatever port we want (as long as its open)
        webServer.getConnector(); // formality, required in order for the server to receive requests

        // App component instantiation
        UserDAO userDAO = new UserDAO();
        AuthService authService = new AuthService(userDAO);
        UserService userService = new UserService(userDAO);
        UserServlet userServlet = new UserServlet(userService);
        AuthServlet authServlet = new AuthServlet(authService);

        // Web server context and servlet configurations
        final String rootContext = "/taskmaster";
        webServer.addContext(rootContext, docBase);
        webServer.addServlet(rootContext, "UserServlet", userServlet).addMapping("/users");
        webServer.addServlet(rootContext, "AuthServlet", authServlet).addMapping("/auth");

        // Starting and awaiting web requests
        webServer.start();
        webServer.getServer().await();

    }

}
