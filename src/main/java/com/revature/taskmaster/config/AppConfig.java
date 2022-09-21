package com.revature.taskmaster.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.revature.taskmaster")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }

}
