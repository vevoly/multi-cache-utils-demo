package com.github.vevoly.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Banner与用户类型的多对多关联表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("banner_user_type")
public class BannerUserType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("banner_id")
    private Long bannerId;

    /**
     * 类型: user, level
     */
    @TableField("show_user_type")
    private String showUserType;

    /**
     * 用户ID或用户等级ID
     */
    @TableField("user_type_id")
    private Long userTypeId;
}
