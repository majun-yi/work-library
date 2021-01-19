package com.work.library.controller;

import cn.hutool.core.util.StrUtil;
import com.work.library.config.global.GlobalCache;
import com.work.library.dto.home.LoginDTO;
import com.work.library.dto.home.RegisterDTO;
import com.work.library.entity.UserEntity;
import com.work.library.repository.UserRepository;
import com.work.library.service.IHomeService;
import com.work.library.util.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 * @Description 登录中心
 * @Date 2021/1/6 17:21
 */
@Api(tags = "登录中心")
@RestController
@RequestMapping("home/")
//忽略拦截注解
//@IgnoreAuth(value = true)
public class LoginController {
    private final IHomeService homeService;
    private final UserRepository userRepository;

    public LoginController(IHomeService homeService, UserRepository userRepository) {
        this.homeService = homeService;
        this.userRepository = userRepository;
    }

    @ApiOperation("注册")
    @PostMapping("register")
    @IgnoreAuth(value = true)
    public Boolean register(@RequestBody @Valid RegisterDTO registerDTO) {
        return homeService.register(registerDTO);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    @IgnoreAuth(value = true)
    public String login(@RequestBody @Valid LoginDTO loginDTO) {
        return homeService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @GetMapping("query")
    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    @ApiOperation("验证登录是否有效")
    @GetMapping("login/{token}")
    public Boolean checkLogin(@PathVariable String token) {
        return StrUtil.isNotBlank(GlobalCache.getToken(token)) ? Boolean.TRUE : Boolean.FALSE;
    }

}
