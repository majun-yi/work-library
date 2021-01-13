package com.work.library.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mongodb.client.result.DeleteResult;
import com.work.library.assebler.EnglishAssemble;
import com.work.library.config.ServiceException;
import com.work.library.dto.english.EnglishDTO;
import com.work.library.entity.EnglishEntity;
import com.work.library.enums.ExceptionEnum;
import com.work.library.service.IEnglishService;
import com.work.library.vo.EnglishQuery;
import com.work.library.vo.PageVO;
import com.work.library.vo.english.EnglishListVO;
import com.work.library.vo.english.EnglishVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @Description 英语相关业务实现
 * @Date 2021/1/12 18:56
 */
@Service
public class EnglishServiceImpl implements IEnglishService {
    private final MongoTemplate template;
    private final EnglishAssemble englishAssemble;

    public EnglishServiceImpl(MongoTemplate template, EnglishAssemble englishAssemble) {
        this.template = template;
        this.englishAssemble = englishAssemble;
    }

    /**
     * 新增单词
     */
    @Override
    @Transactional
    public String create(EnglishDTO englishDTO) {
        //dto转实体,实体尽量不要暴露在对外接口,这里需要dto转换一下
        EnglishEntity englishEntity = englishAssemble.toEntity(englishDTO);
        return template.save(englishEntity).getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String update(EnglishDTO englishDTO) {
        if (StrUtil.isEmpty(englishDTO.getId())) ExceptionEnum.DATA_ID_MISS.throwException();
        EnglishEntity englishEntity = template.findById(englishDTO.getId(), EnglishEntity.class);
        englishEntity = englishAssemble.dto2Entity(englishEntity, englishDTO);
        return template.save(englishEntity).getId();
    }

    @Override
    public EnglishVO get(String id) {
        EnglishEntity englishEntity = template.findById(id, EnglishEntity.class);
        if (null == englishEntity) ExceptionEnum.DATA_IS_NOT_FOUND.throwException();
        return englishAssemble.toVO(englishEntity);
    }

    @Override
    public PageVO<EnglishListVO> getList(EnglishQuery englishQuery, Integer page, Integer size) {
        Query query = EnglishEntity.newInstance().buildQuery(englishQuery);
        long count = template.count(query, EnglishEntity.class);
        List<EnglishEntity> englishEntities = template.find(query.with(PageRequest.of(page - 1, size)), EnglishEntity.class);
        return englishAssemble.toListVO(englishEntities,count);
    }

    @Override
    public Long delete(String id) {
        DeleteResult result = template.remove(Query.query(Criteria.where("id").is(id)), EnglishEntity.class);
        return result.getDeletedCount();
    }
}
