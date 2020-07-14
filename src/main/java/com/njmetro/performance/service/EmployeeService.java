package com.njmetro.performance.service;


import com.njmetro.performance.domain.Employee;

/**
 * 职工 Service 接口
 *
 * @author RCNJTECH
 * @date 2020/4/14 14:06
 */
public interface EmployeeService {

    /**
     * 根据免登授权码查询职工信息
     *
     * @param code 免登授权码
     * @return 职工信息
     */
    Employee getByCode(String code);

}
