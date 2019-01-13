package com.ming.jms.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    @Value("spring.mail.protocol")
    private String protocol;
    @Value("spring.mail.host")
    private String host;
    @Value("spring.mail.port")
    private String port;
    @Value("spring.mail.username")
    private String username;
    @Value("spring.mail.password")
    private String password;
    private String sendUrl = "2416289468@qq.com";

    public void send() {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", protocol);
        //服务器
        prop.setProperty("mail.smtp.host", host);
        //端口
        prop.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //开启安全协议,使用SSL,企业邮箱必需
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(prop, new MyAuthenricator(username, password));
            session.setDebug(true);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(sendUrl, "test"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("995942712@qq.com"));
            mimeMessage.setSubject("hello");
            mimeMessage.setSentDate(new Date());
            mimeMessage.setText("hello,world!");
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}