package com.revature.taskmaster;

import com.revature.taskmaster.auth.AuthController;
import com.revature.taskmaster.config.AppConfig;
import com.revature.taskmaster.users.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TaskmasterApp {

    private static Logger logger = LogManager.getLogger(TaskmasterApp.class);

    public static void main(String[] args) throws LifecycleException {

        logger.info("Starting Taskmaster application");


        String docBase = System.getProperty("java.io.tmpdir");
        Tomcat webServer = new Tomcat();

        // Web server base configurations
        webServer.setBaseDir(docBase);
        webServer.setPort(5000); // defaults to 8080, but we can set it to whatever port we want (as long as its open)
        webServer.getConnector(); // formality, required in order for the server to receive requests
        final String rootContext = "/taskmaster";
        webServer.addContext(rootContext, docBase);

        AnnotationConfigWebApplicationContext beanContainer = new AnnotationConfigWebApplicationContext();
        beanContainer.register(AppConfig.class);

        //UserServlet userServlet = beanContainer.getBean(UserServlet.class);

        // Web server context and servlet configurations

        //webServer.addServlet("/v1", "UserServlet", userServlet).addMapping("/users");
        webServer.addServlet(rootContext, "DispatcherServlet", new DispatcherServlet(beanContainer)).addMapping("/");

        // Starting and awaiting web requests
        webServer.start();
        logger.info("Taskmaster web application successfully started");
        webServer.getServer().await();

    }

}
