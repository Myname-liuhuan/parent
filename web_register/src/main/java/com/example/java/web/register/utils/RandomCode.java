package com.example.java.web.register.utils;

import java.util.Random;

/**
 * @author 刘欢
 * @Date 2019/12/13
 *
 * 生成随机的验证码
 *
 */
public class RandomCode {

    /**
     *
     * @param length
     * @return
     */
    public static String randomStr(int length){
        StringBuffer codeStr = new StringBuffer("");
        for (int i = 0;i < length; i++){
            codeStr.append((int)(10*Math.random()));
        }
        return codeStr.toString();
    }


    /**
     * 适配阿里云的短信随机数
     * @return
     */
    public static String randomAliyunMsgCode(){
        String s = randomStr(6);
        //阿里云第一位为0 会有一定问题
        if (s.charAt(0) == '0'){
            char[] arr = s.toCharArray();
            arr[0] = '1';
            s = String.valueOf(arr);
        }
        return s;
    }
}
