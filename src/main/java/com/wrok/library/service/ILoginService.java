package com.wrok.library.service;

import com.wrok.library.dto.RegisterDTO;

/**
 * @author Administrator
 * @Description 登录业务
 * @Date 2021/1/6 17:43
 */
public interface ILoginService {
    /**
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     * @description 登录功能
     */
    Boolean login(String username, String password);

    /**
     * 注册
     */
    Boolean register(RegisterDTO register);

    /**
     * 判断用户名是否被使用
     */
    Boolean existsUsername(String username);
}
