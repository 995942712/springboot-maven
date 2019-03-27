package com.ming.shiro.controller.security;

import com.ming.shiro.domain.vo.ModuleVo;
import com.ming.shiro.error.BaseError;
import com.ming.shiro.utils.Constants;
import com.ming.shiro.controller.BaseController;
import com.ming.shiro.error.MyError;
import com.ming.shiro.domain.Result;
import com.ming.shiro.domain.security.User;
import com.ming.shiro.utils.StringUtil;
import com.ming.shiro.utils.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * 登录 Controller
 * <p>
 * ///////////////////////////////////////////////////
 * //                    _ooOoo_                    //
 * //                   o8888888o                   //
 * //                   88" . "88                   //
 * //                   (| -_- |)                   //
 * //                   O\  =  /O                   //
 * //                ____/`---'\____                //
 * //              .'  \\|     |//  `.              //
 * //             /  \\|||  :  |||//  \             //
 * //            /  _||||| -:- |||||-  \            //
 * //            |   | \\\  -  /// |   |            //
 * //            | \_|  ''\---/''  |   |            //
 * //            \  .-\__  `-`  ___/-. /            //
 * //          ___`. .'  /--.--\  `. . __           //
 * //       ."" '<  `.___\_<|>_/___.'  >'"".        //
 * //      | | :  `- \`.;`\ _ /`;.`/ - ` : | |      //
 * //      \  \ `-.   \_ __\ /__ _/   .-` /  /      //
 * // ======`-.____`-.___\_____/___.-`____.-'====== //
 * //                    `=---='                    //
 * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
 * //          佛祖保佑        永无BUG               //
 * // 佛曰:                                         //
 * //        写字楼里写字间,写字间里程序员.            //
 * //        程序人员写程序,又拿程序换酒钱.            //
 * //        酒醒只在网上坐,酒醉还来网下眠.            //
 * //        酒醉酒醒日复日,网上网下年复年.            //
 * //        但愿老死电脑间,不愿鞠躬老板前.            //
 * //        奔驰宝马贵者趣,公交自行程序员.            //
 * //        别人笑我忒疯癫,我笑自己命太贱.            //
 * //        不见满街漂亮妹,哪个归得程序员.            //
 * ///////////////////////////////////////////////////
 */
@Slf4j
@Controller
public class UserController extends BaseController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        log.info("跳到这边的路径为:" + request.getRequestURI());
        Subject s = SecurityUtils.getSubject();
        log.info("是否记住登录--->" + s.isRemembered() + "<-----是否有权限登录----->" + s.isAuthenticated() + "<----");
        if (s.isAuthenticated()) {
            return "redirect:main/index";
        } else {
            return "login";
        }
    }

    /**
     * 登录
     * @param request
     * @param response
     * @return
     * @throws MyError
     */
    @ResponseBody
    @PostMapping(value = "/login/main")
    public Result login(HttpServletRequest request, HttpServletResponse response) throws MyError {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");

        log.info(loginName + "==========" + code + "==========" + rememberMe);
        String url = "";
        if (StringUtil.isBlank(loginName) || StringUtil.isBlank(password)) {
            log.info("用户名或者密码不能为空!");
            return Result.create(BaseError.PARAMETER_VALIDATION_ERROR.toString(), "用户名或者密码不能为空!");
        }
        if (StringUtil.isBlank(code)) {
            log.info("验证码不能为空!");
            return Result.create("500", "验证码不能为空!");
        }
        code = code.trim().toLowerCase();
        HttpSession session = request.getSession();
        if (null == session) {
            log.info("session超时!");
            return Result.create("500", "session超时!");
        }
        String vcode = (String) session.getAttribute(Constants.VALIDATE_CODE);
        if (StringUtil.isBlank(vcode)) {
            log.info("验证码超时!");
            return Result.create("500", "验证码超时!");
        }
        vcode = vcode.trim().toLowerCase();
        if (!vcode.equals(code)) {
            log.info("验证码填写错误!");
            return Result.create("500", "验证码填写错误!");
        }
        //登录验证
        User user = this.userService.findUserByLoginName(loginName);
        if (user == null) {
            log.info("账号不存在!");
            return Result.create("500", "账号不存在!");
        } else {
            if ("1".equals(user.getStatus())) {
                log.info("账号已禁用,请联系管理员!");
                return Result.create("500", "账号已禁用,请联系管理员!");
            } else if ("2".equals(user.getStatus())) {
                log.info("账号已删除,请联系管理员!");
                return Result.create("500", "账号已删除,请联系管理员!");
            }
        }
        //用户登录
        try {
            AuthenticationToken token = new UsernamePasswordToken(loginName, password, Boolean.valueOf(rememberMe));
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("登录成功!");
            if (subject.isAuthenticated()) {
                url = "main/index";
            }
        } catch (Exception e) {
            log.info("登录失败!");
            return Result.create("500", "登录失败");
        }
        return Result.create("200", "登录成功", url);
    }

    /**
     * 退出
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/logout")
    public Result logout() {
        try {
            SecurityUtils.getSubject().logout();
            log.info("退出成功!");
        } catch (Exception e) {
            log.info("退出失败!");
            e.printStackTrace();
        }
        return Result.create("200", "退出成功");
    }

    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        log.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    /***
     * 获得用户所拥有的菜单列表
     * @return
     */
    @GetMapping("/getUserModule")
    @ResponseBody
    public List<ModuleVo> getUserModule(Long id){
        List<ModuleVo> list = this.moduleService.getModuleVoByUser(id);
        return list;
    }

}