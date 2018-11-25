package com.ming.cache.service;

import com.ming.cache.domain.User;
import java.util.List;

public interface UserService {

    List<User> fingAll();
    boolean addUser(User user);

}