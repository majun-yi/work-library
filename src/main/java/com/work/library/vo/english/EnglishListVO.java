package com.work.library.vo.english;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 * @Descriptio0n 列表出参
 * @Date 2021/1/13 16:16
 */
@Data
@ApiModel("列表-出参")
public class EnglishListVO {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty(value = "单词", required = true)
    private String keyword;

    @ApiModelProperty(value = "译文", required = true)
    private String chinese;

    @ApiModelProperty("是否收藏")
    private Boolean favorite;

    public static EnglishListVO newInstance() {
        return new EnglishListVO();
    }
}
