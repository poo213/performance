package com.njmetro.performance.controller;


import com.njmetro.performance.domain.*;
import com.njmetro.performance.exception.EmployeeException;
import com.njmetro.performance.exception.ErrorEnum;
import com.njmetro.performance.exception.PerformanceException;
import com.njmetro.performance.service.MenuListService;
import com.njmetro.performance.service.StaffService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.JwsToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getMenu")
    public List<Menu> GetMenu()
    {
        List<MenuList> menuLists = menuListService.getMenuList();
        List<Menu> listMenu = new ArrayList<>();//传到前台
        for(MenuList menuList :menuLists)
        {
            List<MenuInfo> list = new ArrayList<>();
            if(menuList.getKey_menu() == "0")
            {

                MenuInfo menuInfo= new MenuInfo();
                menuInfo.setIndex(menuList.getIndex_menu());
                menuInfo.setName(menuList.getName());
                list.add(menuInfo);
            }
            Menu menu = new Menu();
            menu.setIndex("0");
            menu.setData(list);
            listMenu.add(menu);
        }





        System.out.println("listMenu = " + listMenu);
       return listMenu;
    }
    @PostMapping("/Login")
    public LoginInfo Login(@RequestBody  User user)
    {
        System.out.println(user);
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

}

