package com.ming.activemq.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import javax.jms.Destination;

/**
 * 消息生产者
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmt;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, String message){
        jmt.convertAndSend(destination, message);
    }

    /**
     * 使用JmsListener配置消费者监听的队列,其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "myActiveMQ2")
    public void recvMessage(String text){
        System.out.println("myActiveMQ2: " + text);
    }

}