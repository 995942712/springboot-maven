package com.ming.shiro.security.service;

import com.ming.shiro.security.dao.ModuleDao;
import com.ming.shiro.security.dao.RoleDao;
import com.ming.shiro.security.dao.RoleModuleDao;
import com.ming.shiro.security.dao.UserRoleDao;
import com.ming.shiro.security.domain.Role;
import com.ming.shiro.security.domain.RoleModule;
import com.ming.shiro.security.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 角色管理Service实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleModuleDao roleModuleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public List<Role> findList(Role role) {
        return null;
    }

    @Override
    public List<RoleModule> findRoleModule(RoleModule roleModule) {
        return this.roleModuleDao.findList(roleModule);
    }

    @Override
    public List<UserRole> findUserRole(UserRole userRole) {
        return this.userRoleDao.findList(userRole);
    }

    @Override
    public Role findById(Integer id) {
        return null;
    }

    @Override
    public int save(Role role) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}