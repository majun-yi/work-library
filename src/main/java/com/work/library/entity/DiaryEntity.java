package com.work.library.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

/**
 * @author Administrator
 * @Description 日记实体
 * @Date 2021/1/15 19:24
 */
@Document("spl_biz_diary")
@Data
@Accessors(chain = true)
public class DiaryEntity {
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

    /**
     * 无参构造
     */
    public DiaryEntity() {
    }

    /**
     * 查询条件
     */
    private Query buildQuery() {
        Criteria criteria = new Criteria();
        DiaryEntity entity = new DiaryEntity();

        return Query.query(criteria).with(Sort.by("create_time").descending());
    }
}
