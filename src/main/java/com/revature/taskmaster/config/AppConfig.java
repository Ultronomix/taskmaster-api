package com.revature.taskmaster.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.revature.taskmaster")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }

}
