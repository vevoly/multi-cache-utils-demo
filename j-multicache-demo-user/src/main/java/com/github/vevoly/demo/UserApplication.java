package com.github.vevoly.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UserApplication.class);
        application.run(args);
        System.out.println("Starting a User application ...");
    }
}
