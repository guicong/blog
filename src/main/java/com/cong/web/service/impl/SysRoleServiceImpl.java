package com.cong.web.service.impl;

import com.cong.web.model.SysRole;
import com.cong.web.mapper.SysRoleMapper;
import com.cong.web.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 得到该用户下的所有角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> queryRoleByUserId(long userId) {
        return sysRoleMapper.queryRoleByUserId(userId);
    }
}
