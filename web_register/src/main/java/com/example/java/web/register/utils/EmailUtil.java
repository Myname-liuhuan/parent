package com.example.java.web.register.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author 刘欢
 * @Date 2019/12/14
 * 邮件发送的工具类
 * 功能能实现的前提条件是 需要给发件邮箱设置 开启 pop3/pop 代理
 */
public class EmailUtil {

    /**
     * 发送方邮箱的服务器
     */
    private static String mailServerHost = "smtp.163.com";

    /**
     * 发送方 邮箱名
     */
    private static String mailSenderAddress = "studyliuhuan@163.com";

    /**
     * 发件人姓名
     */
    private static String mailSenderNick = "huan";

    /**
     * 发件人的账号
     */
    private static String mailSenderUsername = "studyliuhuan@163.com";

    /**
     * 发件人的邮箱密码（授权码）
     */
    private static String mailSenderPassword = "199604293119aA";


    /**
     * 发送html格式的邮件
     */
    public static void sendEmail(){
        HtmlEmail email = new HtmlEmail();
        //设置邮箱所属服务器
        email.setHostName(mailServerHost);
        //设置发件方账号密码
        email.setAuthentication(mailSenderUsername,mailSenderPassword);
        //设置发件方邮箱编码
        email.setCharset("UTF-8");


        try {
            //设置接收方邮箱账号
            email.addTo("951910990@qq.com");
            //设置发件方地址
            email.setFrom(mailSenderAddress,mailSenderNick);
            //设置邮件标题
            email.setSubject("恭喜中奖");
            //设置消息 因为使用的是htmlemail 所以消息中的标签会生效
            email.setMsg("<p>你好 恭喜您获得两箱瓜子</p><br><image src='http://q1ivuydqx.bkt.clouddn.com/4dd439bea9824a018c8851721a0d4ad1'/> ");

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }



    }
}
