package com.work.library.enums;

import com.work.library.config.ServiceException;
import lombok.Getter;

/**
 * @author Administrator
 * @Description 异常信息枚举
 * @Date 2021/1/13 16:33
 */
@Getter
public enum ExceptionEnum {
    //数据主键丢失
    DATA_ID_MISS(4001, "数据主键丢失,请重试"),
    DATA_CREATE_FAILURE(4002, "糟糕,数据添加失败,请重试"),
    DATA_IS_NOT_FOUND(4003, "数据未找到,请重试"),

    ;
    /**
     * 编码值
     */
    private final Integer code;

    /**
     * 异常信息
     */
    private final String message;

    /**
     * 构造
     */
    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 抛出异常信息
     */
    public void throwException() {
        throw new ServiceException(getCode(), getMessage());
    }

    public ServiceException newInstance() {
        return new ServiceException(code, message);
    }

    /**
     * 静态方法抛出
     */
    public static void throwException(ExceptionEnum status) {
        throw new ServiceException(status.getCode(), status.getMessage());
    }
}
