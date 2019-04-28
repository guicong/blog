package com.cong.vo;

import com.cong.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guicong
 * @description 定义普通返回信息
 * @since 2019-04-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMsgVO implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 返回成功，无数据
     * @return
     */
    public static SimpleMsgVO getOk() {
        return new SimpleMsgVO(ResponseUtil.CODE_0, ResponseUtil.MESSAGE_0, null);
    }

    /**
     * 返回成功，有数据
     * @param data
     * @return
     */
    public static SimpleMsgVO getOk(Object data) {
        return new SimpleMsgVO(ResponseUtil.CODE_0, ResponseUtil.MESSAGE_0, data);
    }

    /**
     * 系统错误，无数据
     * @return
     */
    public static SimpleMsgVO getFail9997() {
        return new SimpleMsgVO(ResponseUtil.CODE_9997, ResponseUtil.MESSAGE_9997, null);
    }

    /**
     * 登录错误，无数据
     * @return
     */
    public static SimpleMsgVO getFail9996(String message) {
        return new SimpleMsgVO(ResponseUtil.CODE_9997, message, null);
    }

}
