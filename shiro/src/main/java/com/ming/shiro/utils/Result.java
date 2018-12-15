package com.ming.shiro.utils;

import lombok.Getter;

/**
 * 统一返回值类
 * @param <T>
 */
@Getter
public class Result<T> {

    private ResultCodeEnum code;
    private String message;
    private T data;

    public Result() {

    }

    public Result setCode(ResultCodeEnum code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}