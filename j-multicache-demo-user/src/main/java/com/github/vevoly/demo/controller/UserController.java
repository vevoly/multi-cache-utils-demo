package com.github.vevoly.demo.controller;

import com.github.vevoly.demo.entity.User;
import com.github.vevoly.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @RequestMapping("getUser")
    public User getUserById(@RequestParam(required = false) Long id) {
        return userService.getUserById(id);
    }

}
