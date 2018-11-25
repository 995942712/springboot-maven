package com.ming.cache.service;

import com.ming.cache.dao.UserDao;
import com.ming.cache.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.Transient;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> fingAll() {
        return this.userDao.fingAll();
    }

    @Transient
    @Override
    public boolean addUser(User user){
        int count = this.userDao.addUser(user);
        return count == 1 ? true : false;
    }

}