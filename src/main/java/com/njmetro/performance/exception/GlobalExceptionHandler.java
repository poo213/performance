package com.njmetro.performance.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 *
 * @author RCNJTECH
 * @date 2020/4/12 12:49
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException() {
        return new ErrorResponse(ErrorEnum.NEED_LOGIN);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public ErrorResponse handleAuthorizationException() {
        return new ErrorResponse(ErrorEnum.ACCESS_DENIED);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(SystemException.class)
    public ErrorResponse handleSystemException() {
        return new ErrorResponse(ErrorEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorResponse());
    }

    @ExceptionHandler(ThesisException.class)
    public ResponseEntity<ErrorResponse> handleThesisException(ThesisException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorResponse());
    }

    @ExceptionHandler(ManagerException.class)
    public ResponseEntity<ErrorResponse> handleManagerException(ManagerException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorResponse());
    }

    @ExceptionHandler(ConfigException.class)
    public ResponseEntity<ErrorResponse> handleConfigException(ConfigException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorResponse());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        System.out.println(fieldErrorList);
        return new ErrorResponse("FormNotCompleted", fieldErrorList.get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolationSet = e.getConstraintViolations();
        ConstraintViolation<?> constraintViolation = constraintViolationSet.stream().findFirst().orElseThrow();
        String message = constraintViolation.getMessage();
        return new ErrorResponse("RequestParamError", message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ErrorResponse("RequestParamError", "缺少请求参数 " + e.getParameterName());
    }

}
