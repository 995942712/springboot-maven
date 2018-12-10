package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联
 */
@Getter
@Setter
public class UserRole {

    private Integer userId;
    private Integer roleId;

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}