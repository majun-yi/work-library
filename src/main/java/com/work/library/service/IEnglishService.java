package com.work.library.service;

import com.work.library.dto.english.EnglishDTO;

/**
 * 英语相关业务
 * @author Administrator
 */
public interface IEnglishService {
    /**
     * 创建
     * @param englishDTO
     * @return
     */
    String create(EnglishDTO englishDTO);
}
