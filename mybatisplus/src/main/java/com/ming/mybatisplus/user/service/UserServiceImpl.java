package com.ming.mybatisplus.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.mybatisplus.user.dao.UserDao;
import com.ming.mybatisplus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}