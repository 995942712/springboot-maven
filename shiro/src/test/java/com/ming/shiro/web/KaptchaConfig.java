//package com.ming.shiro.web;
//
//import com.google.code.kaptcha.Constants;
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//import com.google.code.kaptcha.util.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.util.Properties;
//
///**
// * 验证码图片样式配置
// */
////@Configuration
//public class KaptchaConfig {
//
////    @Bean(name = "captchaProducer")
//    public DefaultKaptcha getKaptchaBean() {
//        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
//        Properties properties = new Properties();
//        //验证码字符范围
//        properties.setProperty("kaptcha.textproducer.char.string", "23456789");
//        //文字间隔
//        properties.setProperty("kaptcha.textproducer.char.space", "1");
//        //长度
//        properties.setProperty("kaptcha.textproducer.char.length", "4");
//        //字体颜色
//        properties.setProperty("kaptcha.textproducer.font.color", "black");
//        //字体大小
//        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        //字体
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
//        //图片宽度
//        properties.setProperty("kaptcha.image.width", "100");
//        //图片高度
//        properties.setProperty("kaptcha.image.height", "35");
//        //图片边框颜色
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        //session的key
//        //properties.setProperty("kaptcha.session.key", "code");
//        Config config = new Config(properties);
//        defaultKaptcha.setConfig(config);
//        return defaultKaptcha;
//    }
//
//    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
//        String capText = this.getKaptchaBean().createText();
//        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
//        BufferedImage bi = this.getKaptchaBean().createImage(capText);
//        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(bi, "jpg", out);
//        try {
//            out.flush();
//        } finally {
//            out.close();
//        }
//    }
//
//}