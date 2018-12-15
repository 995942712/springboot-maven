package com.ming.shiro.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ming.shiro.security.domain.User;
import com.ming.shiro.security.service.LoginService;
import com.ming.shiro.security.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

/**
 * 登录Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private Producer captchaProducer;

    /**
     * 登录
     * @param loginName
     * @param password
     * @param code
     * @param rememberMe
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, @RequestParam("code") String code, @RequestParam("rememberMe") Boolean rememberMe){
        JSONObject json = new JSONObject();
        System.out.println(loginName+"=========="+code+"=========="+rememberMe);
        //
        code = code.trim().toLowerCase();
        if(code == null || code == ""){
            json.put("status", "500");
            json.put("message", "验证码不能为空！");
            return json.toJSONString();
        }
        Session session = SecurityUtils.getSubject().getSession();
        String vcode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!code.equals(vcode)){
            json.put("status", 500);
            json.put("message", "验证码错误！");
            return json.toJSONString();
        }
        //登录验证
        User user = this.loginService.findByLoginName(loginName);
        if (user == null) {
            json.put("status", "500");
            json.put("message", "该用户不存在，请您联系管理员");
            return json.toJSONString();
        } else {
            if ("1".equals(user.getStatus())) {
                json.put("status", "500");
                json.put("message", "该用户已禁用，请您联系管理员");
                return json.toJSONString();
            }else if("2".equals(user.getStatus())){
                json.put("status", "500");
                json.put("message", "该用户已删除，请您联系管理员");
                return json.toJSONString();
            }
        }
        //用户登录
        try {
            AuthenticationToken token = new UsernamePasswordToken(loginName, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            json.put("status", "200");
            json.put("message", "ok");
        } catch (UnknownAccountException uae) {
            json.put("message", "该用户不存在，请您联系管理员");
        } catch (IncorrectCredentialsException ice) {
            json.put("message", "用户名或密码不正确");
        } catch (LockedAccountException lae) {
            json.put("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            json.put("message", "用户名或密码错误次数大于5次,账户已锁定!</br><span style='color:red;font-weight:bold;'>2分钟后可再次登录,或联系管理员解锁</span>");
        } catch (AuthenticationException ae) {
            json.put("message", "用户名或密码不正确");
        } catch (Exception e) {
            json.put("message", "用户登录失败，请您稍后再试");
        }
        return json.toJSONString();
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }

    /**
     * 验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = this.captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = this.captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request){
        List<User> list = this.loginService.findAll();
        System.out.println(list.get(0).getLoginName());
        request.setAttribute("list", list);
        return "security/loginList";
    }

    @RequestMapping(value = "/create")
    public String create(@ModelAttribute User user, HttpServletRequest request) {
        user.setStatus("0");
        user.setCreateId(1);
        user.setCreateName("admin");
        user.setCreateDate(new Date());
        user.setUpdateId(1);
        user.setUpdateName("admin");
        user.setUpdateDate(new Date());
        List<User> list = this.loginService.findAll();
        request.setAttribute("list", list);
        return "security/loginForm";
    }

    @RequestMapping(value = "/save")
    public String save(@RequestParam("roleId") Integer roleId, @Valid User user, BindingResult bindingResult){
        boolean b = this.loginService.save(roleId, user);
        System.out.println(b);
        if (bindingResult.hasErrors()) {
            return "security/loginForm";
        }
        return "redirect:/login/list";
    }

}