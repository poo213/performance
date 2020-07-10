package com.njmetro.performance.domain;

import lombok.Data;

import java.util.List;

/**
 * @program: performance
 * @description: tab用数据
 * @author: zc
 * @create: 2020-07-10 15:47
 **/
@Data
public class TabInfo {
    private String key;
    private String label;
    List<CheckBoxInfo> list;
}
