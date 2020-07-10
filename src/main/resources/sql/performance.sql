/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : performance

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 10/07/2020 17:02:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_point
-- ----------------------------
DROP TABLE IF EXISTS `bonus_point`;
CREATE TABLE `bonus_point`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `team` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属组织',
  `reason` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加分理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加分项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_point
-- ----------------------------
INSERT INTO `bonus_point` VALUES (1, '共性标准', '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生');
INSERT INTO `bonus_point` VALUES (2, '共性标准', '员工在工作中主动提出较合理、有效的工作思路、解决方案等建议或意见');
INSERT INTO `bonus_point` VALUES (3, '共性标准', '员工在工作中解决技术类或管理类难题，提升效率');
INSERT INTO `bonus_point` VALUES (4, '共性标准', '员工在工作中主动提出创新设想（不包括“群策共赢”课题申报），勇于探索实践');
INSERT INTO `bonus_point` VALUES (5, '共性标准', '员工积极承担科室重点工作任务，并较好完成');
INSERT INTO `bonus_point` VALUES (6, '共性标准', '员工较好地完成领导临时交办的工作任务');
INSERT INTO `bonus_point` VALUES (7, '共性标准', '员工以身作则，在科室、班组中发挥了一定的模范榜样作用');
INSERT INTO `bonus_point` VALUES (8, '共性标准', '员工在本科室其他员工离职、调动、请假期间，积极承担他人的工作任务，并较好完成');
INSERT INTO `bonus_point` VALUES (9, '综合科', '员工负责的综合类工作在院、公司层面得到领导肯定好评');
INSERT INTO `bonus_point` VALUES (10, '综合科', '员工积极承担临时性的或新增的综合类的工作，并较好完成');
INSERT INTO `bonus_point` VALUES (11, '计量科', '按照科室每月一练计划开展理论、实作考试得分最高的进行加分');
INSERT INTO `bonus_point` VALUES (12, '计量科', '当月计量工作抽查无差错且表现突出的');
INSERT INTO `bonus_point` VALUES (13, '计量科', '检定组按各项目完成工时排序，收发组按实际收发数量排序，综合组按交办工作完成情况排序，根据排序情况每人');
INSERT INTO `bonus_point` VALUES (14, '信息科', ' 班组单月故障处理量排名前列员工，酌情予以加分');
INSERT INTO `bonus_point` VALUES (15, '信息科', ' 在配合、保障、参与项目或工程工作中有突出表现者，酌情予以加分');
INSERT INTO `bonus_point` VALUES (16, '信息科', '员工主动承担周末、夜间紧急故障处理，酌情予以加分');
INSERT INTO `bonus_point` VALUES (17, '电子科', '根据板件破解，测台搭建，替代品开发，新产品开发四项工作的完成情况，按星级难度标准依序酌情予以加分');
INSERT INTO `bonus_point` VALUES (18, '电子科', '班组员工维修部件原值高于班组平均值，班组故障件修复数量排名前列，班组员工综合维修工时排名前列，酌情予');
INSERT INTO `bonus_point` VALUES (19, '科室负责人', '较好地履行本岗位工作职责，工作中无拖沓、超期现象发生');
INSERT INTO `bonus_point` VALUES (20, '科室负责人', '带领下属员工较好地完成本科室重点工作任务');
INSERT INTO `bonus_point` VALUES (21, '科室负责人', '较好地布置安排并完成领导临时交办的工作任务');
INSERT INTO `bonus_point` VALUES (22, '科室负责人', '在工作中带领科室成员解决技术类或管理类难题，提升效率');
INSERT INTO `bonus_point` VALUES (23, '科室负责人', '在工作中主动提出较合理、有效的工作思路、解决方案等建议或意见');
INSERT INTO `bonus_point` VALUES (24, '科室负责人', '在工作中主动提出创新设想（不包括“群策共赢”课题申报），勇于探索实践');
INSERT INTO `bonus_point` VALUES (25, '科室负责人', '科室职能工作在院、公司层面得到领导肯定好评');
INSERT INTO `bonus_point` VALUES (26, '科室负责人', '科室整体氛围融洽，团队凝聚力较强');
INSERT INTO `bonus_point` VALUES (27, '科室负责人', '关心帮助下属，主动了解下属员工的思想动态，积极开展谈心谈话等思想工作');
INSERT INTO `bonus_point` VALUES (28, '科室负责人', '积极引导科室员工参与配合公司、院组织的各项活动');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `year` int(11) NOT NULL COMMENT '年',
  `month` int(11) NOT NULL COMMENT '月',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 2020, 7);

-- ----------------------------
-- Table structure for grade_info
-- ----------------------------
DROP TABLE IF EXISTS `grade_info`;
CREATE TABLE `grade_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `year` int(11) NOT NULL COMMENT '年',
  `month` int(11) NOT NULL COMMENT '月',
  `grader_id` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打分人工号',
  `grader_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打分人姓名',
  `grader_job_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打分人角色',
  `for_grader_id` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被打分人工号',
  `score` decimal(5, 2) NULL DEFAULT NULL COMMENT '打分',
  `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '打分理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打分详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade_info
-- ----------------------------
INSERT INTO `grade_info` VALUES (7, 2020, 7, '111180', 'zxc', '组长', '117044', 0.00, '1|2|3');
INSERT INTO `grade_info` VALUES (8, 2020, 7, '111180', 'zxc', '组长', '117042', 0.00, '1|2');
INSERT INTO `grade_info` VALUES (9, 2020, 7, '111180', '张成', '组长', '123456', 1.00, '1');
INSERT INTO `grade_info` VALUES (10, 2020, 7, '100215', '刘建飞', '院长', '111180', 3.00, '测试');
INSERT INTO `grade_info` VALUES (11, 2020, 7, '100215', '刘建飞', '院长', '117044', 2.00, NULL);
INSERT INTO `grade_info` VALUES (12, 2020, 7, '100215', '刘建飞', '院长', '117042', 2.00, NULL);
INSERT INTO `grade_info` VALUES (13, 2020, 7, '100215', '刘建飞', '院长', '108815', 2.00, NULL);
INSERT INTO `grade_info` VALUES (14, 2020, 7, '100215', '刘建飞', '院长', '108816', 2.00, NULL);
INSERT INTO `grade_info` VALUES (15, 2020, 7, '100215', '刘建飞', '院长', '101854', 2.00, NULL);
INSERT INTO `grade_info` VALUES (16, 2020, 7, '100215', '刘建飞', '院长', '102050', 2.00, NULL);
INSERT INTO `grade_info` VALUES (17, 2020, 7, '100215', '刘建飞', '院长', '101936', 2.00, NULL);
INSERT INTO `grade_info` VALUES (18, 2020, 7, '100215', '刘建飞', '院长', '102915', 1.00, '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生|员工在工作中解决技术类或管理类难题，提升效率123');
INSERT INTO `grade_info` VALUES (19, 2020, 7, '100215', '刘建飞', '院长', '123456', 2.00, NULL);
INSERT INTO `grade_info` VALUES (20, 2020, 7, '101943', '王星', '科长', '102915', 10.00, '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生');
INSERT INTO `grade_info` VALUES (21, 2020, 7, '101943', '王星', '科长', '101654', 10.00, '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生1111');
INSERT INTO `grade_info` VALUES (22, 2020, 7, '100215', '刘建飞', '院长', '110391', 12.00, '员工在工作中主动提出较合理、有效的工作思路、解决方案等建议或意见');
INSERT INTO `grade_info` VALUES (23, 2020, 7, '100215', '刘建飞', '院长', '109818', 1.00, '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生123');
INSERT INTO `grade_info` VALUES (24, 2020, 7, '100215', '刘建飞', '院长', '107609', 9.00, '员工以身作则，在科室、班组中发挥了一定的模范榜样作用');
INSERT INTO `grade_info` VALUES (25, 2020, 7, '101942', '唐从林', '副院长2', '111180', 2.00, '员工较好地履行本岗位工作职责，工作中无拖沓、超期现象发生111');
INSERT INTO `grade_info` VALUES (26, 2020, 7, '111180', 'zxc', '组长', '107606', 0.00, '1|2|3');

-- ----------------------------
-- Table structure for menu_list
-- ----------------------------
DROP TABLE IF EXISTS `menu_list`;
CREATE TABLE `menu_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `menu_id` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `parent_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_list
-- ----------------------------
INSERT INTO `menu_list` VALUES (8, '0', '首页', 'index', NULL, '0|1');
INSERT INTO `menu_list` VALUES (12, '1', '绩效打分', 'dafen', NULL, '0|1');
INSERT INTO `menu_list` VALUES (13, '3', '配置', 'peizhi', NULL, '0');
INSERT INTO `menu_list` VALUES (14, '4', '登录日志', 'dl', '0', '0|1');
INSERT INTO `menu_list` VALUES (15, '5', '绩效打分', 'ForGradeCurrentMonth', '1', '0|1');
INSERT INTO `menu_list` VALUES (16, '6', '员工列表', 'StaffList', '3', '0');
INSERT INTO `menu_list` VALUES (17, '7', '用户配置', 'StaffConfig', '3', '0');
INSERT INTO `menu_list` VALUES (18, '8', '参数配置', 'Config', '3', '0');
INSERT INTO `menu_list` VALUES (19, '9', '查询打分结果', 'GetGrade', '1', '0|1');

-- ----------------------------
-- Table structure for score_list
-- ----------------------------
DROP TABLE IF EXISTS `score_list`;
CREATE TABLE `score_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `year` int(11) NOT NULL COMMENT '年',
  `month` int(11) NOT NULL COMMENT '月',
  `staff_id` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `group_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '组长打分',
  `group_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组长打分理由',
  `header_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '院长打分（全员）',
  `header_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院长打分理由（全员）',
  `header_charge_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '院长_分管_打分',
  `header_charge_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院长打分（分管）',
  `section_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '科长打分',
  `section_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科长打分理由',
  `admin_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '管理员打分',
  `admin_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绩效管理员打分理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score_list
-- ----------------------------
INSERT INTO `score_list` VALUES (1, 2020, 5, '111180', 1.00, '组长', 1.00, '院长全员', 1.00, '院长分管1zsassdasdasd', 1.00, '科长', 1.00, '绩效管理员');
INSERT INTO `score_list` VALUES (2, 2020, 5, '117044', NULL, NULL, NULL, '测试测试', 1.00, '111111', NULL, NULL, NULL, NULL);
INSERT INTO `score_list` VALUES (3, 2020, 5, '117042', NULL, NULL, NULL, NULL, NULL, '测试2', NULL, NULL, NULL, NULL);
INSERT INTO `score_list` VALUES (7, 2020, 5, '100000', NULL, NULL, NULL, NULL, NULL, '12', NULL, NULL, NULL, NULL);
INSERT INTO `score_list` VALUES (8, 2020, 5, '100001', NULL, NULL, 22.00, NULL, NULL, '1212', NULL, NULL, NULL, NULL);
INSERT INTO `score_list` VALUES (9, 2020, 5, '102915', NULL, NULL, NULL, NULL, NULL, '1212', NULL, NULL, NULL, NULL);
INSERT INTO `score_list` VALUES (10, 2020, 6, '111180', NULL, NULL, NULL, NULL, 111.00, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `staff_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `staff_id` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门',
  `section` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室',
  `team` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '刘建飞', '100215', '电子及信息技术研究院', NULL, NULL);
INSERT INTO `staff` VALUES (2, '隋海美', '100027', '电子及信息技术研究院', NULL, NULL);
INSERT INTO `staff` VALUES (3, '唐从林', '101942', '电子及信息技术研究院', NULL, NULL);
INSERT INTO `staff` VALUES (4, '王星', '101943', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (5, '吴惠婷', '101654', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (6, '王永艳', '102915', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (7, '孙晨晨', '100835', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (8, '黄梅', '115534', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (9, '石垚黎', '101634', '电子及信息技术研究院', '综合科', NULL);
INSERT INTO `staff` VALUES (10, '张成', '111180', '电子及信息技术研究院', '信息科', '开发组');
INSERT INTO `staff` VALUES (11, '任驰', '117044', '电子及信息技术研究院', '信息科', '开发组');
INSERT INTO `staff` VALUES (12, '牟欢', '117042', '电子及信息技术研究院', '信息科', '开发组');
INSERT INTO `staff` VALUES (13, '陈维', '107606', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (14, '徐雅楠', '109818', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (15, '陈泉林 ', '110391', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (16, '魏婷', '112758', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (17, '成祥卉', '107623', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (18, '陈海燕', '117043', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (19, '王宇嘉', '117040', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (20, '杨逍宇', '117039', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (21, '曹梦晗', '117041', '电子及信息技术研究院', '信息科', '项目组');
INSERT INTO `staff` VALUES (22, '张琮瑞', '103754', '电子及信息技术研究院', '信息科', '网络组');
INSERT INTO `staff` VALUES (23, '王蒋蕤', '110387', '电子及信息技术研究院', '信息科', '网络组');
INSERT INTO `staff` VALUES (24, '沙敬文', '116824', '电子及信息技术研究院', '信息科', '网络组');
INSERT INTO `staff` VALUES (25, '解宇晨', '116825', '电子及信息技术研究院', '信息科', '网络组');
INSERT INTO `staff` VALUES (26, '杨红能', '116818', '电子及信息技术研究院', '信息科', '网络组');
INSERT INTO `staff` VALUES (27, '缪凯云', '100854', '电子及信息技术研究院', '信息科', '终端1组');
INSERT INTO `staff` VALUES (28, '仇亚健', '107599', '电子及信息技术研究院', '信息科', '终端1组');
INSERT INTO `staff` VALUES (29, '丁谭谭', '107608', '电子及信息技术研究院', '信息科', '终端1组');
INSERT INTO `staff` VALUES (30, '刘畅', '107611', '电子及信息技术研究院', '信息科', '终端1组');
INSERT INTO `staff` VALUES (31, '张俊捷', '107605', '电子及信息技术研究院', '信息科', '终端2组');
INSERT INTO `staff` VALUES (32, '石高亮', '107600', '电子及信息技术研究院', '信息科', '终端2组');
INSERT INTO `staff` VALUES (33, '张文杰', '116823', '电子及信息技术研究院', '信息科', '终端2组');
INSERT INTO `staff` VALUES (34, '丁泽圆', '116821', '电子及信息技术研究院', '信息科', '终端2组');
INSERT INTO `staff` VALUES (35, '滕智涛', '101944', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (36, '仲济通', '107609', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (37, '童征', '117038', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (38, '曹旻潇', '117046', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (39, '龚运', '117045', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (40, '刘子珺', '110389', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (41, '范鸣飞', '110384', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (42, '张宇', '107620', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (43, '庄典', '116822', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (44, '蔡道浩', '116827', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (45, '陈嘉卉 ', '110388', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (46, '王朝阳', '116826', '电子及信息技术研究院', '信息科', '综合管理及机房组');
INSERT INTO `staff` VALUES (47, '施宪雯', '112784', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (48, '王广龙', '117037', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (49, '周林森', '100154', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (50, '李崇刚', '101945', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (51, '陈诚', '102929', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (52, '谢波', '102002', '电子及信息技术研究院', '信息科', NULL);
INSERT INTO `staff` VALUES (53, '张恒', '101300', '电子及信息技术研究院', '电子科', NULL);
INSERT INTO `staff` VALUES (54, '谈永昌', '108815', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (55, '朱军', '115536', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (56, '张艳林', '108165', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (57, '周玉', '110382', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (58, '季春梅', '108176', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (59, '刘洋', '106607', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (60, '项蓉', '116817', '电子及信息技术研究院', '电子科', '电子维修1班');
INSERT INTO `staff` VALUES (61, '秦涛', '108816', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (62, '谈军', '107612', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (63, '陈欣岩', '116812', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (64, '顾显铭', '116820', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (65, '王超', '116809', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (66, '胡晓健', '116813', '电子及信息技术研究院', '电子科', '电子维修2班');
INSERT INTO `staff` VALUES (67, '林静', '101854', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (68, '许志江', '108152', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (69, '黄梦琦', '108174', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (70, '章程', '110383', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (71, '许洲博', '115535', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (72, '薛聪', '116816', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (73, '王天乐', '117284', '电子及信息技术研究院', '电子科', '电子维修3班');
INSERT INTO `staff` VALUES (74, '赵俊', '104872', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (75, '沈云霄', '101928', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (76, '王嵘', '101637', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (77, '孙清羚', '100488', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (78, '庄乐', '101276', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (79, '陶涛', '103311', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (80, '姜向明', '100437', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (81, '渠针针', '114965', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (82, '聂玉洁', '115130', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (83, '肖强', '117047', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (84, '李铖', '117048', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (85, '王颖', '116814', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (86, '王露露', '116815', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (87, '刘月芳', '116819', '电子及信息技术研究院', '电子科', '电子科');
INSERT INTO `staff` VALUES (88, '陈军兰', '100111', '电子及信息技术研究院', '计量科', '计量科');
INSERT INTO `staff` VALUES (89, '吕华', '101936', '电子及信息技术研究院', '计量科', '综合组');
INSERT INTO `staff` VALUES (90, '陈书群', '108814', '电子及信息技术研究院', '计量科', '综合组');
INSERT INTO `staff` VALUES (91, '夏睿祺', '111094', '电子及信息技术研究院', '计量科', '综合组');
INSERT INTO `staff` VALUES (92, '孙月', '116808', '电子及信息技术研究院', '计量科', '综合组');
INSERT INTO `staff` VALUES (93, '丰雷', '102050', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (94, '胡瑞', '111095', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (95, '牛安琦', '111096', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (96, '蔡宇', '111093', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (97, '冯钰', '111097', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (98, '曹琪', '107667', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (99, '谢宇', '116811', '电子及信息技术研究院', '计量科', '检收组');
INSERT INTO `staff` VALUES (100, '杨剑', '101230', '电子及信息技术研究院', '计量科', '检收组');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `password` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `evaluation_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打分对象',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `job_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111180', '1', '0', '开发组|项目组', 'zxc', '组长');
INSERT INTO `user` VALUES (2, '100215', '1', '1', '信息科|电子科|计量科|综合科', '刘建飞', '院长');
INSERT INTO `user` VALUES (5, '100027', '1', '1', '信息科|电子科|计量科|综合科', '隋海美', '副院长1');
INSERT INTO `user` VALUES (6, '101942', '1', '1', '信息科|电子科|计量科|综合科', '唐从林', '副院长2');
INSERT INTO `user` VALUES (7, '100111', '1', '1', '计量科', '陈军兰', '科长');
INSERT INTO `user` VALUES (8, '101943', '1', '1', '综合科', '王星', '科长');
INSERT INTO `user` VALUES (9, '101300', '1', '1', '电子科', '张恒', '科长');
INSERT INTO `user` VALUES (10, '102915', '1', '0', '信息科|电子科|计量科|综合科', '王永艳', '综合管理员');

SET FOREIGN_KEY_CHECKS = 1;
