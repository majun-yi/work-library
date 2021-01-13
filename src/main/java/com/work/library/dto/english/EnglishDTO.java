package com.work.library.dto.english;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Administrator
 * @Description 单词-入参
 * @Date 2021/1/12 18:48
 */
@ApiModel("英语单词-入参")
@Data
public class EnglishDTO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty(value = "单词", required = true)
    @NotEmpty(message = "请输入单词")
    private String keyword;

    @ApiModelProperty(value = "译文", required = true)
    @NotEmpty(message = "请输入译文")
    private String chinese;

    @ApiModelProperty("例句")
    private String example;

    @ApiModelProperty("例句 翻译")
    private String exampleChinese;

    @ApiModelProperty("是否收藏")
    private Boolean favorite;
}
