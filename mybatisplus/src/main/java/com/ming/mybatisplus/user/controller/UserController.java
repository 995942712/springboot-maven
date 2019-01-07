package com.ming.mybatisplus.user.controller;

import com.ming.mybatisplus.user.domain.User;
import com.ming.mybatisplus.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@MapperScan("com.ming.mybatisplus.user.dao")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        List<User> list = this.userService.list();
        for (User user : list) {
            System.out.println(user.toString());
        }
        return "";
    }

    @RequestMapping("/add")
    public String add(){
        User user = new User();
        user.setName("ww");
        user.setPassword("123456");
        this.userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/update")
    public String update(){
        User user = new User();
        user.setId(3);
        user.setName("zl");
        user.setPassword("123456");
        this.userService.saveOrUpdate(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(){
        this.userService.removeById(3);
        return "redirect:/list";
    }

}