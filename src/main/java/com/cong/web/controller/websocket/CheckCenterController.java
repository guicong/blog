package com.cong.web.controller.websocket;

import com.cong.vo.SimpleMsgVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author guicong
 * @description
 * @since 2019-04-30
 */
@Controller
public class CheckCenterController {

    //页面请求
    @GetMapping("/websocket/{cid}")
    public String socket(@PathVariable String cid, ModelMap modelMap) {
        modelMap.addAttribute("cid", cid);
        return "websocket";
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Object pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
//            return ApiReturnUtil.error(cid+"#"+e.getMessage());
        }
        return SimpleMsgVO.getOk(cid);
    }

    @RequestMapping("/websocket")
    public String init() {
        return "websocket";
    }

}
