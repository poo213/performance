package com.njmetro.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result
 *
 * @author zc
 * @date 2019/11/21 9:36
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 状态码
     * 200：操作成功
     * 400：操作失败
     * 401：登录状态失效
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;


    private Result(int code, T data) {
        this.code = code;
        this.data = data;
    }


}
