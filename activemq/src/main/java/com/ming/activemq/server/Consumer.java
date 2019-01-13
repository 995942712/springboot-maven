package com.ming.activemq.server;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
public class Consumer {

    /**
     * (单向)使用JmsListener配置消费者监听的队列,其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "myActiveMQ1")
    public void recvMessage(String text){
        System.out.println("myActiveMQ1: " + text);
    }

    /**
     * (双向)使用JmsListener配置消费者监听的队列,SendTo将return回的值,再发送到"out.queue"队列中,其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "myActiveMQ1")
    @SendTo(value = "myActiveMQ2")
    public String recvQueue(String text){
        System.out.println(text + "...");
        return text;
    }

}