//package com.ming.shiro.config;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.ming.shiro.utils.RetryLimitHashedCredentialsMatcher;
//import net.sf.ehcache.CacheException;
//import net.sf.ehcache.CacheManager;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.io.ResourceUtils;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * shiro配置类
// */
//@Configuration
//public class ShiroConfig {
//
//    /**
//     * 处理拦截资源文件过滤器
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //拦截器
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        //配置不会被拦截的链接,顺序判断,因为前端模板采用了thymeleaf,这里不能直接使用("/static/**", "anon")来配置匿名访问,必须配置到每个静态目录
//        map.put("/css/**", "anon");
//        map.put("/js/**", "anon");
//        map.put("/img/**", "anon");
//        map.put("/layui/**", "anon");
//        //配置退出过滤器,具体的退出代码Shiro已经实现
//        map.put("/logout", "logout");
//        map.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        //指定要求登录时的链接
//        shiroFilterFactoryBean.setLoginUrl("/");
//        //登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/login/hello");
//        //未授权时跳转的界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * shiro安全管理器设置realm认证和ehcache缓存管理
//     * @return
//     */
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //设置realm
//        securityManager.setRealm(myRealm());
//        //注入ehcache缓存管理器
//        securityManager.setCacheManager(ehCacheManager());
//        //注入Cookie记住我管理器
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }
//
//    /**
//     * thymeleaf里使用shiro的标签的bean
//     * @return
//     */
////    @Bean
////    public ShiroDialect shiroDialect() {
////        return new ShiroDialect();
////    }
//
//    /**
//     * 身份认证realm(账号,密码,校验,权限等)
//     * @return
//     */
//    @Bean(name = "myRealm")
//    public MyRealm myRealm() {
//        MyRealm myRealm = new MyRealm();
//        //使用自定义的CredentialsMatcher进行密码校验和输错次数限制
//        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return myRealm;
//    }
//
//    /**
//     * ehcache缓存管理器
//     * @return
//     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager ehcache = new EhCacheManager();
//        CacheManager cacheManager = CacheManager.getCacheManager("es");
//        if (cacheManager == null) {
//            try {
//                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:config/ehcache.xml"));
//            } catch (CacheException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ehcache.setCacheManager(cacheManager);
//        return ehcache;
//    }
//
//    /**
//     * 设置记住我cookie过期时间
//     * @return
//     */
//    @Bean
//    public SimpleCookie remeberMeCookie() {
//        SimpleCookie scookie = new SimpleCookie("rememberMe");
//        scookie.setMaxAge(3600);
//        return scookie;
//    }
//
//    /**
//     * 配置cookie记住我管理器
//     * @return
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(remeberMeCookie());
//        return cookieRememberMeManager;
//    }
//
//    /**
//     * 凭证匹配器
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
//        //散列算法:这里使用MD5算法
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //散列的次数,比如散列两次,相当于
//        hashedCredentialsMatcher.setHashIterations(1);
//        return hashedCredentialsMatcher;
//    }
//
//    /**
//     * 开启Shiro的注解
//     * @return
//     */
//    @Bean
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }
//
//}