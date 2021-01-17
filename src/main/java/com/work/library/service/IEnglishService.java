package com.work.library.service;

import com.work.library.dto.english.EnglishDTO;
import com.work.library.vo.english.EnglishQuery;
import com.work.library.vo.PageVO;
import com.work.library.vo.english.EnglishListVO;
import com.work.library.vo.english.EnglishVO;

/**
 * 英语相关业务
 *
 * @author Administrator
 */
public interface IEnglishService {
    /**
     * 创建
     *
     * @param englishDTO 参数
     * @return 数据主键
     */
    String create(EnglishDTO englishDTO);

    /**
     * 更新
     *
     * @param englishDTO 参数
     * @return 数据主键
     */
    String update(EnglishDTO englishDTO);

    /**
     * 查询 单个数据
     *
     * @param id 主键
     */
    EnglishVO get(String id);

    /**
     * 分页
     *
     * @param englishQuery 查询条件
     * @param page         页码
     * @param size         每页数量
     */
    PageVO<EnglishListVO> getList(EnglishQuery englishQuery, Integer page, Integer size);

    /**
     * 删除
     */
    Long delete(String id);

    /**
     * 收藏
     */
    String collection(String id);
}
