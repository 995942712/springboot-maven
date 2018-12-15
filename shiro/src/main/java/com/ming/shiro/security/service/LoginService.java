package com.ming.shiro.security.service;

import com.ming.shiro.security.domain.User;
import com.ming.shiro.security.domain.UserRole;
import java.util.List;

/**
 * 登录账号Service
 */
public interface LoginService {

    /**
     * 查询登录账号列表
     * @return
     */
    List<User> findAll();

    /**
     * 模糊查询登录账号列表
     * @param user
     * @return
     */
    List<User> findList(User user);

    /**
     * 根据id查询登录账号信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据登录账号查询登录账号信息
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 查询用户角色列表
     * @param userRole
     * @return
     */
    List<UserRole> findUserRole(UserRole userRole);

    /**
     * 保存登录账号信息
     * @param user
     * @return
     */
    boolean save(Integer roleId, User user);

    /**
     * 更新登录账号信息
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 删除登录账号信息
     * @param id
     * @return
     */
    int delete(Integer id);

}