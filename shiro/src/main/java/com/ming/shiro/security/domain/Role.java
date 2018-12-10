package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * 角色管理
 */
@Getter
@Setter
public class Role {

    /**ID*/
    private Integer id;
    /**名称*/
    private String name;
    /**编码*/
    private String code;
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