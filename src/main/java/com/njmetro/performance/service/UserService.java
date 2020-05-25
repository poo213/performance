package com.njmetro.performance.service;

import com.njmetro.performance.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
public interface UserService extends IService<User> {
    String getCheckedEvaluationScope(String userId);
    User getUserInfo(String userId);
}
