package com.njmetro.performance.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @program: performance
 * @description: Result
 * @author: zc
 * @create: 2020-07-02 13:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeResultDTO {
    private Integer id;
    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 工号
     */
    private String staffId;

    /**
     * 部门
     */
    private String department;

    /**
     * 科室
     */
    private String section;

    /**
     * 组
     */
    private String team;

    private String head0Reason;//院长（全员）
    private String headReasonFen;//院长（分管）
    private String head1Reason;//隋院长（全员）
    private String head2Reason;//唐院长（分管）
    private String sectionReason;//科长
    private String teamReason;//组长

    private BigDecimal head0Score;
    private BigDecimal headScoreFen;
    private BigDecimal head1Score;
    private BigDecimal head2Score;
    private BigDecimal sectionScore;
    private BigDecimal teamScore;

}
