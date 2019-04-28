package com.cong.web.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {


    @RequestMapping("/queryUserList")
    @RequiresPermissions("sysUser:view")
    public String queryUserList(){
        return "/userList";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("sysUser:add")
    public String queryUserAdd(){
        return "/userAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("sysUser:del")
    public String queryUserDel(){
        return "/userDel";
    }


}

