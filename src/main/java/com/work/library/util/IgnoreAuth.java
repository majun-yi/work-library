package com.work.library.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 * 忽略 鉴权拦截器 注解, 加入此注解 会被拦截器忽略
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAuth {
    /**
     * 默认值为 false,需显示 指定value值
     */
    boolean value() default false;
}
