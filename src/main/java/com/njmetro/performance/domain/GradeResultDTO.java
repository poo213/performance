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

    private String head0Reason;//院长
    private String head1Reason;//隋院长
    private String head2Reason;//唐院长
    private String sectionReason;//科长
    private String teamReason;//组长

    private BigDecimal head0Score;
    private BigDecimal head1Score;
    private BigDecimal head2Score;
    private BigDecimal sectionScore;
    private BigDecimal teamScore;

}
