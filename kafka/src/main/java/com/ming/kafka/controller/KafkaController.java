package com.ming.kafka.controller;

import com.ming.kafka.server.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaController {

    @Autowired
    private Producer producer;

    public String send(){
        producer.sendMessage(new Object());
        return "ok";
    }

}