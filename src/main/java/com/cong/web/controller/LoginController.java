package com.cong.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cong.vo.SimpleMsgVO;
import com.cong.web.model.SysUser;
import com.cong.web.service.ISysUserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guicong
 * @description
 * @since 2019-04-22
 */
@Controller
@Log4j2
public class LoginController {

    @Autowired
    private EhCacheManager cacheManager;

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 跳转到登录界面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 跳转到主页面
     * @param map
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index(Map<String, Object> map){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        map.put("sysUser", sysUser);
        return "/index";
    }

    /**
     * 登录验证
     */
    @PostMapping("/login")
    @ResponseBody
    public SimpleMsgVO login(String username, String password, @RequestParam(value = "rememberMe", required = false) boolean rememberMe){
        log.debug("用户登录,请求参数username=%s,password=%s,是否记住我rememberMe=%s", username, password, rememberMe);
        SysUser sysUser = new SysUser().selectOne(new QueryWrapper<SysUser>().eq("user_name", username));
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return SimpleMsgVO.getFail9996("用户不存在,请联系管理员");
        } catch (IncorrectCredentialsException ice) {
//            return SimpleMsgVO.getFail9996("密码不正确");
            Cache<String, AtomicInteger> passwordRetryCache = cacheManager.getCache("passwordRetryCache");
            if (passwordRetryCache != null) {
                int retryNum = passwordRetryCache.get(sysUser.getUserName()) == null ? 0 : passwordRetryCache.get(sysUser.getUserName()).intValue();
                if (retryNum < 3) {
                    return SimpleMsgVO.getFail9996("密码错误" + retryNum + "次,再输错" + (3 - retryNum) + "次账号将被锁定" );
                } else if (retryNum == 3) {
                    return SimpleMsgVO.getFail9996("密码错误" + retryNum + "次,账户已锁定!2分钟后可再次登录,或联系管理员解锁" );
                }
            }
        } catch (LockedAccountException lae) {
            return SimpleMsgVO.getFail9996("账号已被锁定");
        } catch (ExcessiveAttemptsException eae) {
            return SimpleMsgVO.getFail9996("密码错误3次,账户已锁定!2分钟后可再次登录,或联系管理员解锁");
        } catch (AuthenticationException ae) {
            return SimpleMsgVO.getFail9996("用户名或密码不正确");
        } catch (Exception e) {
            return SimpleMsgVO.getFail9997();
        }
        return SimpleMsgVO.getOk();
    }


}
