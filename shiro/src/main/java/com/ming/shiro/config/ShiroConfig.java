package com.ming.shiro.config;

import com.ming.shiro.filter.CaptchaFormAuthenticationFilter;
//import com.ming.shiro.filter.KickoutSessionFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 处理拦截资源文件过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> map = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接,顺序判断,因为前端模板采用了thymeleaf,这里不能直接使用("/static/**", "anon")来配置匿名访问,必须配置到每个静态目录
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/images/**", "anon");
        map.put("/layui/**", "anon");
        map.put("/genCaptcha", "anon");
        map.put("/login/main", "anon");
        map.put("/logout", "logout");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("authc", new CaptchaFormAuthenticationFilter());
//        filters.put("kickout", kickoutSessionFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //指定要求登录时的链接
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权时跳转的界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        return shiroFilterFactoryBean;
    }

    /**
     * shiro安全管理器设置realm认证和ehcache缓存管理
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myRealm());
        //注入ehcache缓存管理器
        securityManager.setSessionManager(sessionManager());
        //注入ehcache缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        //注入Cookie记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份认证realm(账号,密码,校验,权限等)
     * @return
     */
    @Bean(name = "myRealm")
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setAuthorizationCachingEnabled(false);
        //密码校验
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    /**
     * session管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setKeyPrefix("wl_");
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(18000);//配置过期时间
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * ehcache缓存管理器
     * @return
     */
    @Bean
    public RedisCacheManager ehCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置cookie记住我管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 设置记住我cookie过期时间
     * @return
     */
    @Bean
    public SimpleCookie remeberMeCookie() {
        SimpleCookie scookie = new SimpleCookie("rememberMe");
        scookie.setHttpOnly(true);
        scookie.setMaxAge(864000);
        return scookie;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     * @return
     */
//    @Bean
//    public KickoutSessionFilter kickoutSessionFilter() {
//        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
//        //使用cacheManager获取相应的cache来缓存用户登录的会话,用于保存用户—会话之间的关系的；
//        //这里我们还是用之前shiro使用的ehcache实现的cacheManager()缓存管理
//        //也可以重新另写一个,重新配置缓存时间之类的自定义缓存属性
////        kickoutSessionFilter.setCacheManager(ehCacheManager());
//        //用于根据会话ID,获取会话进行踢出操作的；
//        kickoutSessionFilter.setSessionManager(sessionManager());
//        //是否踢出后来登录的,默认是false,即后者登录的用户踢出前者登录的用户,踢出顺序
//        kickoutSessionFilter.setKickoutAfter(false);
//        //同一个用户最大的会话数,默认1,比如2的意思是同一个用户允许最多同时两个人登录
//        kickoutSessionFilter.setMaxSession(1);
//        //被踢出后重定向到的地址
//        kickoutSessionFilter.setKickoutUrl("/");
//        return kickoutSessionFilter;
//    }

    /**
     * 凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列的次数,比如散列两次,相当于
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro的注解
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}