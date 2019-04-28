package com.cong.web.service.impl;

import com.cong.web.model.SysPermission;
import com.cong.web.mapper.SysPermissionMapper;
import com.cong.web.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 获取该角色下的所有权限
     * @param roleId
     * @return
     */
    @Override
    public List<SysPermission> queryPermissionByRoleId(long roleId) {
        return sysPermissionMapper.queryPermissionByRoleId(roleId);
    }
}
