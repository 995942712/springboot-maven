package com.ming.shiro.dao.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.shiro.domain.security.Role;
import com.ming.shiro.domain.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.Set;

/**
 * 登录账号Dao
 *
 * ////////////////////////////////////////////////////////
 * //                     _ooOoo_                        //
 * //                    o8888888o                       //
 * //                    88" . "88                       //
 * //                    (| ^_^ |)                       //
 * //                    O\  =  /O                       //
 * //                 ____/`---'\____                    //
 * //               .'  \\|     |//  `.                  //
 * //              /  \\|||  :  |||//  \                 //
 * //             /  _||||| -:- |||||-  \                //
 * //             |   | \\\  -  /// |   |                //
 * //             | \_|  ''\---/''  |   |                //
 * //             \  .-\__  `-`  ___/-. /                //
 * //           ___`. .'  /--.--\  `. . ___              //
 * //         ."" '<  `.___\_<|>_/___.'  >'"".           //
 * //       | | :  `- \`.;`\ _ /`;.`/ - ` : | |          //
 * //       \  \ `-.   \_ __\ /__ _/   .-` /  /          //
 * // ========`-.____`-.___\_____/___.-`____.-'========= //
 * //                      `=---='                       //
 * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
 * //       佛祖保佑       永不宕机     永无BUG            //
 * ////////////////////////////////////////////////////////
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据登录账号查询登录账号信息
     * @param loginName
     * @return
     */
    User findByLoginName(@Param("loginName") String loginName);

    /**
     * 查询账号信息
     * @param map
     * @return
     */
    User selectUserByMap(Map<String, Object> map);

    /**
     * 保存用户角色
     * @param id
     * @param roles
     */
    void saveUserRoles(@Param("userId") Long id, @Param("roleIds") Set<Role> roles);

    /**
     * 按用户ID删除用户角色
     * @param userId
     */
    void dropUserRolesByUserId(@Param("userId") Long userId);

    /**
     * 选择用户菜单计数
     * @return
     */
    Map selectUserModuleCount();

}