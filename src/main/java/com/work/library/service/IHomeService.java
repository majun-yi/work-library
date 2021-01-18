package com.work.library.service;

import com.work.library.dto.home.RegisterDTO;

/**
 * @author Administrator
 * @Description 登录业务
 * @Date 2021/1/6 17:43
 */
public interface IHomeService {

    /**
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     * @description 登录功能
     */
    String login(String username, String password);

    /**
     * 注册
     */
    Boolean register(RegisterDTO register);

    /**
     * 判断用户名是否被使用
     */
    Boolean existsUsername(String username);
}
