package com.ming.jpa.user.service;

import com.ming.jpa.user.dao.UserDao;
import com.ming.jpa.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        List<User> list = this.userDao.findAll();
        return list.size() == 0 ? new ArrayList<>() : list;
    }

}