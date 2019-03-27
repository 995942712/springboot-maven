package com.ming.shiro.dao.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.security.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Set;

/**
 * 角色管理Dao
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
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 按id查询角色消息
     * @param id
     * @return
     */
    Role selectRoleById(@Param("id") Long id);

    /**
     * 保存角色菜单
     * @param id
     * @param modules
     */
    void saveRoleModules(@Param("roleId") Long id, @Param("modules") Set<Module> modules);

    /**
     * 删除角色菜单
     * @param roleId
     */
    void dropRoleModules(@Param("roleId") Long roleId);

    /**
     * 删除角色用户
     * @param roleId
     */
    void dropRoleUsers(@Param("roleId") Long roleId);

}