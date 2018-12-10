package com.ming.shiro.utils;

import lombok.Getter;

/**
 * 返回值枚举类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

}