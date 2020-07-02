package com.njmetro.performance.service;

import com.njmetro.performance.domain.ScoreList;
import com.njmetro.performance.domain.Staff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
public interface StaffService extends IService<Staff> {
    List<String> getSectionList();
    List<String> getTeamList();
//    List<String> getStaffList(String[] scope);
}
