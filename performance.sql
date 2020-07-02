CREATE DATABASE performace;

USE performace;

CREATE TABLE `staff`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `staff_name` varchar(20)  NOT NULL  COMMENT '员工姓名',
    `staff_id` char(6)  NOT NULL  COMMENT '工号',
    `department` varchar(20)  NOT NULL  COMMENT '部门',
    `section` varchar(20)  NOT NULL  COMMENT '科室',
    `group` varchar(20)  NOT NULL  COMMENT '组',
    PRIMARY KEY (`id`)
)COMMENT = '员工信息';

CREATE TABLE `user`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `user_id`  char(6)  NOT NULL  COMMENT '工号',
    `password` char(64)  NOT NULL  COMMENT '密码',
    `role` char(1) NOT NULL  COMMENT '角色',
    `evaluation_scope` varchar(255) NOT NULL COMMENT '打分对象',
    PRIMARY KEY (`id`)
)COMMENT = '用户信息';

CREATE TABLE `score_list`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `year` INT  NOT NULL  COMMENT '年',
    `month` INT  NOT NULL  COMMENT '月',
    `staff_id` char(6)  NOT NULL  COMMENT '工号',
    `group_score` decimal(5,2) NOT NULL COMMENT '组长打分',
    `section_score` decimal(5,2) NOT NULL COMMENT '科长打分',
    `header_score` decimal(5,2) NOT NULL COMMENT '院长打分',
    PRIMARY KEY (`id`)
)COMMENT = '打分表';

CREATE TABLE `menu_list`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `key_menu` char(1)  NOT NULL  COMMENT 'key',
    `index_menu` varchar(10)  NOT NULL  COMMENT '跳转',
    `name` varchar(10)  NOT NULL  COMMENT '名称',
    PRIMARY KEY (`id`)
)COMMENT = '菜单表';

CREATE TABLE `Config`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `year` INT  NOT NULL  COMMENT '年',
    `month` INT  NOT NULL  COMMENT '月',
    PRIMARY KEY (`id`)
)COMMENT = '参数表';

CREATE TABLE `grade_info`
(
    `id` INT  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `year` INT  NOT NULL  COMMENT '年',
    `month` INT  NOT NULL  COMMENT '月',
    `grader_id` char(6)  NOT NULL  COMMENT '打分人工号',
    `grader_name` varchar(10)  NOT NULL  COMMENT '打分人姓名',
    `grader_role` varchar(10)  NOT NULL  COMMENT '打分人角色',
    `for_grader_id` char(6)  NOT NULL  COMMENT '被打分人工号',
    `group_score` decimal(5,2) NOT NULL COMMENT '打分',
    `group_reason` decimal(5,2) NOT NULL COMMENT '打分理由',
    PRIMARY KEY (`id`)
)COMMENT = '打分详情';