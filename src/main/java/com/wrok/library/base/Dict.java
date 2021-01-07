package com.wrok.library.base;

import lombok.Data;

/**
 * @author Administrator
 * @Description 字典类型
 * @Date 2021/1/6 17:59
 */
@Data
public class Dict {
    /**
     * code值
     */
    private Integer code;
    /**
     * name值
     */
    private String name;

    Dict() {
    }

    public Dict(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
