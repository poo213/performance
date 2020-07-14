package com.njmetro.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.*;
import com.njmetro.performance.exception.SystemException;
import com.njmetro.performance.service.ConfigService;
import com.njmetro.performance.service.GradeInfoService;
import com.njmetro.performance.service.StaffService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 打分详情 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-07-01
 */
@Slf4j
@RestController
@RequestMapping("/gradeInfo")
@RequiredArgsConstructor
public class GradeInfoController {

    private final StaffService staffService;
    private final UserService userService;
    private final GradeInfoService gradeInfoService;
    private final ConfigService configService;

    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonth")
    public List<GradeInfoDTO> forGradeCurrentMonth(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}",claims);
        String userId = (String) claims.get("userId");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        List<User> userList = userService.list(userQueryWrapper);

        String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
        String[] scope1 = evaluationScope.split("\\|");
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1));
        List<Staff> list = staffService.list(queryWrapper);
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();
        for (Staff staff : list
        ) {
            if (!staff.getStaffId().equals(userId)) {//把本人排除
                QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId);
                GradeInfo gradeInfo = gradeInfoService.getOne(gradeInfoQueryWrapper);
                GradeInfoDTO gradeInfoDTO = new GradeInfoDTO();
                gradeInfoDTO.setYear(year);
                gradeInfoDTO.setMonth(month);
                if (gradeInfo != null) {
                    gradeInfoDTO.setId(gradeInfo.getId());
                    gradeInfoDTO.setScore(gradeInfo.getScore());
                    gradeInfoDTO.setReason(gradeInfo.getReason());
                    gradeInfoDTO.setAllResult(gradeInfo.getAllResult());
                }
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());

                gradeInfoDTOList.add(gradeInfoDTO);
            }
        }

        return gradeInfoDTOList;
    }

    @CheckTokenAndRole
    @GetMapping("/getGrade")
    public List<GradeResultDTO> getGrade(@RequestParam int year, @RequestParam int month, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
        String[] scope1 = evaluationScope.split("\\|");
        log.info(Arrays.toString(scope1));
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1)).ne("staff_id",userId);
        List<Staff> staffList = staffService.list(queryWrapper);
        log.info("staffList=" + staffList);
        List<GradeResultDTO> gradeResultDTOList = new ArrayList<>();

        for (Staff item : staffList) {

            QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
            gradeInfoQueryWrapper.eq("for_grader_id", item.getStaffId()).eq("year", year).eq("month", month);
            List<GradeInfo> gradeInfoList = gradeInfoService.list(gradeInfoQueryWrapper);
            BigDecimal head0Score = new BigDecimal(0);
            BigDecimal head1Score = new BigDecimal(0);
            BigDecimal head2Score = new BigDecimal(0);
            BigDecimal sectionScore = new BigDecimal(0);
            BigDecimal teamScore = new BigDecimal(0);
            String head0Reason = null;
            String head1Reason = null;
            String head2Reason = null;
            String sectionReason = null;
            String teamReason = null;
            for (GradeInfo gradeInfo : gradeInfoList
            ) {
                if (gradeInfo.getGraderJobName().equals("院长")) {
                    head0Reason = gradeInfo.getReason();
                    head0Score = gradeInfo.getScore();
                } else if (gradeInfo.getGraderJobName().equals("副院长1")) {
                    head1Reason = gradeInfo.getReason();
                    head1Score = gradeInfo.getScore();
                } else if (gradeInfo.getGraderJobName().equals("副院长2")) {
                    head2Reason = gradeInfo.getReason();
                    head2Score = gradeInfo.getScore();
                } else if (gradeInfo.getGraderJobName().equals("科长")) {
                    sectionReason = gradeInfo.getReason();
                    sectionScore = gradeInfo.getScore();
                } else if (gradeInfo.getGraderJobName().equals("组长")) {
                    teamReason = gradeInfo.getReason();
                    teamScore = gradeInfo.getScore();
                } else {
                }

            }
            GradeResultDTO gradeResultDTO = GradeResultDTO.builder().staffId(item.getStaffId()).staffName(item.getStaffName()).section(item.getSection()).team(item.getTeam())
                    .head0Reason(head0Reason)
                    .head0Score(head0Score)
                    .head1Reason(head1Reason)
                    .head1Score(head1Score)
                    .head2Reason(head2Reason)
                    .head2Score(head2Score)
                    .sectionReason(sectionReason)
                    .sectionScore(sectionScore)
                    .teamReason(teamReason)
                    .teamScore(teamScore)
                    .build();
            gradeResultDTOList.add(gradeResultDTO);

        }
        return gradeResultDTOList;
    }

    @CheckTokenAndRole
    @PostMapping("/saveGradeItem")
    public String saveGradeItem(@RequestBody GradeInfo gradeInfo, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        gradeInfo.setGraderJobName(userService.getOne(userQueryWrapper).getJobName());
        gradeInfo.setGraderName(userService.getOne(userQueryWrapper).getUserName());
        gradeInfo.setGraderId(userId);
        QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
        gradeInfoQueryWrapper.eq("for_grader_id", gradeInfo.getForGraderId())
                .eq("year", gradeInfo.getYear())
                .eq("month", gradeInfo.getMonth())
                .eq("grader_id", userId);
        if (gradeInfoService.getOne(gradeInfoQueryWrapper) != null) {
            if (gradeInfoService.update(gradeInfo, gradeInfoQueryWrapper)) {
                System.out.println("更新记录");
                return "success";
            } else {
                throw new SystemException();
            }
        } else {
            if (gradeInfoService.save(gradeInfo)) {
                System.out.println("新建记录");
                return "success";
            } else {
                throw new SystemException();
            }
        }
    }

}

