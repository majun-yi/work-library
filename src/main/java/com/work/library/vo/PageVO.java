package com.work.library.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @Description 分页-包装类,对外统一包装-引用泛型
 * @Date 2021/1/13 16:17
 */
@Data
@ApiModel("分页-包装类")
public class PageVO<T> {

    @ApiModelProperty("结果")
    private List<T> result;

    @ApiModelProperty("总条数")
    private Long count;

    public PageVO<T> build(List<T> list, long count) {
        this.result = list;
        this.count = count;
        return this;
    }
}
