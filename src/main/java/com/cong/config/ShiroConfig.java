package com.cong.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author guicong
 * @description shiro插件配置
 * @since 2019-04-22
 */
@Configuration
public class ShiroConfig {


    /**
     * 定义shiro Filter工厂类
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChainMap = new LinkedHashMap<>();
        //配置退出. 过滤器：logout,这个由shiro进行实现的.
        filterChainMap.put("/logout", "logout");
        filterChainMap.put("/login", "anon");
        // 配置记住我或认证通过可以访问的地址
//        filterChainMap.put("/", "user");

        filterChainMap.put("/js/**","anon");//可匿名访问到js文件
        filterChainMap.put("/css/**","anon");
        filterChainMap.put("/img/**","anon");

        //authc:所有的URL都必须认证通过才可以访问.
        filterChainMap.put("/**","user");

        //设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置成功之后要跳转的链接.
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面.
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 定义shiro的安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //设置自定义的realm
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        //配置记住我.
        securityManager.setRememberMeManager(cookieRememberMeManager());

        return securityManager;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        myShiroRealm.setAuthorizationCacheName("shiro");//指定选取哪一个缓存策略（主要针对于授权）
        return myShiroRealm;
    }

    /**
     * 密码加密算法
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        //默认的
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//加密算法.
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数.
        return hashedCredentialsMatcher;
    }

    /**
     * 注入Ehcache缓存.
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        //配置缓存文件.
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return ehCacheManager;
    }

    /**
     * cookie的管理对象.
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //需要管理我们的cookie对象.
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 配置cookie对象. -- 记住我cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //cookie的名称，也即是 前端 checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //可选： 配置cookie的生效时间，单位是秒. 60*60*24 = 1天.
        simpleCookie.setMaxAge(60*60*24);
        return simpleCookie;
    }

    /**
     * 开启shiro注解
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro aop注解支持.
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);//设置安全管理器
        return attributeSourceAdvisor;
    }

}
