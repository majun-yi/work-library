package com.work.library.config;

import com.work.library.constant.ResultConstant;
import com.work.library.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 * @Description 自定义异常
 * @Date 2021/1/7 12:00
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {
    /**
     * 异常码
     */
    private Integer statusCode;
    /**
     * 异常描述
     */
    private String message;

    /**
     * 业务异常 : 自定义 异常信息
     * 统一 异常码
     */
    public ServiceException(String message) {
        this.statusCode = ResultConstant.FAILURE_CODE;
        this.message = message;
    }

    /**
     * 业务异常 : 自定义异常码 和 异常信息
     */
    public ServiceException(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ServiceException(ExceptionEnum exception) {
        this.statusCode = exception.getCode();
        this.message = exception.getMessage();
    }
}
