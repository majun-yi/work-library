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
@ApiModel("注册参数")
public class RegisterDTO {

    @ApiModelProperty("用户名1")
    @NotEmpty(message = "请输入用户名")
    private String username;

    @ApiModelProperty("密码1")
    @NotEmpty(message = "请输入密码")
    private String password;
}
