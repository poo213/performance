package com.njmetro.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njmetro.performance.domain.*;
import com.njmetro.performance.exception.EmployeeException;
import com.njmetro.performance.exception.ErrorEnum;
import com.njmetro.performance.exception.PerformanceException;
import com.njmetro.performance.service.MenuListService;
import com.njmetro.performance.service.SectionGroupService;
import com.njmetro.performance.service.StaffService;
import com.njmetro.performance.service.UserService;
import com.njmetro.performance.token.CheckTokenAndRole;
import com.njmetro.performance.token.JwsToken;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-05-09
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final StaffService staffService;
    private final MenuListService menuListService;

    private final SectionGroupService sectionGroupService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userService.list();
    }

    /**
     * 获取用户打分范围
     *
     * @param userId
     * @return
     */
    @GetMapping("/getCheckedEvaluationScopeList")
    public List<String> getCheckedEvaluationScopeList(@RequestParam String userId) {
        String evaluationScope = userService.getCheckedEvaluationScope(userId);
        String[] checkedEvaluationScopeList = evaluationScope.split("\\|");
        return Arrays.asList(checkedEvaluationScopeList);
    }

    @PostMapping("/UpdateUser")
    public String UpdateUser(@RequestBody @Valid User user) {
        System.out.println("user = " + user);
        if (userService.updateById(user)) {
            return "修改成功";
        } else {
            throw new PerformanceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.SYSTEM_ERROR);
        }

    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid User user) {
        if (userService.save(user)) {
            return "修改成功";
        } else {
            throw new PerformanceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.SYSTEM_ERROR);
        }
    }

    @PostMapping("/Login")
    public LoginInfo Login(@RequestBody User user) {
        User userInfo = userService.checkExist(user.getUserId(), user.getPassword());

        if (userInfo == null) {
            throw new EmployeeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.EMPLOYEE_NOT_FOUND);
        } else {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setRole(userInfo.getRole());
            loginInfo.setUserId(userInfo.getUserId());
            loginInfo.setToken(JwsToken.createPC(userInfo));
            return loginInfo;
        }
    }

    @CheckTokenAndRole
    @GetMapping("/getUserId")
    public String getUserId(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("claims" + claims);
        String userId = (String) claims.get("userId");
        System.out.println("userId" + userId);
        return userId;
    }

    //获取当前登陆人姓名
    @CheckTokenAndRole
    @GetMapping("/getUserName")
    public String getUserName(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = (String) claims.get("userName");
        return userName;
    }

    //获取当前登陆人岗位，前端用于区分科长
    @CheckTokenAndRole
    @GetMapping("/getCurrentUserJobName")
    public String getCurrentUserJobName(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = userService.getOne(queryWrapper);
        return user.getJobName();
    }

    //获取当前登陆人的打分角色字符串
    @CheckTokenAndRole
    @GetMapping("/getEvaluationScopeBranch")
    public String getEvaluationScopeBranch(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = userService.getOne(queryWrapper);
        return user.getEvaluationScopeBranch();
    }

    //获取当前登录人的打分上限
    @CheckTokenAndRole
    @GetMapping("/getCentLimit")
    public CentLimitDTO getCentLimit(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        User user = userService.getUserInfo(userId);
        //获取打分的角色，是给分管打分，给全员打分，作为信息科负责人打分（此处主要是因为有兼职的情况）
        String evaluationScopeBranch = user.getEvaluationScopeBranch();
        CentLimitDTO centLimitDTO = new CentLimitDTO();
        //全员打分上限
        if (evaluationScopeBranch.contains("全员")) {
            if (userId.equals("100027"))//隋副院长面向全员，但是手上的分数是分管科室的分数，主要是因为有党群干事存在
            {
                String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();//此处获取到隋分管的电子和计量
                String[] scope1 = evaluationScope.split("\\|");
                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                //排除中层，取得其分管的人，计算出加分上线
                staffQueryWrapper.ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111").in("section", scope1).or(i -> i.in("team", scope1));
                List<Staff> staffList = staffService.list(staffQueryWrapper);
                BigDecimal headLimit = (new BigDecimal(staffList.size())).multiply(new BigDecimal("0.2"));
                centLimitDTO.setHeadLimit(headLimit);
            }
            //todo 此处留着，以后分管变化后用
//            else if(userId.equals("101942")){
//                String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
//                String[] scope1 = evaluationScope.split("\\|");
//                QueryWrapper<Staff> staffQueryWrapper=new QueryWrapper<>();
//                staffQueryWrapper.ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111").in("section", scope1).or(i -> i.in("team", scope1));
//                List<Staff> staffList=staffService.list(staffQueryWrapper);
//                BigDecimal headLimit =(new BigDecimal(staffList.size())).multiply(new BigDecimal("0.2"));
//                centLimitDTO.setHeadLimit(headLimit);
//            }
            else {
                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                staffQueryWrapper.eq("type", 1);//非中层
                List<Staff> staffList = staffService.list(staffQueryWrapper);
                BigDecimal headLimit = (new BigDecimal(staffList.size())).multiply(new BigDecimal("0.1"));//院长打分上线，一般员工数量乘0.1
                centLimitDTO.setHeadLimit(headLimit);
            }

        }
        //分管打分上限
        if (evaluationScopeBranch.contains("分管")) {
            String evaluationScope = userService.getUserInfo(userId).getEvaluationScope();
            String[] scope1 = evaluationScope.split("\\|");
            QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
            staffQueryWrapper.ne("staff_id", "100215").ne("staff_id", "100027").ne("staff_id", "101300").ne("staff_id", "101944").ne("staff_id", "101943").ne("staff_id", "101942").ne("staff_id", "100111").in("section", scope1).or(i -> i.in("team", scope1));
            List<Staff> staffList = staffService.list(staffQueryWrapper);//获取全部员工，不包含中层
            if (user.getJobName().contains("院长")) {
                BigDecimal fenLimit = (new BigDecimal(staffList.size())).multiply(new BigDecimal("0.2"));
                centLimitDTO.setFenLimit(fenLimit);
            }
            //此处需要减去组长
            if (user.getJobName().equals("组长")) {
                BigDecimal fenLimit = ((new BigDecimal(staffList.size())).subtract(new BigDecimal("1"))).multiply(new BigDecimal("0.3"));
                centLimitDTO.setFenLimit(fenLimit);
            }

        }
        //科长打分权限
        if (evaluationScopeBranch.contains("信息科负责人") || user.getJobName().equals("科长")) {
            if (evaluationScopeBranch.contains("信息科负责人")) {
                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                staffQueryWrapper.eq("section", "信息技术科");
                List<Staff> staffList = staffService.list(staffQueryWrapper);
                //找到信息科下面的小组，计算出小组成员的个数，用科室上限-组长打分权限
                QueryWrapper<SectionGroup> sectionGroupQueryWrapper = new QueryWrapper<>();
                sectionGroupQueryWrapper.eq("section_name", "信息技术科");
                List<SectionGroup> sectionGroupList = sectionGroupService.list(sectionGroupQueryWrapper);
                log.info("科室和组关系{}", sectionGroupList);
                Integer num = 0;
                for (SectionGroup item : sectionGroupList) {
                    QueryWrapper<Staff> staffQueryWrapper1 = new QueryWrapper<>();
                    staffQueryWrapper1.eq("team", item.getGroupName());
                    List<Staff> staffList1 = staffService.list(staffQueryWrapper1);

                    num += staffList1.size() - 1;//把组长本身去掉
                    log.info("组员数量{}", num);
                }
                log.info("组员数量{}", num);
                log.info("信息科成员{}", staffList.size());

                BigDecimal sectionLimit = (new BigDecimal(staffList.size())).multiply(new BigDecimal("0.5")).subtract(((new BigDecimal(num))).multiply(new BigDecimal("0.3")));
                log.info("信息科长打分上限{}", sectionLimit);
                centLimitDTO.setSectionLimit(sectionLimit);
            } else {
                QueryWrapper<SectionGroup> sectionGroupQueryWrapper = new QueryWrapper<>();
                sectionGroupQueryWrapper.eq("section_name", user.getEvaluationScope());
                List<SectionGroup> sectionGroupList = sectionGroupService.list(sectionGroupQueryWrapper);
                //获取当前科室下小组的数量
                Integer groupNum = sectionGroupList.size();
                log.info("科室和组关系{}", sectionGroupList);
                Integer num = 0;
                for (SectionGroup item : sectionGroupList) {
                    QueryWrapper<Staff> staffQueryWrapper1 = new QueryWrapper<>();
                    staffQueryWrapper1.eq("team", item.getGroupName());
                    List<Staff> staffList1 = staffService.list(staffQueryWrapper1);

                    num += staffList1.size();
                }
                log.info("组员数量{}", num);//不包含
                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                staffQueryWrapper.eq("section", user.getEvaluationScope());
                List<Staff> staffList = staffService.list(staffQueryWrapper);
                //把科长自己减去
                BigDecimal sectionLimit = ((new BigDecimal(staffList.size()).subtract(new BigDecimal("1"))).multiply(new BigDecimal("0.5"))).subtract(((new BigDecimal(num)).subtract(new BigDecimal(groupNum))).multiply(new BigDecimal("0.3")));
                centLimitDTO.setSectionLimit(sectionLimit);
            }
        }
        return centLimitDTO;
    }

}

