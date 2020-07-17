package com.njmetro.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.Employee;
import com.njmetro.performance.domain.EmployeeVO;
import com.njmetro.performance.domain.User;
import com.njmetro.performance.service.EmployeeService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import com.njmetro.performance.token.JwsToken;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: performance
 * @description: employee
 * @author: zc
 * @create: 2020-07-13 16:22
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;
    /**
     * 获取企业信息
     *
     * @return 企业信息
     */
    @GetMapping("/corporation")
    public String getCorporation() {
        log.info("corporation: {}","ding94b3e75e6e35e051");
        return "ding94b3e75e6e35e051";
    }
    /**
     * 根据免登授权码查询职工信息
     *
     * @param code 免登授权码
     * @return 职工信息
     */
    @GetMapping("/login/{code}")
    public EmployeeVO loginByCode(@PathVariable String code) {
        log.info("code: {}", code);
        Employee employee = employeeService.getByCode(code);
        log.info("employee: {}", employee.toString());
        String token = JwsToken.create(employee);
        log.info("token:{}",token);
        return new EmployeeVO(employee, token);
    }

    @CheckTokenAndRole
    @GetMapping("/login/token")
    public EmployeeVO loginByToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims: {}", claims);
        String id = (String) claims.get("userId");
        String name = (String) claims.get("name");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        String role="9";//标识此人无权限
        System.out.println("用户表大小="+userService.list(queryWrapper).size());
        if(userService.list(queryWrapper).size()!=0)
        {
            role = userService.list(queryWrapper).get(0).getRole();
            System.out.println("查表角色role="+ role);
        }


        Employee employee = new Employee(id,name,"","","","",role);

        long expirationTimeMillis = claims.getExpiration().getTime();
        long difference = expirationTimeMillis - System.currentTimeMillis();
        String token = (String) request.getAttribute("token");
        // 若 10 分钟后 Token 过期，则重新签发 Token
        if (difference < 600000) {
            token = JwsToken.create(employee);
        }
        return new EmployeeVO(employee, token);
    }
}
