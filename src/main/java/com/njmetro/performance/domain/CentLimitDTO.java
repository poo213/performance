package com.njmetro.performance.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: performance
 * @description: 打分上线
 * @author: zc
 * @create: 2020-11-30 15:51
 **/
@Data
public class CentLimitDTO {
    private BigDecimal headLimit;//院长全员加分
    private BigDecimal fenLimit;//分管加分上限
    private BigDecimal sectionLimit;//科长加分上限
}
