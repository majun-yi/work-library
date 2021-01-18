package com.work.library.controller;

import com.work.library.dto.english.EnglishDTO;
import com.work.library.service.IEnglishService;
import com.work.library.vo.english.EnglishQuery;
import com.work.library.vo.PageVO;
import com.work.library.vo.english.EnglishListVO;
import com.work.library.vo.english.EnglishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Administrator
 * @Description 英语学习相关
 * @Date 2021/1/12 18:45
 * 采用 restful 的接口命名风格,通过请求方式来区分各接口路由
 * `@postMapping` 新增
 * `@GetMapping` 查询
 * `@PutMapping` 修改
 * `@DeleteMapping` 删除
 */
@RestController
@RequestMapping("english/")
@Api(tags = "英语相关")
public class EnglishController {
    private final IEnglishService englishService;

    public EnglishController(IEnglishService englishService) {
        this.englishService = englishService;
    }

    @ApiOperation("添加")
    @PostMapping("")
    public String create(@RequestBody @Valid EnglishDTO englishDTO) {
        return englishService.create(englishDTO);
    }

    @ApiOperation("修改")
    @PutMapping("")
    public String update(@RequestBody @Valid EnglishDTO englishDTO) {
        return englishService.update(englishDTO);
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public EnglishVO get(@PathVariable String id) {
        return englishService.get(id);
    }

    @ApiOperation("列表")
    @PostMapping("{page}/{size}")
    public PageVO<EnglishListVO> getList(@RequestBody EnglishQuery englishQuery, @PathVariable Integer page, @PathVariable Integer size) {
        return englishService.getList(englishQuery, page, size);
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public Long delete(@PathVariable String id) {
        return englishService.delete(id);
    }

    @ApiOperation("收藏")
    @PutMapping("collection/{id}")
    public String collection(@PathVariable String id) {
        return englishService.collection(id);
    }
}
