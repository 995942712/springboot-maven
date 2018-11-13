package com.ming.jdbc.jdbc.service;

import com.ming.jdbc.jdbc.domain.User;

import java.util.List;

public interface UserService {

    List<User> fingAll();
    boolean addUser(User user);

}