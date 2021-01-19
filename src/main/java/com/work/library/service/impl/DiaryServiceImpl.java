package com.work.library.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.work.library.assebler.DiaryAssemble;
import com.work.library.dto.DiaryDTO;
import com.work.library.entity.DiaryEntity;
import com.work.library.enums.ExceptionEnum;
import com.work.library.service.IDiaryService;
import com.work.library.vo.DiaryListVO;
import com.work.library.vo.DiaryQuery;
import com.work.library.vo.DiaryVO;
import com.work.library.vo.PageVO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @Description 日记相关实现
 * @Date 2021/1/19 18:55
 */
@Service
public class DiaryServiceImpl implements IDiaryService {
    private final MongoTemplate mongoTemplate;
    private final DiaryAssemble diaryAssemble;

    public DiaryServiceImpl(MongoTemplate mongoTemplate, DiaryAssemble diaryAssemble) {
        this.mongoTemplate = mongoTemplate;
        this.diaryAssemble = diaryAssemble;
    }

    /**
     * 添加
     */
    @Override
    public String create(DiaryDTO diaryDTO) {
        //dto 转换 实体
        DiaryEntity diaryEntity = diaryAssemble.toEntity(diaryDTO);

        diaryEntity = mongoTemplate.save(diaryEntity);
        return diaryEntity.getId();
    }

    /**
     * 修改
     */
    @Override
    public String update(DiaryDTO diaryDTO) {
        return null;
    }

    /**
     * 查询
     */
    @Override
    public DiaryVO get(String id) {
        DiaryEntity diary = this.getDiary(id);
        return diaryAssemble.toDiaryVO(diary);
    }

    /**
     * 分页
     */
    @Override
    public PageVO<DiaryListVO> getList(DiaryQuery diaryQuery, Integer page, Integer size) {
        DiaryEntity diaryEntity = DiaryEntity.newInstance();

        Query query = diaryEntity.buildQuery(diaryQuery);

        List<DiaryEntity> diaryEntities = mongoTemplate.find(query, DiaryEntity.class);

        return diaryAssemble.toListVO(diaryEntities);
    }

    /**
     * 删除
     */
    @Override
    public Long delete(String id) {
        DiaryEntity diary = this.getDiary(id);
        DeleteResult remove = mongoTemplate.remove(diary);
        return remove.getDeletedCount();
    }

    /**
     * 查询
     */
    private DiaryEntity getDiary(String id) {
        DiaryEntity diaryEntity = mongoTemplate.findById(id, DiaryEntity.class);
        if (null == diaryEntity) ExceptionEnum.DATA_IS_NOT_FOUND.throwException();
        return diaryEntity;
    }
}
