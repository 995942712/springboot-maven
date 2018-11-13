package com.ming.jpa.user.service;

import com.ming.jpa.user.domain.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

}