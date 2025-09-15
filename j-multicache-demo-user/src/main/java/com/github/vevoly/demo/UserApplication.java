package com.github.vevoly.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.github.vevoly.demo.mapper")
@SpringBootApplication(scanBasePackages = "com.github.vevoly.demo")
public class UserApplication {

    public static void main(String[] args) {
        var ioc = SpringApplication.run(UserApplication.class, args);
        var beanNames = ioc.getBeanDefinitionNames();
//        for (var beanName : beanNames) {
//            System.out.println(beanName);
//        }
        System.out.println("Starting a User application ...");
    }
}
