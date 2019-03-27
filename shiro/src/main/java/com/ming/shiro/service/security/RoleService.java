package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.security.Role;
import java.util.List;
import java.util.Set;

/**
 * 角色管理 Service
 * <p>
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
public interface RoleService extends IService<Role> {

    /**
     * 保存角色
     * @param role
     * @return
     */
    Role saveRole(Role role);

    /**
     * 查询全部
     * @return
     */
    List<Role> selectAll();

    /**
     * 按ID获取角色
     * @param id
     * @return
     */
    Role getRoleById(Long id);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     * @param role
     */
    void deleteRole(Role role);

    /**
     * 保存角色菜单
     * @param id
     * @param moduleSet
     */
    void saveRoleModules(Long id, Set<Module> moduleSet);

    /**
     * 删除角色菜单
     * @param id
     */
    void dropRoleMenus(Long id);

    /**
     * 获取角色名称计数
     * @param name
     * @return
     */
    Integer getRoleNameCount(String name);

}