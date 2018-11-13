package com.ming.jdbc.jdbc.service;

import com.ming.jdbc.jdbc.dao.UserDao;
import com.ming.jdbc.jdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> fingAll() {
        return this.userDao.fingAll();
    }

    @Override
    public boolean addUser(User user){
        int count = this.userDao.addUser(user);
        return count == 1 ? true : false;
    }

}