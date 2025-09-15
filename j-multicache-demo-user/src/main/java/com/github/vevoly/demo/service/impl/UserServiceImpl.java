package com.github.vevoly.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.vevoly.MultiCacheUtils;
import com.github.vevoly.config.MultiCacheProperties;
import com.github.vevoly.core.CacheUtils;
import com.github.vevoly.demo.entity.User;
import com.github.vevoly.demo.enums.CacheName;
import com.github.vevoly.demo.mapper.UserMapper;
import com.github.vevoly.demo.service.UserService;
import com.github.vevoly.plug.CachePreloadable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, CachePreloadable {

    private final MultiCacheUtils multiCacheUtils;

    private final static String NAMESPACE = CacheName.USER_DETAILS_BY_ID.getNamespace();

    /**
     * 根据 id 获取 用户详情
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return multiCacheUtils.getSingleData(
                CacheUtils.getCacheKey(NAMESPACE, id),
                new TypeReference<>() {},
                () -> this.getById(id)
        );
    }

    @Override
    public List<User> getUserListByIds(List<Long> ids) {
        return null;
    }

    /**
     * 缓存预热接口实现
     * @return
     */
    @Override
    public int preLoadCache() {

        // 1. 从DB查询全量数据
        List<User> allEntities = this.list();
        if (CollectionUtils.isEmpty(allEntities)) {
            return 0;
        }

        // 2. 将 List<Entity> 转换为 Map<Key, Entity>
        Map<String, User> dataToPreload = allEntities.stream()
                .collect(Collectors.toMap(entity -> CacheUtils.getCacheKey(NAMESPACE, entity.getId()),
                        Function.identity(), (existing, replacement) -> replacement));
        return multiCacheUtils.preLoadCacheFromMap(dataToPreload);
    }
}
