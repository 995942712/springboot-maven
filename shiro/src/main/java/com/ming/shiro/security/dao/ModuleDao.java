package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 模块管理Dao
 */
@Mapper
public interface ModuleDao {

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
     * 根据id查询模块
     * @param id
     * @return
     */
    Module findById(@Param("id") Integer id);

    /**
     * 保存模块信息
     * @param module
     * @return
     */
    int save(Module module);

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
    int delete(@Param("id") Integer id);

}