package com.work.library.entity;

import com.work.library.base.Dict;
import com.work.library.constant.AccountStatusConstant;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Administrator
 * @Description 用户实体
 * @Date 2021/1/6 17:58
 */
@Data
@Document("spl_biz_user")
public class UserEntity {
    /**
     * 数据主键
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态
     */
    private Dict status;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 注册用户
     */
    public void register(String username, String password) {
        Date currDate = new Date();
        this.username = username;
        this.password = password;
        this.status = AccountStatusConstant.STATUS_NORMAL;
        this.createDate = currDate;
        this.modifyDate = currDate;
    }
}
