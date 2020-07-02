package com.njmetro.performance.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @program: performance
 * @description: gradeinfoDTO
 * @author: zc
 * @create: 2020-07-01 13:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfoDTO {
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
    @TableField("grader_role")
    private String graderRole;

    /**
     * 被打分人工号
     */
    @TableField("for_grader_id")
    private String forGraderId;

    /**
     * 部门
     */
    @TableField("department")
    private String department;

    /**
     * 科室
     */
    @TableField("section")
    private String section;

    /**
     * 组
     */
    @TableField("team")
    private String team;
    /**
     * 打分
     */
    private BigDecimal score;
    /**
     * 打分理由
     */
    private String reason;

    private String staffName;

}
