package com.work.library.config;

import com.work.library.constant.ResultConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 * @Description 统一结果封装
 * @Date 2021/1/7 9:52
 */
@Getter
@Setter
public class ResultVO {
    /**
     * 结果数据
     */
    private Object result;
    /**
     * 状态码
     */
    private Integer statusCode;
    /**
     * 状态描述
     */
    private String message;

    /**
     * 封装 状态码和状态信息,使用与结果为null的情况
     */
    public ResultVO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 封装 结果集 和状态信息
     */
    public ResultVO(Object result, Integer statusCode, String message) {
        this.result = result;
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 封装 结果集 和状态信息
     */
    public ResultVO(String result, Integer statusCode, String message) {
        this.result = result;
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 初始 封装构造 成功情况
     */
    public static ResultVO buildResult(Object result) {
        return new ResultVO(result, ResultConstant.SUCCESS_CODE, ResultConstant.SUCCESS_MESSAGE);
    }

    /**
     * 重载 封装构造 失败情况
     */
    public static ResultVO buildResult(Integer statusCode, String message) {
        return new ResultVO(statusCode, message);
    }
}
