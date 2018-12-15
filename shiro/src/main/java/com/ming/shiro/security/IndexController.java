package com.ming.shiro.security;

import com.ming.shiro.utils.Result;
import com.ming.shiro.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/hello")
    public Result hello(){
        return ResultGenerator.genSuccessResult("hello,world");
    }

    @RequestMapping(value = "/")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

}