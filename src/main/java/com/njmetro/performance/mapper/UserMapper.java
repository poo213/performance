package com.njmetro.performance.mapper;

import com.njmetro.performance.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("Select evaluation_scope  from user where user_id=#{userId}")
    String getCheckedEvaluationScope(@Param("userId") String userId);
    @Select("Select *  from user where user_id=#{userId}")
    User getUserInfo(@Param("userId") String userId);

}
