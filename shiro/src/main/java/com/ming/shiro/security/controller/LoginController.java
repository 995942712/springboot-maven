package com.ming.shiro.security.controller;

import com.ming.shiro.security.domain.User;
import com.ming.shiro.utils.Result;
import com.ming.shiro.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/hello")
    public Result hello(){
        return ResultGenerator.genSuccessResult("hello,world");
    }

    @RequestMapping(value = "/")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(){
        System.out.println("ok");
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){

        return "security/loginList";
    }

    @RequestMapping(value = "/create")
    public String create(){
        return "security/loginForm";
    }

    @RequestMapping(value = "/save")
    public String save(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "security/loginForm";
        }
        return "redirect:/list";
    }

}