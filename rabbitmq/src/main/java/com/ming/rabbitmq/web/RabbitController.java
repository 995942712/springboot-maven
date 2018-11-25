package com.ming.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RabbitController {

    @Autowired
    private Sender sender;

    @RequestMapping("/hello")
    public void hello() throws Exception {
        sender.send();
    }

}