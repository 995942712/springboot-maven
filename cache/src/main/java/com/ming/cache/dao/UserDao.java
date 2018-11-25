package com.ming.cache.dao;

import com.ming.cache.domain.User;
import java.util.List;

public interface UserDao {

    List<User> fingAll();

    int addUser(User user);

}