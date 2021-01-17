package com.work.library.entity;

import com.work.library.constant.NumberConstant;
import com.work.library.vo.english.EnglishQuery;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

/**
 * @author Administrator
 * @Description 英语单词相关实体 聚合根:相关业务聚合
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
     * 例句 翻译
     */
    private String exampleChinese;
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
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 无参构造
     */
    public EnglishEntity() {
    }

    /**
     * 初始化构造
     */
    public static EnglishEntity newInstance() {
        return new EnglishEntity();
    }

    /**
     * 创建实体基本参数
     */
    public EnglishEntity buildBase() {
        //默认不收藏
        this.favorite = Boolean.FALSE;
        this.totalCount = NumberConstant.ZERO;
        this.mistakeCount = NumberConstant.ZERO;
        this.createTime = new Date();
        return this;
    }

    /**
     * 创建查询
     *
     * @return
     */
    public Query buildQuery(EnglishQuery englishQuery) {
        Criteria criteria = new Criteria();

        return Query.query(criteria).with(Sort.by("create_time").descending());
    }

    /**
     * +1 计数
     */
    public void counter() {
        this.totalCount = (null == totalCount) ? 1 : totalCount++;
    }
}
