package com.work.library.config;//package com.work.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Description swagger配置类
 * @Date 2021/1/6 17:05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("界面首页")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.work.library.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.getParameterList());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("工作学习库接口文档")
                .description("工作过程中的知识小仓库")
                .contact(new Contact("界面首页", "http://192.168.10.97:8989", null))
                .version("1.0")
                .build();
    }

    private List<Parameter> getParameterList() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        //参数名
        parameters.add(parameterBuilder.name("token")
                .description("请求凭证,按需传")
                // date类型
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                // 是否必须填
                .required(false)
                .build());
        return parameters;
    }
}
