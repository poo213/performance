package com.njmetro.performance.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: performance
 * @description: checkbox信息
 * @author: zc
 * @create: 2020-07-10 15:49
 **/
@Data
public class CheckBoxInfo {
    private Integer id;
    private String title;
    private BigDecimal value;
}
