package com.ming.websocket.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 浏览器向服务端请求的消息
 */
@Getter
@Setter
public class Send {

    private String msg;

}