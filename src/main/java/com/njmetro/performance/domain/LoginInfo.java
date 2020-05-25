package com.njmetro.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private String token;
    private String name;
    private String userId;
    private String role;
}
