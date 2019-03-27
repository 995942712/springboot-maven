package com.ming.shiro.config;

import com.google.common.collect.Sets;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.security.Role;
import com.ming.shiro.domain.security.User;
import com.ming.shiro.service.security.*;
import com.ming.shiro.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录认证和授权
 *
 * ///////////////////////////////////////////////////
 * //                    _ooOoo_                    //
 * //                   o8888888o                   //
 * //                   88" . "88                   //
 * //                   (| -_- |)                   //
 * //                   O\  =  /O                   //
 * //                ____/`---'\____                //
 * //              .'  \\|     |//  `.              //
 * //             /  \\|||  :  |||//  \             //
 * //            /  _||||| -:- |||||-  \            //
 * //            |   | \\\  -  /// |   |            //
 * //            | \_|  ''\---/''  |   |            //
 * //            \  .-\__  `-`  ___/-. /            //
 * //          ___`. .'  /--.--\  `. . __           //
 * //       ."" '<  `.___\_<|>_/___.'  >'"".        //
 * //      | | :  `- \`.;`\ _ /`;.`/ - ` : | |      //
 * //      \  \ `-.   \_ __\ /__ _/   .-` /  /      //
 * // ======`-.____`-.___\_____/___.-`____.-'====== //
 * //                    `=---='                    //
 * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
 * //          佛祖保佑        永无BUG               //
 * // 佛曰:                                         //
 * //        写字楼里写字间,写字间里程序员.            //
 * //        程序人员写程序,又拿程序换酒钱.            //
 * //        酒醒只在网上坐,酒醉还来网下眠.            //
 * //        酒醉酒醒日复日,网上网下年复年.            //
 * //        但愿老死电脑间,不愿鞠躬老板前.            //
 * //        奔驰宝马贵者趣,公交自行程序员.            //
 * //        别人笑我忒疯癫,我笑自己命太贱.            //
 * //        不见满街漂亮妹,哪个归得程序员.            //
 * ///////////////////////////////////////////////////
 */
public class MyRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private ModuleService moduleService;
    @Lazy
    @Autowired
    private RoleService roleService;
    @Lazy
    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Set<String> roleNames = Sets.newHashSet();
        Set<String> permissions = Sets.newHashSet();
        if( null == user ){
            return null;
        } else {
            user = this.userService.findUserByLoginName(user.getLoginName());
            Set<Role> roles = user.getRoleSet();
            for (Role role : roles) {
                if(StringUtil.isNotBlank(role.getName())){
                    roleNames.add(role.getName());
                }
            }
            Set<Module> modules = user.getModuleSet();
            for (Module module : modules) {
                if(StringUtil.isNotBlank(module.getCode())){
                    permissions.add(module.getCode());
                }
            }
            authorizationInfo.addRoles(roleNames);
            authorizationInfo.addStringPermissions(permissions);
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token携带了用户信息
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户名
        String loginName = upToken.getUsername();
        //数据库保存的密码
        String password = String.valueOf(upToken.getPassword());
        //根据用户名查询数据库中对应的记录
        User user = this.userService.findUserByLoginName(loginName);
        if( null == user ){
            throw new AccountException("用户不存在");
        } else if ("2".equals(user.getStatus())) {
            throw new DisabledAccountException("帐号已经禁止登录！");
        } else {
            //盐值
            ByteSource bs = ByteSource.Util.bytes(loginName);
            if (null != user.getPassword()) {
                //封装用户信息，构建AuthenticationInfo对象并返回
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), bs, getName());
                return authenticationInfo;
            }
        }
        return null;
    }

}