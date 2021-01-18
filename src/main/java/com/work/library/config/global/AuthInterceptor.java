package com.work.library.config.global;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.work.library.controller.LoginController;
import com.work.library.enums.ExceptionEnum;
import com.work.library.util.IgnoreAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
        // 获取忽略 拦截处理代码
        // 拦截处理代码
//        HandlerMethod method = (HandlerMethod) handler;
//        IgnoreAuth loginRequired = method.getMethodAnnotation(IgnoreAuth.class);
//        if (null != loginRequired) return false;
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod)
            method = (HandlerMethod) handler;
        IgnoreAuth annotation;
        if (null != method)
            annotation = method.getMethodAnnotation(IgnoreAuth.class);
        else  //返回false拦截,true不拦截
            annotation = handler.getClass().getAnnotation(IgnoreAuth.class);

        if (null != annotation) return true;

        //验证登录状态
        String token = request.getHeader("token");
        String result = GlobalCache.getToken(token);
        //若不存在,则抛出 登录失效 异常
        if (StrUtil.isEmpty(result)) ExceptionEnum.LOGIN_EXPIRE.throwException();
        //若存在, 则不进行拦截操作
        if (StrUtil.isNotBlank(result)) return true;
        //否则拦截所有的请求
        return false;
    }

    /**
     * 获取类 和 方法上的 忽略注解
     */
    public List<IgnoreAuth> getAnnounceList(Object handler) {
        List<IgnoreAuth> list = new ArrayList<>();
        HandlerMethod method = (HandlerMethod) handler;
        IgnoreAuth methodAnnotation = method.getMethodAnnotation(IgnoreAuth.class);
        if (null != methodAnnotation) list.add(methodAnnotation);

        IgnoreAuth clazzAnnotation = handler.getClass().getAnnotation(IgnoreAuth.class);
        if (null != clazzAnnotation) list.add(clazzAnnotation);

        return list;
    }
}
