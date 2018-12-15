package com.ming.shiro.config;

import com.ming.shiro.security.domain.RoleModule;
import com.ming.shiro.security.domain.User;
import com.ming.shiro.security.domain.UserRole;
import com.ming.shiro.security.service.LoginService;
import com.ming.shiro.security.service.ModuleService;
import com.ming.shiro.security.service.RoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 自定义登录认证和授权
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginService loginService;

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
        if( null == user ){
            return null;
        } else {
            UserRole userRole = new UserRole(user.getId(), null);
            List<UserRole> list = loginService.findUserRole(userRole);
            for (UserRole ur : list) {
                authorizationInfo.addRole(String.valueOf(ur.getRoleId()));
                RoleModule roleModule = new RoleModule(ur.getRoleId(), null);
                List<RoleModule> rmList = this.roleService.findRoleModule(roleModule);
                for (RoleModule rm : rmList) {
                    authorizationInfo.addStringPermission(String.valueOf(rm.getModuleId()));
                }
            }
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
        User user = this.loginService.findByLoginName(loginName);
        if( null == user ){
            return null;
        } else {
            //盐值
            ByteSource bs = ByteSource.Util.bytes(loginName);
            if (null != user.getPassword()) {
                //封装用户信息，构建AuthenticationInfo对象并返回
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), bs, getName());
                //AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, DigestUtils.md5Hex(password), getName());
                return authenticationInfo;
            }
            return null;
        }
    }

}