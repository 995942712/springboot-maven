package com.ming.jdbc.jdbc.dao;

import com.ming.jdbc.jdbc.domain.User;

import java.util.List;

public interface UserDao {

    List<User> fingAll();

    int addUser(User user);

}