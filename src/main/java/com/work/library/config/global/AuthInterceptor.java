package com.work.library.config.global;

import cn.hutool.core.util.StrUtil;
import com.work.library.enums.ExceptionEnum;
import com.work.library.util.IgnoreAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @Description 登录认证拦截
 * @Date 2021/1/17 18:34
 */
public class AuthInterceptor<T> implements HandlerInterceptor {

    /**
     * 拦截所有的请求
     *
     * @param request  请求参数
     * @param response 响应
     * @param handler  处理
     * @return
     * @throws ServletException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //返回false拦截,true不拦截

        //判断是否需要拦截
        return this.getIgnoreInterceptor(request, handler);
    }

    /**
     * 不拦截应用 或 场景 -> 添加 @IgnoreAuth 注解的不拦截
     * 1.登录,注册 (无登录令牌)
     * 2.登录令牌未过期的,不进行拦截
     */
    public boolean getIgnoreInterceptor(HttpServletRequest request, Object handler) {
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod)
            method = (HandlerMethod) handler;
        IgnoreAuth annotation;
        if (null != method)
            annotation = method.getMethodAnnotation(IgnoreAuth.class);
        else  //返回false拦截,true不拦截
            annotation = handler.getClass().getAnnotation(IgnoreAuth.class);

        //若添加忽略拦截注解,可提前返回,无需校验令牌
        if (null != annotation) return true;

        //验证 登录是否有效
        if (this.checkLogin(request)) return true;
        return false;
    }

    /**
     * 验证登录是否有效
     */
    private boolean checkLogin(HttpServletRequest request) {
        //验证登录状态,获取请求头中的登录令牌
        String token = request.getHeader("token");
        String result = GlobalCache.getToken(token);

        //若不存在,则抛出 登录失效 异常
        if (StrUtil.isEmpty(result)) ExceptionEnum.LOGIN_EXPIRE.throwException();

        //若存在, 则不进行拦截操作
        return true;
    }
}
