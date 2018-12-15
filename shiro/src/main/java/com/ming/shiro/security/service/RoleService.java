package com.ming.shiro.security.service;

import com.ming.shiro.security.domain.Role;
import com.ming.shiro.security.domain.RoleModule;
import com.ming.shiro.security.domain.UserRole;
import java.util.List;

/**
 * 角色管理Service
 */
public interface RoleService {

    /**
     * 查询角色列表
     * @return
     */
    List<Role> findAll();

    /**
     * 模糊查询角色列表
     * @param role
     * @return
     */
    List<Role> findList(Role role);

    /**
     * 模糊查询角色模块列表
     * @param roleModule
     * @return
     */
    List<RoleModule> findRoleModule(RoleModule roleModule);

    /**
     * 查询用户角色列表
     * @param userRole
     * @return
     */
    List<UserRole> findUserRole(UserRole userRole);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    boolean save(Integer moduleId, Role role);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    int delete(Integer id);

}