package com.revature.taskmaster;

import com.revature.taskmaster.auth.AuthController;
import com.revature.taskmaster.config.AppConfig;
import com.revature.taskmaster.users.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TaskmasterApp {

    private static Logger logger = LogManager.getLogger(TaskmasterApp.class);

    public static void main(String[] args) throws LifecycleException {

        logger.info("Starting Taskmaster application");

        try (AnnotationConfigApplicationContext beanContainer = new AnnotationConfigApplicationContext(AppConfig.class)) {

            String docBase = System.getProperty("java.io.tmpdir");
            Tomcat webServer = new Tomcat();

            // Web server base configurations
            webServer.setBaseDir(docBase);
            webServer.setPort(5000); // defaults to 8080, but we can set it to whatever port we want (as long as its open)
            webServer.getConnector(); // formality, required in order for the server to receive requests

            UserServlet userServlet = beanContainer.getBean(UserServlet.class);
            AuthController authController = beanContainer.getBean(AuthController.class);

            // Web server context and servlet configurations
            final String rootContext = "/taskmaster";
            webServer.addContext(rootContext, docBase);
            webServer.addServlet(rootContext, "UserServlet", userServlet).addMapping("/users");
            webServer.addServlet(rootContext, "AuthServlet", authController).addMapping("/auth");

            // Starting and awaiting web requests
            webServer.start();
            logger.info("Taskmaster web application successfully started");
            webServer.getServer().await();

        }

    }

}
