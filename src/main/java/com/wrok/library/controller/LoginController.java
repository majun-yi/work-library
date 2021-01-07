package com.wrok.library.controller;

import com.wrok.library.dto.RegisterDTO;
import com.wrok.library.service.ILoginService;
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
@RequestMapping("login/")
public class LoginController {
    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public Boolean register(@RequestBody @Valid RegisterDTO registerDTO) {
        return loginService.register(registerDTO);
    }
}
