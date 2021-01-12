package com.work.library.dto.english;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 * @Description TODO
 * @Date 2021/1/12 18:48
 */
@ApiModel("英语-添加")
@Data
public class EnglishDTO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("单词")
    private String keyword;

    @ApiModelProperty("译文")
    private String chinese;

    @ApiModelProperty("例句")
    private String example;

    @ApiModelProperty("是否收藏")
    private Boolean favorite;
}
