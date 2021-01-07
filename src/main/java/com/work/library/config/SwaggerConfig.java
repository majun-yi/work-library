package com.work.library.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 * @Description swagger配置类
 * @Date 2021/1/6 17:05
 */
@EnableOpenApi
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("界面首页")
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.work.library.controller"))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket createLoginApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .groupName("登录中心")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wrok.library.controller.LoginController"))
//                .build().apiInfo(new ApiInfoBuilder()
//                        .title("交易台账")
//                        .build());
//}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("工作学习库接口文档")
                .description("工作过程中的知识小仓库")
                .contact(new Contact("mjy", "http://www.baidu.cn", "732231129@qq.com"))
                .version("1.0")
                .build();
    }
}
