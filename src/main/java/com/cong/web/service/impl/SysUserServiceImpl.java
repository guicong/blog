package com.cong.web.service.impl;

import com.cong.web.model.SysUser;
import com.cong.web.mapper.SysUserMapper;
import com.cong.web.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
