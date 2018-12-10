package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 角色管理Dao
 */
@Mapper
public interface RoleDao {

    /**
     * 角色模块列表
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
     * 根据id查询角色
     * @param id
     * @return
     */
    Role findById(@Param("id") Integer id);

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    int save(Role role);

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
    int delete(@Param("id") Integer id);

}