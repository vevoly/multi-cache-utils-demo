package com.github.vevoly.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Banner基础信息表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("tenant_id")
    private String tenantId;

    /**
     * Banner标题
     */
    @TableField("title")
    private String title;

    /**
     * 跳转类型: 1=帖子, 2=URL
     */
    @TableField("target_link_type")
    private Integer targetLinkType;

    /**
     * 跳转链接
     */
    @TableField("target_link")
    private String targetLink;

    /**
     * 状态: 1=启用, 0=禁用
     */
    @TableField("status")
    private Integer status;
}
