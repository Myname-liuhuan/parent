package com.example.java.admin.controller;

import com.example.java.admin.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: 刘欢
 * @Date: 2019/11/26 8:32
 */
@Controller
@RequestMapping("login")
public class LoginController {

    /**
     * 使用流输出图片到前台页面 所以返回值应该为void 且不加ResponseBody注解
     */
    @RequestMapping("getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session){
        try {
            String verifyCode = VerifyCodeUtils.outputVerifyImage(220, 50, response.getOutputStream(), 5);
            //将验证码用session保存 以供登录的时候验证
            session.setAttribute("verifyCode", verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证验证码的正确性
     * @param checkCode
     * @param session
     * @return
     */
    @RequestMapping("checkVerifyCode")
    @ResponseBody
    public String checkVerifyCode(String checkCode, HttpSession session){
        String verifyCode = (String) session.getAttribute("verifyCode");
        if (verifyCode != null && checkCode != null && verifyCode.equalsIgnoreCase(checkCode)){
            return "true";
        }
        return "false";
    }
}
