package com.cong.web.mapper;

import com.cong.web.model.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    //得到该用户下的所有角色
    @Select("SELECT * " +
            "FROM sys_user_role sur " +
            "LEFT JOIN sys_role sr ON sur.role_id = sr.id " +
            "WHERE sur.user_id = #{userId} ")
    List<SysRole> queryRoleByUserId(long userId);
}
