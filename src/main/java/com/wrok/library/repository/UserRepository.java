package com.wrok.library.repository;

import com.wrok.library.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Administrator
 * 用户仓储
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {
    /**
     * 通过用户名和密码查询用户
     */
    UserEntity findByUsernameAndPasswordAndStatus_Code(String username, String password, Integer statusCode);

    /**
     * 查询用户名是否重复
     */
    boolean existsByUsername(String username);
}
