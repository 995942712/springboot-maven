package com.ming.jdbc.jdbc.controller;

import com.ming.jdbc.jdbc.domain.User;
import com.ming.jdbc.jdbc.service.UserService;
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
        List<User> list = this.userService.fingAll();
        for(User u : list){
            System.out.println(u.toString());
        }
        return list.toString();
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(){
        User user = new User();
        user.setName("zs");
        user.setPassword("123456");
        return this.userService.addUser(user) ? "true" : "false";
    }

}