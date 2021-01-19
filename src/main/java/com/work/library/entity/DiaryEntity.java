package com.work.library.entity;

import cn.hutool.core.date.DateUtil;
import com.work.library.vo.DiaryQuery;
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
     * 创建实例
     */
    public static DiaryEntity newInstance() {
        return new DiaryEntity();
    }

    /**
     * 查询条件
     */
    public Query buildQuery(DiaryQuery diaryQuery) {
        Criteria criteria = new Criteria();
        //查询本月的所有数据
        criteria.andOperator(Criteria.where("create_time").gte(DateUtil.beginOfMonth(diaryQuery.getQueryDate())),
                Criteria.where("create_time").lte(DateUtil.endOfMonth(diaryQuery.getQueryDate())));
        return Query.query(criteria);
    }
}
