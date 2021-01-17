package com.work.library.repository;

import com.work.library.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

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
     * 通过用户名和密码查询用户信息
     */
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    /**
     * 查询用户名是否重复 判断是否存在用户
     */
    boolean existsByUsername(String username);


}
