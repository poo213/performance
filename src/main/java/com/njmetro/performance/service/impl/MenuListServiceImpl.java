package com.njmetro.performance.service.impl;

import com.njmetro.performance.domain.MenuList;
import com.njmetro.performance.mapper.MenuListMapper;
import com.njmetro.performance.mapper.StaffMapper;
import com.njmetro.performance.service.MenuListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuListServiceImpl implements MenuListService {
    @Resource
    public MenuListMapper menuListMapper;
    @Override
    public List<MenuList> getMenuList(){
        return menuListMapper.getMenuList();
    }
}
