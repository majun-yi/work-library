package com.work.library.service.impl;

import com.work.library.config.global.GlobalCache;
import com.work.library.dto.home.RegisterDTO;
import com.work.library.entity.UserEntity;
import com.work.library.enums.ExceptionEnum;
import com.work.library.service.IHomeService;
import com.work.library.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @Description TODO
 * @Date 2021/1/6 17:56
 */
@Service
public class LoginServiceImpl implements IHomeService {

    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public String login(String username, String password) {
        boolean existsByUsername = userRepository.existsByUsername(username);
        //判断用户名是否存在
        if (!existsByUsername) ExceptionEnum.NOT_THIS_USER.throwException();

        //通过账号密码查询
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password).orElse(null);

        //若没有查到用户信息,则返回失败
        if (null == userEntity) ExceptionEnum.LOGIN_FAILURE.throwException();

        //创建模拟token
        GlobalCache.initToken(userEntity.getId(), userEntity.getId());

        return GlobalCache.getToken(userEntity.getId());
    }

    /**
     * 注册功能
     */
    @Override
    public Boolean register(RegisterDTO register) {
        //为了减少大量强引用对象,这里引用null,使用的时候再new
        UserEntity userEntity = null;
        //双重校验
        if (null == register) return false;
        userEntity = new UserEntity();
        userEntity.register(register.getUsername(), register.getPassword());
        UserEntity entity = userRepository.save(userEntity);
        if (null == entity) return false;
        return true;
    }

    /**
     * 用户名是否被使用
     *
     * @param username 注册用户名
     */
    @Override
    public Boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }


}
