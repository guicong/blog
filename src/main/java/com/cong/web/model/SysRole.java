package com.cong.web.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 状态（0：可用  1：禁用）
     */
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
