package com.ming.thymeleaf.user.controller;

import com.ming.thymeleaf.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
public class UserController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(){
        return "user/userList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute User user){
        return "user/userForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/userForm";
        }
        //System.out.println(user.getName()+"==="+user.getPassword());
        return "redirect:/list";
    }

}