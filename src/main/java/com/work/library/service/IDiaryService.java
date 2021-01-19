package com.work.library.service;

import com.work.library.dto.DiaryDTO;
import com.work.library.vo.DiaryListVO;
import com.work.library.vo.DiaryQuery;
import com.work.library.vo.DiaryVO;
import com.work.library.vo.PageVO;

public interface IDiaryService {
    String create(DiaryDTO diaryDTO);

    String update(DiaryDTO diaryDTO);

    DiaryVO get(String id);

    PageVO<DiaryListVO> getList(DiaryQuery diaryQuery, Integer page, Integer size);

    Long delete(String id);
}
