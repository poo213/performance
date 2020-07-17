package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.njmetro.performance.domain.Config;
import com.njmetro.performance.service.ConfigService;
import com.njmetro.performance.token.CheckTokenAndRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 参数表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-07-01
 */
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;

    @CheckTokenAndRole
    @GetMapping("/confirmMonth")
    public String confirmMonth(@RequestParam int year, @RequestParam int month, HttpServletRequest request) {
        UpdateWrapper<Config> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",1).set("year", year).set("month", month);
        if (configService.update(updateWrapper)) {
            return "更新成功";
        }
        else{
            return "保存失败";
        }
    }
    @CheckTokenAndRole
    @GetMapping("/getConfig")
    public Config getConfig()
    {
        return  configService.getById(1);
    }

    @CheckTokenAndRole
    @GetMapping("/getSystemState")
    public boolean getSystem()
    {
        return  configService.getById(1).isSystemState();
    }

    @CheckTokenAndRole
    @GetMapping("/setSystemState")
    public String setSystemState(@RequestParam boolean state)
    {
        UpdateWrapper<Config> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",1).set("system_state", state);
        if (configService.update(updateWrapper)) {
            return "更新成功";
        }
        else{
            return "保存失败";
        }
    }
}

