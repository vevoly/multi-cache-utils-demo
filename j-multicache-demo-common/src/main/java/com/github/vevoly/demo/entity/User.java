package com.github.vevoly.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("id")
    private Long id;

    /**
     * 所属租户ID
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户等级ID
     */
    @TableField("user_level_id")
    private Long userLevelId;

    /**
     * 用户头像ID
     */
    @TableField("user_avatar_id")
    private Long userAvatarId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
