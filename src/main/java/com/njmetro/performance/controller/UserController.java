package com.njmetro.performance.controller;


import com.njmetro.performance.domain.*;
import com.njmetro.performance.exception.EmployeeException;
import com.njmetro.performance.exception.ErrorEnum;
import com.njmetro.performance.exception.PerformanceException;
import com.njmetro.performance.service.MenuListService;
import com.njmetro.performance.service.StaffService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import com.njmetro.performance.token.JwsToken;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final StaffService staffService;
    private final MenuListService menuListService;

    @GetMapping("/getUserList")
    public List<User> getUserList()
    {
        return userService.list();
    }
    @GetMapping("/getCheckedEvaluationScopeList")
    public List<String> getCheckedEvaluationScopeList(@RequestParam String userId)
    {
        System.out.println("******************");
        System.out.println("userId = " + userId);
        String  evaluationScope= userService.getCheckedEvaluationScope(userId);
        System.out.println(evaluationScope);
       String[]  checkedEvaluationScopeList = evaluationScope.split("\\|");
       return Arrays.asList(checkedEvaluationScopeList);
    }
    @PostMapping("/UpdateUser")
    public String UpdateUser(@RequestBody @Valid User user)
    {
        System.out.println("user = " + user);
        if(userService.updateById(user))
        {
            return "修改成功";
        }
        else{
            throw new PerformanceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.SYSTEM_ERROR);
        }

    }
    @PostMapping("/create")
    public String  create(@RequestBody @Valid User user)
    {
        if(userService.save(user))
        {
            return "修改成功";
        }
        else{
            throw new PerformanceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.SYSTEM_ERROR);
        }
    }
    @PostMapping("/Login")
    public LoginInfo Login(@RequestBody  User user)
    {
        User userInfo =  userService.checkExist(user.getUserId(),user.getPassword());

        if(userInfo==null)
        {
            throw  new EmployeeException(HttpStatus.INTERNAL_SERVER_ERROR,ErrorEnum.EMPLOYEE_NOT_FOUND);
        }
        else{
            LoginInfo loginInfo=new LoginInfo();
            loginInfo.setRole(userInfo.getRole());
            loginInfo.setUserId(userInfo.getUserId());
            loginInfo.setToken(JwsToken.createPC(userInfo));
            return loginInfo;
        }
    }
    @CheckTokenAndRole
    @GetMapping("/getUserId")
    public String getUserId(HttpServletRequest request)
    {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("claims"+claims);
        String userId = (String) claims.get("userId");
        System.out.println("userId"+userId);
        return  userId;
    }
    //获取当前登陆人姓名
    @CheckTokenAndRole
    @GetMapping("/getUserName")
    public String getUserName(HttpServletRequest request)
    {
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = (String) claims.get("userName");
        System.out.println(userName);
        return  userName;
    }

}

