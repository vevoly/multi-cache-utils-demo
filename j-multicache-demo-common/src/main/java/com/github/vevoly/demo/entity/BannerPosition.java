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
 * Banner位置关联表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("banner_position")
public class BannerPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("banner_id")
    private Long bannerId;

    /**
     * 位置编码: pc, h5, ios, android
     */
    @TableField("position_code")
    private String positionCode;

    /**
     * 图片URL
     */
    @TableField("image_url")
    private String imageUrl;
}
