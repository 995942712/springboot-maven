package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色模块关联类
 */
@Getter
@Setter
public class RoleModule {

    private Integer roleId;
    private Integer moduleId;

    public RoleModule(Integer roleId, Integer moduleId) {
        this.roleId = roleId;
        this.moduleId = moduleId;
    }

}