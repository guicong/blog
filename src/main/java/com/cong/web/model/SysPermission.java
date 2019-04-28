package com.cong.web.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author guicong
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 权限
     */
    private String permission;

    /**
     * 类型（0：目录  1：按钮）
     */
    private Integer type;

    /**
     * url
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createOn;

    /**
     * 创建人id
     */
    private Long createBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateOn;

    /**
     * 修改人id
     */
    private String updateBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
