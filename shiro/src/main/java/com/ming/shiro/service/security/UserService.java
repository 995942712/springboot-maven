package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.shiro.domain.security.Role;
import com.ming.shiro.domain.security.User;
import java.util.Map;
import java.util.Set;

/**
 * 登录账号 Service
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
public interface UserService extends IService<User> {

    /**
     * 按登录名查找用户
     * @param name
     * @return
     */
    User findUserByLoginName(String name);
    /**
     * 按用户ID查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);
    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);
    /**
     * 更新用户
     * @param user
     * @return
     */
    User updateUser(User user);
    /**
     * 保存用户角色
     * @param id
     * @param roleSet
     */
    void saveUserRoles(Long id, Set<Role> roleSet);
    /**
     * 按用户ID删除用户角色
     * @param id
     */
    void dropUserRolesByUserId(Long id);
    /**
     * 删除用户
     * @param user
     */
    void deleteUser(User user);
    /**
     * 用户数
     * @param param
     * @return
     */
    int userCount(String param);
    /**
     * 选择用户菜单计数
     * @return
     */
    Map selectUserModuleCount();

}