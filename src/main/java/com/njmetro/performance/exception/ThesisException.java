package com.njmetro.performance.exception;

import org.springframework.http.HttpStatus;

/**
 * 论文异常
 *
 * @author RCNJTECH
 * @date 2020/4/17 15:13
 */
public class ThesisException extends GlobalException {

    public ThesisException(HttpStatus httpStatus, ErrorEnum errorEnum) {
        super(httpStatus, errorEnum);
    }

}
