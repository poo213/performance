package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.MenuList;
import com.njmetro.performance.domain.User;
import com.njmetro.performance.service.MenuListService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/menu-list")
@RequiredArgsConstructor
public class MenuListController {
    private final MenuListService menuListService;

    @CheckTokenAndRole
    @GetMapping("/getMenuList")
    public List<MenuList> getMenuList(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String role   = (String) claims.get("role");
        QueryWrapper<MenuList> menuListQueryWrapper = new QueryWrapper<>();

        menuListQueryWrapper.like("role",role);
        List<MenuList> menuLists = menuListService.list(menuListQueryWrapper);

        return menuLists;
    }

}

