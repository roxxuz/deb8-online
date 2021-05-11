package com.example.deb8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class Deb8Application {

    public static void main(String[] args) {
        SpringApplication.run(Deb8Application.class, args);
    }

}
