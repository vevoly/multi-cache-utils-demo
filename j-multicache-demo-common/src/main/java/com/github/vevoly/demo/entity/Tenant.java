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
 * 租户信息表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID (e.g., tenant_a)
     */
    @TableId("id")
    private String id;

    /**
     * 租户名称
     */
    @TableField("name")
    private String name;

    /**
     * 状态: 1=正常, 0=禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
