package com.work.library.controller;

import com.work.library.dto.english.EnglishDTO;
import com.work.library.service.IEnglishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Description 英语学习相关
 * @Date 2021/1/12 18:45
 */
@RestController
@RequestMapping("english/")
@Api("英语相关")
public class EnglishController {
    private final IEnglishService englishService;

    public EnglishController(IEnglishService englishService) {
        this.englishService = englishService;
    }

    @ApiOperation("添加")
    @PostMapping("")
    public String create(@RequestBody EnglishDTO englishDTO) {
        return englishService.create(englishDTO);
    }
}
