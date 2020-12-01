package com.njmetro.performance.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 员工信息
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("staff")
public class Staff implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工姓名
     */
    @NotBlank(message = "姓名不能为空")
    @TableField("staff_name")
    private String staffName;

    /**
     * 工号
     */
    @Size(min=6,max=6,message = "长度必须为6位数")
    @TableField("staff_id")
    private String staffId;

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
     * 员工类型 0表示领导，1表示一般员工
     */
    private Integer type;


}
