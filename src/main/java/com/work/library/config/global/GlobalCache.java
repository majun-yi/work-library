package com.work.library.config.global;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Description 项目暂时未引入redis, 引入一个可设置 过期时间的map工具,对用户的时效性进行验证
 * @Date 2021/1/17 17:57
 */
@Component
public class GlobalCache {
    /**
     * 设置一个全局的缓存中心容器
     */
    static
    ExpiringMap<String, String> map = ExpiringMap.builder()
            //设置最大容量,超出后会挤出第一个数据
            .maxSize(100)
            //超时时间为一天
            .expiration(24, TimeUnit.HOURS)
            //测试超时时间为60秒
//            .expiration(60, TimeUnit.SECONDS)
            //允许更新过期时间,不允许更新过期时间
            .variableExpiration()
            //ACCESSED:在CREATED策略基础上增加 在还没过期时get方法清零过期时间。
            //清零过期时间也就是重置过期时间，重新计算过期时间
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    /**
     * 获取登录凭证
     *
     * @return 登录凭证
     */
    public static String getToken(String key) {
        return map.get(key);
    }

    /**
     * 创建 登录凭证
     */
    public static void initToken(String key, String token) {
        map.put(key, token);
    }

    public static void destroyToken(String key) {
        map.remove(key);
    }

    @PostConstruct
    public void testToken() {
        map.put("testToken", "testToken", 30, TimeUnit.DAYS);
    }
}
