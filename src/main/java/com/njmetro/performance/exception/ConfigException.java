package com.njmetro.performance.exception;

import org.springframework.http.HttpStatus;

/**
 * 配置异常
 *
 * @author RCNJTECH
 * @date 2020/4/17 15:13
 */
public class ConfigException extends GlobalException {

    public ConfigException(HttpStatus httpStatus, ErrorEnum errorEnum) {
        super(httpStatus, errorEnum);
    }

}
