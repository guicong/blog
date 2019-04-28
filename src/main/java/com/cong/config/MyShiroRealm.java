package com.cong.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cong.web.model.SysPermission;
import com.cong.web.model.SysRole;
import com.cong.web.model.SysUser;
import com.cong.web.service.ISysPermissionService;
import com.cong.web.service.ISysRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @author guicong 登录和权限判断
 * @description
 * @since 2019-04-22
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 注意此处需要添加@Lazy注解，否则SysUserService缓存注解、事务注解不生效
     */
    @Autowired
    @Lazy
    private ISysRoleService sysRoleService;

    @Autowired
    @Lazy
    private ISysPermissionService sysPermissionService;

    /**
     * 身份认证 ---- 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //根据用户名从数据库查找用户信息
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser sysUser = new SysUser().selectOne(wrapper);
        if(null == sysUser) {
            return null;
        } else {
           return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        }
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        if(sysUser != null) {
            //获取用户对应的角色
            List<SysRole> roles = sysRoleService.queryRoleByUserId(sysUser.getId());
            if(null != roles && roles.size() > 0) {
                for(SysRole sysRole : roles) {
                    authorizationInfo.addRole(sysRole.getRoleName());
                    //获取角色对应的权限
                    List<SysPermission> permissions = sysPermissionService.queryPermissionByRoleId(sysRole.getId());
                    if(null != permissions && permissions.size() > 0) {
                        for(SysPermission sysPermission : permissions) {
                            authorizationInfo.addStringPermission(sysPermission.getPermission());
                        }
                    }
                }
            }
        }
        return authorizationInfo;
    }

}
