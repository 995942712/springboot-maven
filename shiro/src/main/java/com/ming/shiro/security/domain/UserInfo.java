package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * 用户信息
 */
@Getter
@Setter
public class UserInfo {

    /**ID*/
    private Integer id;
    /**名称*/
    private String name;
    /**登录ID*/
    private Integer userId;
    /**创建者ID*/
    private Integer createId;
    /**创建者名称*/
    private String createName;
    /**创建时间*/
    private Date createDate;
    /**更新者ID*/
    private Integer updateId;
    /**更新者名称*/
    private String updateName;
    /**更新时间*/
    private Date updateDate;
    /**状态*/
    private String status;
    /**备注*/
    private String remark;

}