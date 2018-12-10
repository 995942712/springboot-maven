package com.ming.shiro.security.service;

import com.ming.shiro.security.dao.ModuleDao;
import com.ming.shiro.security.dao.RoleModuleDao;
import com.ming.shiro.security.domain.Module;
import com.ming.shiro.security.domain.RoleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return this.moduleDao.findAll();
    }

    @Override
    public List<Module> findList(Module module) {
        return this.moduleDao.findList(module);
    }

    @Override
    public Module findById(Integer id) {
        return this.moduleDao.findById(id);
    }

    @Override
    public List<RoleModule> findRoleModule(RoleModule roleModule) {
        return this.roleModuleDao.findList(roleModule);
    }

    @Override
    public int save(Module module) {
        return this.moduleDao.save(module);
    }

    @Override
    public int update(Module module) {
        return this.moduleDao.update(module);
    }

    @Override
    public int delete(Integer id) {
        return this.moduleDao.delete(id);
    }

}