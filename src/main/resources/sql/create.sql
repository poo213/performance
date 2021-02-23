### 创建数据库

-- CREATE DATABASE evaluation;

### 创建表

USE performance;
# 使用 evaluation 数据库

# 1. 科室和组对应关系 section_group

CREATE TABLE IF NOT EXISTS section_group
(
    id            INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    section_name        VARCHAR(20)  NOT NULL COMMENT '科室',
    group_name          VARCHAR(20)  NOT NULL COMMENT '组',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;