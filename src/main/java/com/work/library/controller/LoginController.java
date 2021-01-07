package com.work.library.controller;

import com.work.library.dto.home.LoginDTO;
import com.work.library.dto.home.RegisterDTO;
import com.work.library.service.IHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Administrator
 * @Description 登录中心
 * @Date 2021/1/6 17:21
 */
@Api(tags = "登录中心")
@RestController
@RequestMapping("home/")
public class LoginController {
    private final IHomeService homeService;

    public LoginController(IHomeService homeService) {
        this.homeService = homeService;
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public Boolean register(@RequestBody @Valid RegisterDTO registerDTO) {
        return homeService.register(registerDTO);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Boolean login(@RequestBody @Valid LoginDTO loginDTO) {
        return homeService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
