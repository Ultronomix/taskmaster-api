package com.revature.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // implies @Configuration @ComponentScan and @AutoConfiguration
public class TaskmasterApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskmasterApp.class, args);
    }

}
