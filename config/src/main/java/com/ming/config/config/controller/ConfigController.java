package com.ming.config.config.controller;

import com.ming.config.config.domain.Jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigController {

    @Autowired
    private Jdbc jdbc;

    @ResponseBody
    @RequestMapping("/config")
    public String toConfig(){
        //System.out.println(jdbc.toString());
        return jdbc.toString();
    }

}