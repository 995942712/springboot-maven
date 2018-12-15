package com.ming.shiro.security.controller;

import com.ming.shiro.security.domain.Module;
import com.ming.shiro.security.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request){
        List<Module> list = this.moduleService.findAll();
        request.setAttribute("list", list);
        return "security/moduleList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute Module module) {
        module.setStatus("0");
        module.setCreateId(1);
        module.setCreateName("admin");
        module.setCreateDate(new Date());
        module.setUpdateId(1);
        module.setUpdateName("admin");
        module.setUpdateDate(new Date());
        return "security/moduleForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Module module, BindingResult bindingResult){
        boolean b = this.moduleService.save(module);
        System.out.println(module.getId());
        if (bindingResult.hasErrors()) {
            return "security/moduleForm";
        }
        return "redirect:/module/list";
    }

}