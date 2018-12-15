package com.ming.shiro.security.service;

import com.ming.shiro.security.dao.ModuleDao;
import com.ming.shiro.security.dao.RoleModuleDao;
import com.ming.shiro.security.domain.Module;
import com.ming.shiro.security.domain.RoleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块管理Service实现类
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private RoleModuleDao roleModuleDao;

    @Override
    public List<Module> findAll() {
        List<Module> list = this.moduleDao.findAll();
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Module> findList(Module module) {
        List<Module> list = this.moduleDao.findList(module);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Module findById(Integer id) {
        Module module = this.moduleDao.findById(id);
        if (null == module) {
            return new Module();
        }
        return module;
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
    public boolean save(Module module) {
        int count = this.moduleDao.save(module);
        if (count == 0) {
            return false;
        }
        return true;
    }

    @Override
    public int update(Module module) {
        int count = this.moduleDao.update(module);
        if (count == 0) {
            return 0;
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        int count = this.moduleDao.delete(id);
        if (count == 0) {
            return 0;
        }
        return count;
    }

}