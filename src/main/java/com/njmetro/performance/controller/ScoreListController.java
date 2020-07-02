package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.*;
import com.njmetro.performance.exception.SystemException;
import com.njmetro.performance.service.GradeInfoService;
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
//    private final ScoreListService scoreListService;
//    private final StaffService staffService;
//    private final UserService userService;
//    private final GradeInfoService gradeInfoService;

    //    @CheckTokenAndRole
//    @GetMapping("/forGrade")
//    public List<ScoreListDTO> forGrade(@RequestParam int year,@RequestParam int month,HttpServletRequest request) {
//
//        System.out.println("year = " + year + ", month = " + month + ", request = " + request);
//        System.out.println("request = " + request);
//        Claims claims = (Claims) request.getAttribute("claims");
//        System.out.println("claims = " + claims);
//        String userId = (String) claims.get("userId");
//        System.out.println("*******userId = " + userId);
//        //String userId = "111180";
//        String  evaluationScope=userService.getUserInfo(userId).getEvaluationScope();
//
//        String[] scope1 = evaluationScope.split("\\|");
//
//        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
//
//        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1));
//        List<Staff> list = staffService.list(queryWrapper);
//        System.out.println("list = " + list);
//        List<ScoreListDTO> scoreListDTOList = new ArrayList<>();
//
//        for (Staff staff : list
//        ) {
//            ;//工号
//
//            QueryWrapper<ScoreList> scoreListQueryWrapper = new QueryWrapper<>();
//            scoreListQueryWrapper.eq("staff_id", staff.getStaffId()).eq("year",year).eq("month",month);
//            ScoreList scoreList = scoreListService.getOne(scoreListQueryWrapper);
//            ScoreListDTO scoreListDTO = new ScoreListDTO();
//            scoreListDTO.setYear(year);
//            scoreListDTO.setMonth(month);
//            if (scoreList != null) {
//                scoreListDTO.setId(scoreList.getId());
//                scoreListDTO.setGroupScore(scoreList.getGroupScore());
//                scoreListDTO.setGroupReason(scoreList.getGroupReason());
//                scoreListDTO.setHeaderScore(scoreList.getHeaderScore());
//                scoreListDTO.setHeaderReason(scoreList.getHeaderReason());
//                scoreListDTO.setHeaderChargeScore(scoreList.getHeaderChargeScore());
//                scoreListDTO.setHeaderChargeReason(scoreList.getHeaderChargeReason());
//                scoreListDTO.setSectionScore(scoreList.getSectionScore());
//                scoreListDTO.setSectionReason(scoreList.getSectionReason());
//                scoreListDTO.setAdminScore(scoreList.getAdminScore());
//                scoreListDTO.setAdminReason(scoreList.getAdminReason());
//            }
//
//
//            scoreListDTO.setStaffId(staff.getStaffId());
//            scoreListDTO.setTeam(staff.getTeam());
//            scoreListDTO.setSection(staff.getSection());
//            scoreListDTO.setStaffName(staff.getStaffName());
//            scoreListDTO.setDepartment(staff.getDepartment());
//            scoreListDTOList.add(scoreListDTO);
//
//        }
//
//        return scoreListDTOList;
//    }
//    @CheckTokenAndRole
//    @GetMapping("/forGradeCurrentMonth")
//    public List<GradeInfoDTO> forGradeCurrentMonth(HttpServletRequest request) {
//        Claims claims = (Claims) request.getAttribute("claims");
//        String userId = (String) claims.get("userId");
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("user_id", userId);
//        List<User> userList = userService.list(userQueryWrapper);
//        String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
//        String[] scope1 = evaluationScope.split("\\|");
//        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1));
//        List<Staff> list = staffService.list(queryWrapper);
//        System.out.println("list = " + list);
//        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
//        for (Staff staff : list
//        ) {
//            QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
//            gradeInfoQueryWrapper.eq("grader_id", staff.getStaffId()).eq("year", 2020).eq("month", 7);
//            GradeInfo gradeInfo = gradeInfoService.getOne(gradeInfoQueryWrapper);
//            GradeInfoDTO gradeInfoDTO = new GradeInfoDTO();
//            gradeInfoDTO.setYear(2020);
//            gradeInfoDTO.setMonth(7);
//            if (gradeInfo != null) {
//                gradeInfoDTO.setId(gradeInfo.getId());
////                scoreListDTO.set
//                gradeInfoDTO.setScore(gradeInfo.getScore());
//
//                gradeInfoDTO.setReason(gradeInfo.getReason());
//            }
//
//
//            gradeInfoDTO.setForGraderId(staff.getStaffId());
//            gradeInfoDTO.setTeam(staff.getTeam());
//            gradeInfoDTO.setSection(staff.getSection());
//            gradeInfoDTO.setStaffName(staff.getStaffName());
//            gradeInfoDTO.setDepartment(staff.getDepartment());
//            gradeInfoDTOList.add(gradeInfoDTO);
//
//        }
//
//        return gradeInfoDTOList;
//    }
//
//    @PostMapping("/saveGradeItem")
//    public String saveGradeItem(@RequestBody GradeInfo gradeInfo) {
//        QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
//        gradeInfoQueryWrapper.eq("for_grader_id", gradeInfo.getForGraderId())
//                .eq("year", gradeInfo.getYear())
//                .eq("month", gradeInfo.getMonth());
//        if (gradeInfoService.getOne(gradeInfoQueryWrapper) != null) {
//            if (gradeInfoService.update(gradeInfo, gradeInfoQueryWrapper)) {
//                System.out.println("更新记录");
//                return "success";
//            } else {
//                throw new SystemException();
//            }
//        } else {
//            if (gradeInfoService.save(gradeInfo)) {
//                System.out.println("新建记录");
//                return "success";
//            } else {
//                throw new SystemException();
//            }
//        }
//

//    }
}


