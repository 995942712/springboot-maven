package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.User;
import java.util.List;

public interface LoginDao {

    List<User> findAll();

}