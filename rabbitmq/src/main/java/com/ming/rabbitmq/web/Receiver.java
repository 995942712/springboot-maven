package com.ming.rabbitmq.web;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}