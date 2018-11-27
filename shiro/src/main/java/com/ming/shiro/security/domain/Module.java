package com.ming.shiro.security.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * 菜单模块
 */
@Setter
@Getter
public class Module {

    /**ID*/
    private Integer id;
    /**名称*/
    private String name;
    /**编码*/
    private String code;
    /**父级ID*/
    private Integer parentId;
    /**全称*/
    private String path;
    /**URL*/
    private String url;
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