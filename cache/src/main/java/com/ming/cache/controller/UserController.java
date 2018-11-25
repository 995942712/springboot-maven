package com.ming.cache.controller;

import com.ming.cache.domain.User;
import com.ming.cache.service.UserService;
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

    @RequestMapping("/add")
    public String add(){
        User user = new User();
        user.setName("zl");
        user.setPassword("123456");
        this.userService.addUser(user);
        return "redirect:/list";
    }

}