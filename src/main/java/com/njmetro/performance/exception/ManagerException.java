package com.njmetro.performance.exception;

import org.springframework.http.HttpStatus;

/**
 * 管理员异常
 *
 * @author RCNJTECH
 * @date 2020/4/14 16:53
 */
public class ManagerException extends GlobalException {

    public ManagerException(HttpStatus httpStatus, ErrorEnum errorEnum) {
        super(httpStatus, errorEnum);
    }

}
