package com.example.java.web.register.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.java.web.register.entity.WebUsersEntity;
import com.example.java.web.register.utils.AliyunSmsUtil;
import com.example.java.web.register.utils.RandomCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author huan
 *   WebUsers控制器
 * @date 2019-12-09 19:07:02
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{


    /**
     * 将这个随机数通过阿里云短信发送给用户
     * @return
     */
    @RequestMapping("sendMsg")
    @ResponseBody
    public String sendMsg(String phone, String code){
        //通过阿里云发送短信验证码
        try {
            AliyunSmsUtil.sendSms(phone,code);
        } catch (ClientException e) {
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }


}
