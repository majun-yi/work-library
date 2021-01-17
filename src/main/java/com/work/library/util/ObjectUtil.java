package com.work.library.util;

import com.work.library.entity.UserEntity;

/**
 * @author Administrator
 * @Description 对象-工具封装
 * @Date 2021/1/14 17:24
 */
public class ObjectUtil {


    public static void main(String[] args) {
        UserEntity user = new UserEntity();
        user.setUsername("张三");
        test(user);
        System.out.println(user.getUsername());
    }

    private static void test(UserEntity user) {
        UserEntity user2 = new UserEntity();
        user2.setUsername("李四");
        user = user2;
        user.setUsername("王五");
    }
}
