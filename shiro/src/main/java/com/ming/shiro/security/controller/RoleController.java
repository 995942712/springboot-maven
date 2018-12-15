package com.ming.shiro.security.controller;

import com.ming.shiro.security.domain.Module;
import com.ming.shiro.security.domain.Role;
import com.ming.shiro.security.service.ModuleService;
import com.ming.shiro.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request){
        List<Role> list = this.roleService.findAll();
        request.setAttribute("list", list);
        return "security/roleList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute Role role, HttpServletRequest request) {
        role.setStatus("0");
        role.setCreateId(1);
        role.setCreateName("admin");
        role.setCreateDate(new Date());
        role.setUpdateId(1);
        role.setUpdateName("admin");
        role.setUpdateDate(new Date());
        List<Module> list = this.moduleService.findAll();
        request.setAttribute("list", list);
        return "security/roleForm";
    }

    @RequestMapping(value = "/save")
    public String save(@RequestParam("moduleId") Integer moduleId, @Valid Role role, BindingResult bindingResult){
        boolean b = this.roleService.save(moduleId, role);
        System.out.println(b);
        if (bindingResult.hasErrors()) {
            return "security/roleForm";
        }
        return "redirect:/role/list";
    }

}