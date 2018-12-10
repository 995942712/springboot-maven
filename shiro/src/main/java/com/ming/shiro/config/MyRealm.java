//package com.ming.shiro.config;
//
//import com.ming.shiro.security.domain.RoleModule;
//import com.ming.shiro.security.domain.User;
//import com.ming.shiro.security.domain.UserRole;
//import com.ming.shiro.security.service.LoginService;
//import com.ming.shiro.security.service.ModuleService;
//import com.ming.shiro.security.service.RoleService;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 自定义登录认证和授权
// */
//public class MyRealm extends AuthorizingRealm {
//
//    @Autowired
//    private ModuleService moduleService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private LoginService loginService;
//
//    /**
//     * 授权
//     * @param principals
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();
//        if( null == user ){
//            return null;
//        } else {
//            List<UserRole> list = loginService.findByUserId(user.getId());
//            for (UserRole userRole : list) {
//                authorizationInfo.addRole(String.valueOf(userRole.getRoleId()));
//                List<RoleModule> rmList = this.roleService.findByRoleId(userRole.getRoleId());
//                for (RoleModule roleModule : rmList) {
//                    authorizationInfo.addStringPermission(String.valueOf(roleModule.getModuleId()));
//                }
//            }
//        }
//        return authorizationInfo;
//    }
//
//    /**
//     * 登录认证
//     * @param token
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String loginName = token.getPrincipal().toString();
//        User user = this.loginService.findByLoginName(loginName);
//        if( null == user ){
//            return null;
//        } else {
//            String password = user.getPassword();
//            if (null != password) {
//                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, DigestUtils.md5Hex(password), getName());
//                return authenticationInfo;
//            }
//            return null;
//        }
//    }
//
//}