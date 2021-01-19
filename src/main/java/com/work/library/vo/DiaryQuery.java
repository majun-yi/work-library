package com.work.library.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @Description TODO
 * @Date 2021/1/19 18:55
 */
@ApiModel("查询参数")
@Data
public class DiaryQuery {

    @ApiModelProperty("本月时间")
    private Date queryDate;
}
