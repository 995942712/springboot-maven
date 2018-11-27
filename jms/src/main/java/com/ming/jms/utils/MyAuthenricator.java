package com.ming.jms.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenricator extends Authenticator {

    private String username;
    private String password;

    public MyAuthenricator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

}