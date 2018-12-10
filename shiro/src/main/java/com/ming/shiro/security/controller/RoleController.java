package com.ming.shiro.security.controller;

import com.ming.shiro.security.domain.Role;
import com.ming.shiro.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){

        return "security/roleList";
    }

    @RequestMapping(value = "/create")
    public String create(){
        return "security/roleForm";
    }

    @RequestMapping(value = "/save")
    public String save(@Valid Role role, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "security/roleForm";
        }
        return "redirect:/list";
    }

}