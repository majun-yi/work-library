package com.work.library.assebler;

/**
 * @Description 日记转换类
 * @Date 2021/1/19 18:59
 */

import cn.hutool.core.bean.BeanUtil;
import com.work.library.dto.DiaryDTO;
import com.work.library.entity.DiaryEntity;
import com.work.library.vo.DiaryListVO;
import com.work.library.vo.DiaryVO;
import com.work.library.vo.PageVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Component
public class DiaryAssemble {
    /**
     * 转实体
     */
    public DiaryEntity toEntity(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = DiaryEntity.newInstance();
        BeanUtil.copyProperties(diaryDTO, diaryEntity);
        return diaryEntity;
    }

    /**
     * 实体转vo
     */
    public DiaryVO toDiaryVO(DiaryEntity diary) {
        DiaryVO diaryVO = DiaryVO.newInstance();
        BeanUtil.copyProperties(diary, diaryVO);
        return diaryVO;
    }

    /**
     * 转集合vo
     */
    public PageVO<DiaryListVO> toListVO(List<DiaryEntity> diaryEntities) {
        PageVO<DiaryListVO> pageVO = new PageVO<>();
        return pageVO.build(diaryEntities.stream().map(diaryEntity -> {
            DiaryListVO diaryListVO = new DiaryListVO();
            BeanUtil.copyProperties(diaryEntity, diaryListVO);
            return diaryListVO;
        }).collect(Collectors.toList()));
    }
}
