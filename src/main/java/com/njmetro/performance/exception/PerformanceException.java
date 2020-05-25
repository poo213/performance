package com.njmetro.performance.exception;
import org.springframework.http.HttpStatus;

public class PerformanceException extends GlobalException  {
    public PerformanceException(HttpStatus httpStatus, ErrorEnum errorEnum) {
        super(httpStatus, errorEnum);
    }
}
