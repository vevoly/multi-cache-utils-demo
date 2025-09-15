package com.github.vevoly.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户等级定义表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("user_level")
public class UserLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 等级ID
     */
    @TableId("id")
    private Long id;

    /**
     * 所属租户ID
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * 等级名称 (e.g., VIP1, VIP2)
     */
    @TableField("level_name")
    private String levelName;

    /**
     * 等级图标URL
     */
    @TableField("level_icon")
    private String levelIcon;
}
