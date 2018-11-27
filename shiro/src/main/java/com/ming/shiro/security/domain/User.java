package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户登录
 */
@Getter
@Setter
public class User {

    /**ID*/
    private Integer id;
    /**登录名*/
    private String loginName;
    /**密码*/
    private String password;
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

    private Set<Role> roles = new HashSet<>();

}