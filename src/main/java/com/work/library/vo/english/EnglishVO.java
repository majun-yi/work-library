package com.work.library.vo.english;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 * @Description 查询出参
 * @Date 2021/1/13 16:13
 */
@Data
@ApiModel("单词-出参")
public class EnglishVO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty(value = "单词", required = true)
    private String keyword;

    @ApiModelProperty(value = "译文", required = true)
    private String chinese;

    @ApiModelProperty("例句")
    private String example;

    @ApiModelProperty("例句 翻译")
    private String exampleChinese;

    @ApiModelProperty("是否收藏")
    private Boolean favorite;

    public static EnglishVO newInstance() {
        return new EnglishVO();
    }
}
