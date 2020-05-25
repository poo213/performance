package com.njmetro.performance.service.impl;

import com.njmetro.performance.domain.Staff;
import com.njmetro.performance.mapper.StaffMapper;
import com.njmetro.performance.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
    @Resource
    public StaffMapper staffMapper;
    @Override
    public List<String> getSectionList(){
        return staffMapper.getSectionList();
    }
    @Override
    public List<String> getTeamList(){
        return staffMapper.getTeamList();
    }
}
