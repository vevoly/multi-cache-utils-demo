package com.github.vevoly.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 这是一个与缓存配置文件的映射枚举类，便于操作
 * namespace 需要与 multicache.yml 中的 namespace 配置保持一致
 */
@Getter
@AllArgsConstructor
public enum CacheName {

    USER_DETAILS_BY_ID("user:user_details"),
    USER_LEVELS_BY_TENANT("user:user_levels_list"),
    USER_PREFERENCES("user:user_preferences_hash"),

    AVATARS("avatars_list"),

    RESULT_USER_PAGE("result:user:user_page"),
    RESULT_TENANT_PAGE("result:tenant:tenant_page"),

    BANNER_USER_TYPE_USER("banner:usertype_set:userId"),
    BANNER_USER_TYPE_LEVEL("banner:usertype_set:levelId"),
    BANNER_USER_TYPE("banner:banner_visible_ids_union"),

    ;

    private final String namespace;
}