package com.ming.shiro.security.service;

import com.ming.shiro.security.dao.LoginDao;
import com.ming.shiro.security.dao.ModuleDao;
import com.ming.shiro.security.dao.RoleDao;
import com.ming.shiro.security.dao.UserRoleDao;
import com.ming.shiro.security.domain.User;
import com.ming.shiro.security.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录账号Service实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<User> findAll() {
        List<User> list = this.loginDao.findAll();
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<User> findList(User user) {
        List<User> list = this.loginDao.findList(user);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        User user = this.loginDao.findById(id);
        if (null == user) {
            return new User();
        }
        return user;
    }

    @Override
    public User findByLoginName(String loginName) {
        User user = this.loginDao.findByLoginName(loginName);
        if (null == user) {
            return new User();
        }
        return user;
    }

    public List<UserRole> findUserRole(UserRole userRole) {
        return this.userRoleDao.findList(userRole);
    }

    @Override
    public int save(User user) {
        int count = this.loginDao.save(user);
        if (count == 0) {
            return 0;
        }
        return count;
    }

    @Override
    public int update(User user) {
        int count = this.loginDao.update(user);
        if (count == 0) {
            return 0;
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count = this.loginDao.delete(id);
        if (count == 0) {
            return 0;
        }
        return count;
    }

}