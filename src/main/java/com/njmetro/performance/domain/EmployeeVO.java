package com.njmetro.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: performance
 * @description: employeeVO
 * @author: zc
 * @create: 2020-07-13 16:24
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private Employee employee;

    private String token;
}
