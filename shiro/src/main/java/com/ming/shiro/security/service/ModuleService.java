package com.ming.shiro.security.service;

import com.ming.shiro.security.domain.Module;
import com.ming.shiro.security.domain.RoleModule;
import java.util.List;

/**
 * 模块管理Service
 */
public interface ModuleService {

    /**
     * 查询模块列表
     * @return
     */
    List<Module> findAll();

    /**
     * 模糊查询模块列表
     * @param module
     * @return
     */
    List<Module> findList(Module module);

    /**
     * 根据id查询模块信息
     * @param id
     * @return
     */
    Module findById(Integer id);

    /**
     * 模糊查询角色模块列表
     * @param roleModule
     * @return
     */
    List<RoleModule> findRoleModule(RoleModule roleModule);

    /**
     * 保存模块信息
     * @param module
     * @return
     */
    boolean save(Module module);

    /**
     * 更新模块信息
     * @param module
     * @return
     */
    int update(Module module);

    /**
     * 删除模块信息
     * @param id
     * @return
     */
    int delete(Integer id);

}