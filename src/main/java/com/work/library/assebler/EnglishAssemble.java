package com.work.library.assebler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.work.library.dto.english.EnglishDTO;
import com.work.library.entity.EnglishEntity;
import com.work.library.vo.PageVO;
import com.work.library.vo.english.EnglishListVO;
import com.work.library.vo.english.EnglishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Description 英语相关转换类
 * @Date 2021/1/12 18:58
 */
@Component
public class EnglishAssemble {
    /**
     * 转实体
     */
    public EnglishEntity toEntity(EnglishDTO englishDTO) {
        //创建对象
        EnglishEntity englishEntity = EnglishEntity.newInstance();
        //复制 参数值
        BeanUtil.copyProperties(englishDTO,englishEntity);
        //设置创建实体基本信息并返回
        return englishEntity.buildBase();
    }

    /**
     * @param englishEntity 实体信息
     * @param englishDTO    入参
     */
    public EnglishEntity dto2Entity(EnglishEntity englishEntity, EnglishDTO englishDTO) {
        if (StrUtil.isNotBlank(englishDTO.getKeyword()))
            englishEntity.setKeyword(englishDTO.getKeyword());
        if (StrUtil.isNotBlank(englishDTO.getChinese()))
            englishEntity.setChinese(englishDTO.getChinese());
        if (StrUtil.isNotBlank(englishDTO.getExample()))
            englishEntity.setExample(englishDTO.getExample());
        if (null != englishDTO.getFavorite())
            englishEntity.setFavorite(englishDTO.getFavorite());
        return englishEntity;
    }

    /**
     * 转 视图vo
     *
     * @param englishEntity 实体
     * @return vo
     */
    public EnglishVO toVO(EnglishEntity englishEntity) {
        EnglishVO englishVO = EnglishVO.newInstance();
        BeanUtils.copyProperties(englishEntity, englishVO);
        return englishVO;
    }

    /**
     * 转 列表vo
     */
    public PageVO<EnglishListVO> toListVO(List<EnglishEntity> englishEntities, Long count) {
        PageVO<EnglishListVO> pageVO = new PageVO<>();
        List<EnglishListVO> collect = englishEntities.stream().map(englishEntity -> {
            EnglishListVO englishListVO = EnglishListVO.newInstance();
            BeanUtil.copyProperties(englishEntity, englishListVO);
            return englishListVO;
        }).collect(Collectors.toList());
        return pageVO.build(collect, count);
    }
}
