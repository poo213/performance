package com.njmetro.performance.domain;

import java.math.BigDecimal;
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
 * 打分详情
 * </p>
 *
 * @author zc
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("grade_info")
public class GradeInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年
     */
    @TableField("year")
    private Integer year;

    /**
     * 月
     */
    @TableField("month")
    private Integer month;

    /**
     * 打分人工号
     */
    @TableField("grader_id")
    private String graderId;

    /**
     * 打分人姓名
     */
    @TableField("grader_name")
    private String graderName;

    /**
     * 打分人角色
     */
    @TableField("grader_job_name")
    private String graderJobName;

    /**
     * 被打分人工号
     */
    @TableField("for_grader_id")
    private String forGraderId;

    /**
     * 打分
     */
    @TableField("score")
    private BigDecimal score;

    /**
     * 打分理由
     */
    @TableField("reason")
    private String reason;
    /**
     * 打分理由
     */
    @TableField("all_result")
    private String allResult;
    /**
     * 打分角色
     */
    @TableField("cent_role")
    private String centRole;


}
