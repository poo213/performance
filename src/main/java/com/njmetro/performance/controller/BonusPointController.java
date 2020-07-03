package com.njmetro.performance.controller;


import com.njmetro.performance.domain.BonusPoint;
import com.njmetro.performance.service.BonusPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 加分项 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/bonus-point")
@RequiredArgsConstructor
public class BonusPointController {

private final BonusPointService bonusPointService;

@GetMapping("/getBonusPoint")
public List<BonusPoint> getBonusPoint()
{

    return bonusPointService.list();
}
}

