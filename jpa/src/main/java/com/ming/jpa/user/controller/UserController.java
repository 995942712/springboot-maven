package com.ming.jpa.user.controller;

import com.ming.jpa.user.domain.User;
import com.ming.jpa.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/list")
    public String list(){
        List<User> list = this.userService.findAll();
        for(User user : list){
            System.out.println(user.toString());
        }
        return "";
    }

}