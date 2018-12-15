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

import java.util.ArrayList;
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
        List<Role> list = this.roleDao.findAll();
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Role> findList(Role role) {
        List<Role> list = this.roleDao.findList(role);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<RoleModule> findRoleModule(RoleModule roleModule) {
        List<RoleModule> list = this.roleModuleDao.findList(roleModule);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<UserRole> findUserRole(UserRole userRole) {
        List<UserRole> list = this.userRoleDao.findList(userRole);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Role findById(Integer id) {
        Role role = this.roleDao.findById(id);
        if (null == role) {
            return new Role();
        }
        return role;
    }

    @Override
    public boolean save(Integer moduleId, Role role) {
        if (null == role) {
            return false;
        }
        int count = this.roleDao.save(role);
        if (count == 0 || null == role.getId() || null == moduleId) {
            return false;
        }
        RoleModule roleModule = new RoleModule(role.getId(), moduleId);
        count = this.roleModuleDao.save(roleModule);
        if (count == 0) {
            return false;
        }
        return true;
    }

    @Override
    public int update(Role role) {
        int count = this.roleDao.update(role);
        if (count == 0) {
            return 0;
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count = this.roleDao.delete(id);
        if (count == 0) {
            return 0;
        }
        return count;
    }

}