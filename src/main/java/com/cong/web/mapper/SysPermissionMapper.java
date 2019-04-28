package com.cong.web.mapper;

import com.cong.web.model.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    //获取该角色下的所有权限
    @Select("SELECT * " +
            "FROM sys_role_permission srp " +
            "LEFT JOIN sys_permission sp ON srp.permission_id = sp.id " +
            "WHERE srp.role_id = #{roleId} ")
    List<SysPermission> queryPermissionByRoleId(long roleId);
}
