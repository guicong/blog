package com.cong.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author guicong
 * @description 异常处理器
 * @since 2019-04-22
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    public String defaultErrorHandler()  {
        return "/403";
    }

}
