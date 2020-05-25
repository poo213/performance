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
 * 打分表
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("score_list")
public class ScoreList implements Serializable {

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
     * 工号
     */
    @TableField("staff_id")
    private String staffId;

    /**
     * 组长打分
     */
    @TableField("group_score")
    private BigDecimal groupScore;
    /**
     * 组长打分理由
     */
    @TableField("group_reason")
    private String groupReason;
    /**
     * 科长打分
     */
    @TableField("section_score")
    private BigDecimal sectionScore;
    /**
     * 科长打分理由
     */
    @TableField("section_reason")
    private String sectionReason;
    /**
     * 院长打分（全员）
     */
    @TableField("header_score")
    private BigDecimal headerScore;
    /**
     * 院长打分理由 （全员）
     */
    @TableField("header_reason")
    private String headerReason;

    /**
     * 院长打分（分管）
     */
    @TableField("header_charge_score")
    private BigDecimal headerChargeScore;
    /**
     * 院长打分理由 （分管）
     */
    @TableField("header_charge_reason")
    private String headerChargeReason;

    /**
     * 管理员打分
     */
    @TableField("admin_score")
    private BigDecimal adminScore;
    /**
     * 管理员打分理由
     */
    @TableField("admin_reason")
    private String adminReason;

}
