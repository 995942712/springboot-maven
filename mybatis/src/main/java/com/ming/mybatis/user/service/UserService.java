package com.ming.mybatis.user.service;

import com.ming.mybatis.user.domain.User;
import java.util.List;

public interface UserService {

    List<User> fingAll();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

}