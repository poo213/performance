package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njmetro.performance.domain.Staff;
import com.njmetro.performance.exception.AuthenticationException;
import com.njmetro.performance.exception.ErrorEnum;
import com.njmetro.performance.exception.PerformanceException;
import com.njmetro.performance.exception.ThesisException;
import com.njmetro.performance.mapper.StaffMapper;
import com.njmetro.performance.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@CrossOrigin
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping("/getList")
    public List<Staff> list()
    {throw  new ThesisException(HttpStatus.BAD_REQUEST, ErrorEnum.THESIS_PERK_SAVE_FAILED);
        /*QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        List<Staff> list = staffService.list(queryWrapper);

        // staffMapper.insert()

        //throw new AuthenticationException();
       return list;*/

    }
    @GetMapping("/getStaffList")
    public List<Staff> StaffList()
    {
        return staffService.list();
    }
    @PostMapping("/addStaff")
    public String AddStaff(@RequestBody @Valid  Staff staff)
    {

            if(staffService.save(staff)){
             return  "添加成功";
            }
            else {
                throw  new PerformanceException(HttpStatus.BAD_REQUEST, ErrorEnum.THESIS_PERK_SAVE_FAILED);
            }


    }

    @GetMapping("/getEvaluationScopeList")
    public List<String> getEvaluationScopeList()
    {

        List<String> sectionList = staffService.getSectionList();
        List<String> teamList    = staffService.getTeamList();
        System.out.println(sectionList);
        System.out.println(teamList);
        sectionList.addAll(teamList);
        return sectionList.stream().distinct().collect(Collectors.toList());
    }



}

