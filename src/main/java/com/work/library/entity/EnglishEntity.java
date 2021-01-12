package com.work.library.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Administrator
 * @Description 英语单词相关实体
 * @Date 2021/1/12 18:59
 */
@Document("spl_biz_english")
@Data
public class EnglishEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 单词
     */
    private String keyword;
    /**
     * 译文
     */
    private String chinese;
    /**
     * 例句
     */
    private String example;
    /**
     * 是否收藏
     */
    private Boolean favorite;
    /**
     * 出现次数
     */
    private Integer totalCount;
    /**
     * 错误次数
     */
    private Integer mistakeCount;
}
