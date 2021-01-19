package com.work.library.controller;

import com.work.library.dto.DiaryDTO;
import com.work.library.service.IDiaryService;
import com.work.library.vo.DiaryListVO;
import com.work.library.vo.DiaryQuery;
import com.work.library.vo.DiaryVO;
import com.work.library.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Administrator
 * @Description 日记相关业务
 * @Date 2021/1/19 18:49
 */
@RestController
@RequestMapping("/diary/")
@Api(tags = "日记管理")
public class DiaryController {

    private final IDiaryService diaryService;

    public DiaryController(IDiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation("添加")
    @PostMapping("")
    public String create(@RequestBody @Valid DiaryDTO diaryDTO) {
        return diaryService.create(diaryDTO);
    }

    @ApiOperation("修改")
    @PutMapping("")
    public String update(@RequestBody @Valid DiaryDTO diaryDTO) {
        return diaryService.update(diaryDTO);
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public DiaryVO get(@PathVariable String id) {
        return diaryService.get(id);
    }

    @ApiOperation("列表")
    @PostMapping("{page}/{size}")
    public PageVO<DiaryListVO> getList(@RequestBody DiaryQuery diaryQuery, @PathVariable Integer page, @PathVariable Integer size) {
        return diaryService.getList(diaryQuery, page, size);
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public Long delete(@PathVariable String id) {
        return diaryService.delete(id);
    }
}
