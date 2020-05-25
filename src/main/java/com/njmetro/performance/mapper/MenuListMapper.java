package com.njmetro.performance.mapper;

import com.njmetro.performance.domain.MenuList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuListMapper {
    @Select("SELECT * from menu_list")
    List<MenuList> getMenuList();
}
