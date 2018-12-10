package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.RoleModule;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 角色模块关联Dao
 */
@Mapper
public interface RoleModuleDao {

    /**
     * 查询角色模块列表
     * @return
     */
    List<RoleModule> findAll();

    /**
     * 模糊查询角色模块列表
     * @param roleModule
     * @return
     */
    List<RoleModule> findList(RoleModule roleModule);

    /**
     * 保存角色模块信息
     * @param roleModule
     * @return
     */
    int save(RoleModule roleModule);

    /**
     * 更新角色模块信息
     * @param roleModule
     * @return
     */
    int update(RoleModule roleModule);

    /**
     * 删除角色模块信息
     * @param roleModule
     * @return
     */
    int delete(RoleModule roleModule);

}