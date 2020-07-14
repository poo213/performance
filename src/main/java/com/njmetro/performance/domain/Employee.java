package com.njmetro.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 职工
 *
 * @author RCNJTECH
 * @date 2020/4/11 15:21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 职工工号
     */
    private String id;

    /**
     * 职工姓名
     */
    private String name;

    /**
     * 职工单位
     */
    private String department;

    /**
     * 职工职务
     */
    private String post;

    /**
     * 职工归属线路
     */
    private String line;

    /**
     * 职工手机
     */
    private String phone;

    /**
     * 职工权限，论文报销人为 0
     */
    private String role;
}
