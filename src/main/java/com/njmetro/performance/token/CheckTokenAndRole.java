package com.njmetro.performance.token;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckTokenAndRole {
    /**
     * 请求头中是否需要携带令牌
     */
    boolean needTokenInHeader() default true;

    /**
     * 请求参数中是否需要携带令牌
     */
    boolean needTokenInParam() default false;

    /**
     * 访问接口需要什么权限
     */
    int[] needRoleToAccess() default {};
}
