package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 用户角色关联Dao
 */
@Mapper
public interface UserRoleDao {

    /**
     * 查询用户角色列表
     * @return
     */
    List<UserRole> findAll();

    /**
     * 模糊查询用户角色列表
     * @param userRole
     * @return
     */
    List<UserRole> findList(UserRole userRole);

    /**
     * 保存用户角色信息
     * @param userRole
     * @return
     */
    int save(UserRole userRole);

    /**
     * 更新用户角色信息
     * @param userRole
     * @return
     */
    int update(UserRole userRole);

    /**
     * 删除用户角色信息
     * @param userRole
     * @return
     */
    int delete(UserRole userRole);

}