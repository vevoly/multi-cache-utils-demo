package com.github.vevoly.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vevoly.demo.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
public interface UserService extends IService<User> {

    /**
     * 根据 id 获取 用户详情
     * @param id
     * @return
     */
    User getUserById(Long id);

    List<User> getUserListByIds(List<Long> ids);
}
