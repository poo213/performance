package com.njmetro.performance.service.impl;

import com.njmetro.performance.domain.Config;
import com.njmetro.performance.mapper.ConfigMapper;
import com.njmetro.performance.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-07-01
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}
