package com.github.vevoly.demo.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.github.vevoly.demo.entity.UserAvatar;
import com.github.vevoly.demo.service.UserAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 站点可选头像列表 前端控制器
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@RestController()
@RequestMapping("/api/user/avatar")
public class UserAvatarController {

    @Autowired
    private UserAvatarService userAvatarService;

    @GetMapping("/list")
    public List<UserAvatar> avatarList() {
        List<UserAvatar> list = userAvatarService.list();
        return list;
    }
}
