package com.cong.web.service;

import com.cong.web.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 得到该用户下的所有角色
     * @param userId
     * @return
     */
    List<SysRole> queryRoleByUserId(long userId);
}
