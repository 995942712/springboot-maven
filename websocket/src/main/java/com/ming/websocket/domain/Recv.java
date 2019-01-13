package com.ming.websocket.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务端返回给浏览器的消息
 */
@Getter
@Setter
public class Recv {

    private String msg;

}