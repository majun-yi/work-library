package com.work.library.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @Description 统一结果返回包装类
 * @Date 2021/1/7 9:46
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object>, WebMvcConfigurer {
    /**
     * 若 标注 @RestController注解,则开启统一封装
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (Objects.requireNonNull(methodParameter.getMethod()).getDeclaringClass().getAnnotation(RestController.class) != null)
            return true;
        return false;
    }

    /**
     * 返回结果前 封装返回值
     */
    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ResultVO.buildResult(result);
    }

    /**
     * 处理 String类型 返回强转失败问题 重写WebMvcConfigurer方法
     *
     * @param converters 转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //添加 json转http相关类型,因统一返回结果为json (ResponseBody或RestController修饰)
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}
