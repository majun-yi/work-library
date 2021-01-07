package com.wrok.library.service.impl;

import com.wrok.library.dto.RegisterDTO;
import com.wrok.library.entity.UserEntity;
import com.wrok.library.repository.UserRepository;
import com.wrok.library.service.ILoginService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Date 2021/1/6 17:56
 */
@Service
public class LoginServiceImpl implements ILoginService {

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
    public Boolean login(String username, String password) {
        return null;
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
