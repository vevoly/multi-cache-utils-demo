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
 * 用户偏好配置表
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Getter
@Setter
@TableName("user_preference")
public class UserPreference implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    /**
     * 配置项Key: e.g., theme, language
     */
    @TableField("pref_key")
    private String prefKey;

    /**
     * 配置项Value
     */
    @TableField("pref_value")
    private String prefValue;
}
