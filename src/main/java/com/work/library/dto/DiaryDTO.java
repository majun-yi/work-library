package com.work.library.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Date 2021/1/19 18:54
 */
@Data
public class DiaryDTO {
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 正文
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;

}
