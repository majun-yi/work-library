package com.work.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Description TODO
 * @Date 2021/1/6 17:26
 */
@Api(tags = "首页服务")
@RestController
@RequestMapping("/library/")
public class HomeController {

    @ApiOperation("首页测试")
    @GetMapping("test")
    public String test() {
        return "测试成功!";
    }

}
