package com.github.vevoly.demo.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.github.vevoly.demo.entity.UserAvatar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, J-Multicache World - User Service ! ";
    }

    @GetMapping("/hello2")
    public List<String> sayHello2() {
        return List.of("Hello", "J-Multicache", "World", " User Service !");
    }
}