package com.revature.taskmaster;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // implies @Configuration @ComponentScan and @AutoConfiguration
public class TaskmasterApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskmasterApp.class, args);
    }

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }

}
