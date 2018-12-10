package com.ming.shiro.security.dao;

import com.ming.shiro.security.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户信息Dao
 */
@Mapper
public interface UserInfoDao {

    /**
     * 查询用户信息列表
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 模糊查询用户信息列表
     * @param userInfo
     * @return
     */
    List<UserInfo> findList(UserInfo userInfo);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UserInfo findById(@Param("id") Integer id);

    /**
     * 保存用户信息
     * @param userInfo
     * @return
     */
    int save(UserInfo userInfo);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    int update(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);

}