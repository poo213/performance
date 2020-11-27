package com.njmetro.performance.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 角色
     */
    @TableField("role")
    private String role;

    /**
     * 打分对象
     */
    @TableField("evaluation_scope")
    private String evaluationScope;

    /**
     * 打分上限
     */
    @TableField("score_high_limit")
    private String scoreHighLimit;

    private String userName;
    private String jobName;


}
