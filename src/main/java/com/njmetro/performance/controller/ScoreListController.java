package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.ScoreList;
import com.njmetro.performance.domain.ScoreListDTO;
import com.njmetro.performance.domain.Staff;
import com.njmetro.performance.exception.SystemException;
import com.njmetro.performance.service.ScoreListService;
import com.njmetro.performance.service.StaffService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 打分表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@CrossOrigin
@RestController
@RequestMapping("/scoreList")
@RequiredArgsConstructor
public class ScoreListController {
    private final ScoreListService scoreListService;
    private final StaffService staffService;
    private final UserService userService;
    @CheckTokenAndRole
    @GetMapping("/forGrade")
    public List<ScoreListDTO> forGrade(@RequestParam int year,@RequestParam int month,HttpServletRequest request) {

        System.out.println("year = " + year + ", month = " + month + ", request = " + request);
        System.out.println("request = " + request);
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("claims = " + claims);
        String userId = (String) claims.get("userId");
        System.out.println("*******userId = " + userId);
        //String userId = "111180";
        String  evaluationScope=userService.getUserInfo(userId).getEvaluationScope();

        String[] scope1 = evaluationScope.split("\\|");

        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1));
        List<Staff> list = staffService.list(queryWrapper);
        System.out.println("list = " + list);
        List<ScoreListDTO> scoreListDTOList = new ArrayList<>();

        for (Staff staff : list
        ) {
            ;//工号

            QueryWrapper<ScoreList> scoreListQueryWrapper = new QueryWrapper<>();
            scoreListQueryWrapper.eq("staff_id", staff.getStaffId()).eq("year",year).eq("month",month);
            ScoreList scoreList = scoreListService.getOne(scoreListQueryWrapper);
            ScoreListDTO scoreListDTO = new ScoreListDTO();
            scoreListDTO.setYear(year);
            scoreListDTO.setMonth(month);
            if (scoreList != null) {
                scoreListDTO.setId(scoreList.getId());
                scoreListDTO.setGroupScore(scoreList.getGroupScore());
                scoreListDTO.setGroupReason(scoreList.getGroupReason());
                scoreListDTO.setHeaderScore(scoreList.getHeaderScore());
                scoreListDTO.setHeaderReason(scoreList.getHeaderReason());
                scoreListDTO.setHeaderChargeScore(scoreList.getHeaderChargeScore());
                scoreListDTO.setHeaderChargeReason(scoreList.getHeaderChargeReason());
                scoreListDTO.setSectionScore(scoreList.getSectionScore());
                scoreListDTO.setSectionReason(scoreList.getSectionReason());
                scoreListDTO.setAdminScore(scoreList.getAdminScore());
                scoreListDTO.setAdminReason(scoreList.getAdminReason());
            }


            scoreListDTO.setStaffId(staff.getStaffId());
            scoreListDTO.setTeam(staff.getTeam());
            scoreListDTO.setSection(staff.getSection());
            scoreListDTO.setStaffName(staff.getStaffName());
            scoreListDTO.setDepartment(staff.getDepartment());
            scoreListDTOList.add(scoreListDTO);

        }

        return scoreListDTOList;
    }

    @PostMapping("/saveGradeItem")
    public String saveGradeItem(@RequestBody ScoreList scoreList) {
        QueryWrapper<ScoreList> scoreListQueryWrapper = new QueryWrapper<>();
        scoreListQueryWrapper.eq("staff_id", scoreList.getStaffId())
                .eq("year", scoreList.getYear())
                .eq("month", scoreList.getMonth());
        if (scoreListService.getOne(scoreListQueryWrapper) != null) {
            if (scoreListService.update(scoreList, scoreListQueryWrapper)) {
                System.out.println("跟新记录");
                return "success";
            } else {
                throw new SystemException();
            }
        } else {
            if (scoreListService.save(scoreList)) {
                System.out.println("新建记录");
                return "success";
            } else {
                throw new SystemException();
            }
        }


    }
}


