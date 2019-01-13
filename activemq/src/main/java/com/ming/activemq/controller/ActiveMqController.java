package com.ming.activemq.controller;

import com.ming.activemq.server.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Destination;

/**
 * ActiveMqController
 */
@RestController
public class ActiveMqController {

    @Autowired
    private Producer producer;

    @RequestMapping("/sendmessage")
    public String sendMessage(){
        Destination destination = new ActiveMQQueue("myActiveMQ1");
        producer.sendMessage(destination, "hello,world!");
        return "ok";
    }

}