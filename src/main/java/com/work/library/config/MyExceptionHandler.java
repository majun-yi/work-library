package com.work.library.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Description 异常统一处理
 * @Date 2021/1/7 11:06
 */
@ResponseBody
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * http请求参数错误捕捉
     * 捕捉异常为 405,捕捉后的http状态值为后台捕捉,故状态为正常200
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO readable(HttpMessageNotReadableException exception) {
        return ResultVO.buildResult(HttpStatus.METHOD_NOT_ALLOWED.value(), "请检查请求方式是否正确");
    }

    /**
     * 校验参数异常拦截,如:NotNull,NotEmpty 并给出拦截提示信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO valid(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message = null;
        if (null != fieldError)
            message = fieldError.getDefaultMessage();
        return ResultVO.buildResult(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 对非java bean 参数校验的拦截
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleConstraintViol(ConstraintViolationException exception) {
        //接收异常校验集
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        //返回信息
        StringBuilder exceptionMessage = new StringBuilder();
        //
        for (ConstraintViolation<?> violation : violations) {
            exceptionMessage.append(violation.getInvalidValue())
                    //异常码和异常信息通过Tab (4个空格)分割
                    .append("\t")
                    .append(violation.getMessage())
                    //多个异常信息换行
                    .append("\n");
        }
        return ResultVO.buildResult(HttpStatus.BAD_REQUEST.value(), exceptionMessage.toString());
    }

    /**
     * 请求方法与controller的请求方法不一致
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleException(HttpRequestMethodNotSupportedException e) {
        return ResultVO.buildResult(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法错误");
    }

    /**
     * 业务异常 (自定义异常根类)
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleParamException(ServiceException e) {
        return ResultVO.buildResult(e.getStatusCode(), e.getMessage());
    }

    /**
     * 捕获所有
     */
//    @ExceptionHandler({Exception.class})
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO handleException(Exception e) {
//        return ResultVO.buildResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常");
//    }
}
