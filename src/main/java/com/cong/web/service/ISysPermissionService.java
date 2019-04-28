package com.cong.web.service;

import com.cong.web.model.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 获取该角色下的所有权限
     * @param roleId
     * @return
     */
    List<SysPermission> queryPermissionByRoleId(long roleId);
}
