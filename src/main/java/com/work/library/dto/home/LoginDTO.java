package com.work.library.dto.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Administrator
 * @Description 注册参数
 * @Date 2021/1/6 17:51
 */
@Data
public class LoginDTO {

    private String username;

    private String password;
}
