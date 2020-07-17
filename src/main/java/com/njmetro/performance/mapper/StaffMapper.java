package com.njmetro.performance.mapper;

import com.njmetro.performance.domain.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
public interface StaffMapper extends BaseMapper<Staff> {

    @Select("Select distinct section from staff where section is not null and section !=''")
    List<String> getSectionList();

    @Select("Select distinct team from staff where team is not null and team !=''")
    List<String> getTeamList();

    @Select("Select * from staff where section in #{scope} or team in #{scope}")
    List<Staff> getStaffList(@Param("scope") String[] scope);

}
