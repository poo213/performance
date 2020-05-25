package com.njmetro.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuList {
    private int id;
    private String key_menu;
    private String index_menu;
    private String name;

}
