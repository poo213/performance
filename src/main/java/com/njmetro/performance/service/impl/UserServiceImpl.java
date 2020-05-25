package com.njmetro.performance.service.impl;

import com.njmetro.performance.domain.User;
import com.njmetro.performance.mapper.StaffMapper;
import com.njmetro.performance.mapper.UserMapper;
import com.njmetro.performance.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    public UserMapper userMapper;
    @Override
   public String getCheckedEvaluationScope(String userId)
   {
        return userMapper.getCheckedEvaluationScope(userId);
   }
    @Override
   public User getUserInfo(String userId)
   {
       return userMapper.getUserInfo(userId);
   }
}
