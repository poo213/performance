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

    /**
     * 获取当前需要被打分的人
     *
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonth")
    public List<GradeInfoDTO> forGradeCurrentMonth(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        userService.getUserInfo(userId);
        return null;
    }


    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonth1")
    public List<GradeInfoDTO> forGradeCurrentMonth1(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}", claims);
        //获取打分人的工号
        String userId = (String) claims.get("userId");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        //获取打分范围
        String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
        String jobName = userService.getUserInfo(userId).getJobName();
        String[] scope1 = evaluationScope.split("\\|");
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("section", scope1).or(i -> i.in("team", scope1));
        //获取被打分人列表
        List<Staff> list = staffService.list(queryWrapper);
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();
        Integer i = 1;
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
                gradeInfoDTO.setId(i);
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());
                gradeInfoDTO.setGraderJobName(jobName);
                gradeInfoDTOList.add(gradeInfoDTO);
                i++;
            }

        }

        return gradeInfoDTOList;
    }

    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonthS")
    public List<GradeInfoDTO> forGradeCurrentMonthS(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}", claims);
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
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId).eq("grader_job_name", "科长");
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

    /**
     * 获取信息科所有人
     *
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonthX")
    public List<GradeInfoDTO> forGradeCurrentMonthX(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}", claims);
        String userId = (String) claims.get("userId");
        QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
        staffQueryWrapper.eq("section", "信息技术科");

        List<Staff> list = staffService.list(staffQueryWrapper);
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();
        int i = 1;
        for (Staff staff : list
        ) {
            if (!staff.getStaffId().equals(userId)) {//把本人排除
                QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId).eq("cent_role", "信息科负责人");
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
                gradeInfoDTO.setId(i);
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());

                gradeInfoDTOList.add(gradeInfoDTO);
                i++;
            }
        }
        return gradeInfoDTOList;
    }

    /**
     * 获取分管人员列表
     *
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonthF")
    public List<GradeInfoDTO> forGradeCurrentMonthF(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}", claims);
        String userId = (String) claims.get("userId");

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        User user = userService.getOne(userQueryWrapper);
        //获取分管的科室信息
        String evaluationScope = user.getEvaluationScope();
        String[] scope1 = evaluationScope.split("\\|");

        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111")
                .in("section", scope1).or(i -> i.in("team", scope1));

        List<Staff> list = staffService.list(queryWrapper);
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();

        int i = 1;
        for (Staff staff : list
        ) {
            if (!staff.getStaffId().equals(userId)) {//把本人排除
                QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId).eq("cent_role", "分管");
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
                gradeInfoDTO.setId(i);
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());

                gradeInfoDTOList.add(gradeInfoDTO);
                i++;
            }

        }

        return gradeInfoDTOList;
    }

    /**
     * 获取所有人,院长、副院长面向全员，不包含领导
     *
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonthAll")
    public List<GradeInfoDTO> forGradeCurrentMonthAll(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info("claims:{}", claims);
        String userId = (String) claims.get("userId");

        List<Staff> list = staffService.list();
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();
        int i = 1;
        for (Staff staff : list
        ) {
            //把中层全部排除
            if (!staff.getStaffId().equals("100215") && !staff.getStaffId().equals("100027") && !staff.getStaffId().equals("101300") && !staff.getStaffId().equals("101944") && !staff.getStaffId().equals("101943") && !staff.getStaffId().equals("100111") && !staff.getStaffId().equals("101942")) {
                QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
                //获取当前用户的打分
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId).eq("cent_role", "全员");
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
                gradeInfoDTO.setId(i);
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());

                gradeInfoDTOList.add(gradeInfoDTO);
                i++;
            }
        }
        return gradeInfoDTOList;
    }
    /**
     * 获取所有人，综合科用来打全勤和绩效加分
     *
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/forGradeCurrentMonthZ")
    public List<GradeInfoDTO> forGradeCurrentMonthZ(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");

        List<Staff> list = staffService.list();
        List<GradeInfoDTO> gradeInfoDTOList = new ArrayList<>();
        Integer year = configService.getById(1).getYear();//\读取当前需要打分的年月
        Integer month = configService.getById(1).getMonth();
        int i = 1;
        for (Staff staff : list
        ) {
            //把中层全部排除
            if (!staff.getStaffId().equals("100215") && !staff.getStaffId().equals("100027") && !staff.getStaffId().equals("101300") && !staff.getStaffId().equals("101944") && !staff.getStaffId().equals("101943") && !staff.getStaffId().equals("100111") && !staff.getStaffId().equals("101942")) {
                QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
                //获取当前用户的打分
                gradeInfoQueryWrapper.eq("for_grader_id", staff.getStaffId()).eq("year", year).eq("month", month).eq("grader_id", userId).eq("cent_role", "综合管理员");
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
                gradeInfoDTO.setId(i);
                gradeInfoDTO.setForGraderId(staff.getStaffId());
                gradeInfoDTO.setTeam(staff.getTeam());
                gradeInfoDTO.setSection(staff.getSection());
                gradeInfoDTO.setStaffName(staff.getStaffName());
                gradeInfoDTO.setDepartment(staff.getDepartment());

                gradeInfoDTOList.add(gradeInfoDTO);
                i++;
            }
        }
        return gradeInfoDTOList;
    }
    //获取打分结果
    @CheckTokenAndRole
    @GetMapping("/getGrade1")
    public List<GradeResultDTO> getGrade1(@RequestParam int year, @RequestParam int month, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");

        String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
        String[] scope1 = evaluationScope.split("\\|");
        log.info(Arrays.toString(scope1));
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("staff_id", userId).in("section", scope1).or(i -> i.in("team", scope1));
        List<Staff> staffList = staffService.list(queryWrapper);
        List<GradeResultDTO> gradeResultDTOList = new ArrayList<>();

        for (Staff item : staffList) {
            if (!item.getStaffId().equals(userId)) {//把本人排除
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
        }
        return gradeResultDTOList;
    }

    //获取打分结果
    @CheckTokenAndRole
    @GetMapping("/getGrade")
    public List<GradeResultDTO> getGrade(@RequestParam int year, @RequestParam int month, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        User user = userService.getUserInfo(userId);
        //获取打分的角色
        String evaluationScopeBranch = user.getEvaluationScopeBranch();
        //分为全员，分管，科室负责人
        List<Staff> staffList = new ArrayList<>();
        if (evaluationScopeBranch.contains("全员")) {
            QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("staff_id", userId).ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111");
            staffList = staffService.list(queryWrapper);//获取全部员工，不包含中层
        }
        if (!evaluationScopeBranch.contains("全员")) {

            String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
            String[] scope1 = evaluationScope.split("\\|");
            log.info(Arrays.toString(scope1));
            QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111").in("section", scope1).or(i -> i.in("team", scope1));
            staffList = staffService.list(queryWrapper);//获取全部员工，不包含中层
        }


        List<GradeResultDTO> gradeResultDTOList = new ArrayList<>();
        for (Staff item : staffList) {
            QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
            gradeInfoQueryWrapper.eq("for_grader_id", item.getStaffId()).eq("year", year).eq("month", month);
            List<GradeInfo> gradeInfoList = gradeInfoService.list(gradeInfoQueryWrapper);
            BigDecimal head0Score = new BigDecimal(0);//院长对全员打分
            BigDecimal headScoreFen = new BigDecimal(0);//院长对分管打分
            BigDecimal head1Score = new BigDecimal(0);//隋对对全员打分，副院长1
            BigDecimal head2Score = new BigDecimal(0);//唐对分管，副院长2
            BigDecimal sectionScore = new BigDecimal(0);//科长打分
            BigDecimal teamScore = new BigDecimal(0);//组长打分
            String head0Reason = null;
            String headReasonFen = null;
            String head1Reason = null;
            String head2Reason = null;
            String sectionReason = null;
            String teamReason = null;
            for (GradeInfo gradeInfo : gradeInfoList
            ) {
                //只有院长才能看到院长打分
                if(user.getJobName().equals("院长"))
                {
                    if (gradeInfo.getGraderJobName().equals("院长") && gradeInfo.getCentRole().equals("全员")) {
                        head0Reason = gradeInfo.getReason();
                        head0Score = gradeInfo.getScore();
                    }
                    if (gradeInfo.getGraderJobName().equals("院长") && gradeInfo.getCentRole().equals("分管")) {
                        headReasonFen = gradeInfo.getReason();
                        headScoreFen = gradeInfo.getScore();
                    }
                }
                if(user.getJobName().equals("院长")||user.getJobName().equals("副院长1")) {
                    if (gradeInfo.getGraderJobName().equals("副院长1") && gradeInfo.getCentRole().equals("全员")) {
                        head1Reason = gradeInfo.getReason();
                        head1Score = gradeInfo.getScore();
                    }
                }
                if(user.getJobName().equals("院长")||user.getJobName().equals("副院长2")) {
                    if (gradeInfo.getGraderJobName().equals("副院长2") && gradeInfo.getCentRole().equals("分管")) {
                        head2Reason = gradeInfo.getReason();
                        head2Score = gradeInfo.getScore();
                    }
                }
                if (gradeInfo.getGraderJobName().equals("科长") || (gradeInfo.getGraderJobName().equals("副院长2") && gradeInfo.getCentRole().equals("信息科负责人"))) {
                    sectionReason = gradeInfo.getReason();
                    sectionScore = gradeInfo.getScore();
                }

                if (gradeInfo.getGraderJobName().equals("组长")) {
                    teamReason = gradeInfo.getReason();
                    teamScore = gradeInfo.getScore();
                }
            }
            GradeResultDTO gradeResultDTO = GradeResultDTO.builder().staffId(item.getStaffId()).staffName(item.getStaffName()).section(item.getSection()).team(item.getTeam())
                    .head0Reason(head0Reason)
                    .head0Score(head0Score)
                    .headScoreFen(headScoreFen)
                    .headReasonFen(headReasonFen)
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
        int i = 1;
        for (var item : gradeResultDTOList
        ) {
            item.setId(i);
            i++;
        }
        System.out.println(gradeResultDTOList);
        return gradeResultDTOList;
    }

    //新建一个DTO用来接收前台传来的包含角色的打分
    @CheckTokenAndRole
    @PostMapping("/saveGradeItem")
    public String saveGradeItem(@RequestBody SaveGradeItemDTO saveGradeItemDTO, HttpServletRequest request) {
        log.info("*******centRole{}", saveGradeItemDTO);
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        GradeInfo gradeInfo = saveGradeItemDTO.getGradeInfo();
        gradeInfo.setGraderName(userService.getOne(userQueryWrapper).getUserName());
        gradeInfo.setGraderId(userId);
        gradeInfo.setGraderJobName(userService.getOne(userQueryWrapper).getJobName());
        gradeInfo.setCentRole(saveGradeItemDTO.getCentRole());
        QueryWrapper<GradeInfo> gradeInfoQueryWrapper = new QueryWrapper<>();
        gradeInfoQueryWrapper.eq("for_grader_id", gradeInfo.getForGraderId())
                .eq("year", gradeInfo.getYear())
                .eq("month", gradeInfo.getMonth())
                .eq("grader_id", userId)
                .eq("cent_role", saveGradeItemDTO.getCentRole())
                .eq("grader_job_name", gradeInfo.getGraderJobName());
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

    /**
     * 手机端展示
     * @param year
     * @param month
     * @param request
     * @return
     */
    @CheckTokenAndRole
    @GetMapping("/getGradeMobile")
    public List<GradeInfo> getGradeMobile(@RequestParam int year, @RequestParam int month, HttpServletRequest request) {

        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        log.info("year+month+Claims" + year + month + claims);
        List<GradeInfo> gradeInfoList = new ArrayList<>();
        QueryWrapper<GradeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("for_grader_id", userId).eq("year", year).eq("month", month);

        return gradeInfoService.list(queryWrapper);
    }


}

