-- =================================================================
-- 数据库和表结构创建
-- =================================================================

-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS `j_multicache_demo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `j_multicache_demo`;

-- 2. 租户表 (tenant)
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
    `id` VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '租户ID (e.g., tenant_a)',
    `name` VARCHAR(255) NOT NULL COMMENT '租户名称',
    `status` INT NOT NULL DEFAULT 1 COMMENT '状态: 1=正常, 0=禁用',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '租户信息表';

-- 3. 用户等级表 (user_level)
DROP TABLE IF EXISTS `user_level`;
CREATE TABLE `user_level` (
    `id` BIGINT NOT NULL PRIMARY KEY COMMENT '等级ID',
    `tenant_id` VARCHAR(50) NOT NULL COMMENT '所属租户ID',
    `level_name` VARCHAR(100) NOT NULL COMMENT '等级名称 (e.g., VIP1, VIP2)',
    `level_icon` VARCHAR(512) COMMENT '等级图标URL'
) COMMENT '用户等级定义表';

-- 4. 用户表 (user)
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL PRIMARY KEY COMMENT '用户ID',
    `tenant_id` VARCHAR(50) NOT NULL COMMENT '所属租户ID',
    `username` VARCHAR(100) NOT NULL COMMENT '用户名',
    `user_level_id` BIGINT COMMENT '用户等级ID',
    `user_avatar_id` BIGINT COMMENT '用户头像ID',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY `uk_tenant_username` (`tenant_id`, `username`)
) COMMENT '用户信息表';

-- 5. 站点可选头像表 (user_avatar)
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `tenant_id` VARCHAR(50) NOT NULL COMMENT '所属租户ID',
    `avatar_url` VARCHAR(512) NOT NULL COMMENT '头像URL',
    `sort_order` INT DEFAULT 0 COMMENT '排序值',
    `is_default` BOOLEAN DEFAULT FALSE COMMENT '是否为默认头像',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '站点可选头像列表';
CREATE INDEX `idx_tenant_id_avatar` ON `user_avatar` (`tenant_id`);

-- 6. Banner基础信息表 (banner)
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `tenant_id` VARCHAR(50) NOT NULL,
    `title` VARCHAR(255) NOT NULL COMMENT 'Banner标题',
    `target_link_type` INT COMMENT '跳转类型: 1=帖子, 2=URL',
    `target_link` VARCHAR(512) COMMENT '跳转链接',
    `status` INT NOT NULL DEFAULT 1 COMMENT '状态: 1=启用, 0=禁用'
) COMMENT 'Banner基础信息表';

-- 7. Banner位置表 (banner_position)
DROP TABLE IF EXISTS `banner_position`;
CREATE TABLE `banner_position` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `banner_id` BIGINT NOT NULL,
    `position_code` VARCHAR(50) NOT NULL COMMENT '位置编码: homepage_top, lobby_right',
    `image_url` VARCHAR(512) NOT NULL COMMENT '图片URL'
) COMMENT 'Banner位置关联表';

-- 8. Banner用户类型关联表 (banner_user_type)
DROP TABLE IF EXISTS `banner_user_type`;
CREATE TABLE `banner_user_type` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `banner_id` BIGINT NOT NULL,
    `show_user_type` VARCHAR(50) NOT NULL COMMENT '类型: user, level',
    `user_type_id` BIGINT NOT NULL COMMENT '用户ID或用户等级ID'
) COMMENT 'Banner与用户类型的多对多关联表';

-- 9. 用户配置表 (user_preference)
DROP TABLE IF EXISTS `user_preference`;
CREATE TABLE `user_preference` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `pref_key` VARCHAR(100) NOT NULL COMMENT '配置项Key: e.g., theme, language',
    `pref_value` VARCHAR(255) NOT NULL COMMENT '配置项Value',
    UNIQUE KEY `uk_user_pref` (`user_id`, `pref_key`)
) COMMENT '用户偏好配置表';


-- =================================================================
-- 模拟数据插入
-- =================================================================

-- 1. 插入租户 (用于Page和String测试)
INSERT INTO `tenant` (`id`, `name`, `status`) VALUES
('tenant_a', '演示租户A', 1),
('tenant_b', '演示租户B', 1),
('tenant_c', '被禁用的租户C', 0);

-- 2. 插入用户等级 (用于List和Union测试)
-- 租户A的等级
INSERT INTO `user_level` (`id`, `tenant_id`, `level_name`) VALUES
(1, 'tenant_a', 'VIP1'),
(2, 'tenant_a', 'VIP2'),
(3, 'tenant_a', 'VIP3');
-- 租户B的等级
INSERT INTO `user_level` (`id`, `tenant_id`, `level_name`) VALUES
(11, 'tenant_b', '普通会员'),
(12, 'tenant_b', '黄金会员');

-- 3. 插入用户 (用于String, Union, Hash测试)
-- 租户A的用户
INSERT INTO `user` (`id`, `tenant_id`, `username`, `user_level_id`) VALUES
(101, 'tenant_a', 'alice', 2),   -- Alice是VIP2
(102, 'tenant_a', 'bob', 3),     -- Bob是VIP3
(103, 'tenant_a', 'charlie', 2); -- Charlie也是VIP2
-- 租户B的用户
INSERT INTO `user` (`id`, `tenant_id`, `username`, `user_level_id`) VALUES
(201, 'tenant_b', 'david', 12);  -- David是黄金会员

-- 4. 插入可选头像 (用于List测试)
-- 租户A的头像
INSERT INTO `user_avatar` (`tenant_id`, `avatar_url`, `sort_order`, `is_default`) VALUES
('tenant_a', 'https://example.com/avatars/tenant_a/1.png', 1, TRUE),
('tenant_a', 'https://example.com/avatars/tenant_a/2.png', 2, FALSE),
('tenant_a', 'https://example.com/avatars/tenant_a/3.png', 3, FALSE);
-- 租户B的头像
INSERT INTO `user_avatar` (`tenant_id`, `avatar_url`, `sort_order`, `is_default`) VALUES
('tenant_b', 'https://example.com/avatars/tenant_b/default.png', 1, TRUE);

-- 5. 插入Banner (用于List测试)
INSERT INTO `banner` (`id`, `tenant_id`, `title`, `status`) VALUES
(1001, 'tenant_a', '【A租户】VIP2专属活动', 1),
(1002, 'tenant_a', '【A租户】VIP3专属活动', 1),
(1003, 'tenant_a', '【A租户】给Bob的私信Banner', 1),
(1004, 'tenant_a', '【A租户】已禁用活动', 0),
(2001, 'tenant_b', '【B租户】黄金会员活动', 1);

-- 6. 插入Banner位置 (用于List测试)
-- Banner 1001在两个位置显示
INSERT INTO `banner_position` (`banner_id`, `position_code`, `image_url`) VALUES
(1001, 'homepage_top', 'https://example.com/banners/1001_home.png'),
(1001, 'lobby_right', 'https://example.com/banners/1001_lobby.png'),
-- 其他Banner
(1002, 'homepage_top', 'https://example.com/banners/1002_home.png'),
(1003, 'inbox_popup', 'https://example.com/banners/1003_inbox.png'),
(2001, 'homepage_top', 'https://example.com/banners/2001_home.png');

-- 7. 插入Banner用户类型关联 (用于Set/Union测试)
-- Banner 1001对所有VIP2可见
INSERT INTO `banner_user_type` (`banner_id`, `show_user_type`, `user_type_id`) VALUES
(1001, 'level', 2);
-- Banner 1002对所有VIP3可见
INSERT INTO `banner_user_type` (`banner_id`, `show_user_type`, `user_type_id`) VALUES
(1002, 'level', 3);
-- Banner 1003只对Bob (userId=102) 可见
INSERT INTO `banner_user_type` (`banner_id`, `show_user_type`, `user_type_id`) VALUES
(1003, 'user', 102);
-- Banner 2001对所有黄金会员(levelId=12)可见
INSERT INTO `banner_user_type` (`banner_id`, `show_user_type`, `user_type_id`) VALUES
(2001, 'level', 12);

-- 8. 插入用户配置 (用于Hash测试)
-- Alice的配置
INSERT INTO `user_preference` (`user_id`, `pref_key`, `pref_value`) VALUES
(101, 'theme', 'dark'),
(101, 'language', 'en_US'),
(101, 'notification_sound', 'enabled');
-- Bob的配置
INSERT INTO `user_preference` (`user_id`, `pref_key`, `pref_value`) VALUES
(102, 'theme', 'light'),
(102, 'language', 'zh_CN');



