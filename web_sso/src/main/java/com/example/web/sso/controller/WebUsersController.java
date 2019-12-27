package com.example.web.sso.controller;

import com.example.java.web.model.entity.WebUsersEntity;
import com.example.web.sso.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author huan
 *   WebUsers控制器
 * @date 2019-12-09 19:07:02
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 该方法验证登录的同时,如果账号密码正确,还要  将获取到的用户信息添加到redis(service层操作) 同时封装到cookie,返回到浏览器(客户端)
     * @param user
     * @return
     */
    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(WebUsersEntity user, HttpServletResponse response){

        //先将密码加密处理
        if(user.getPwd() != null){
            user.setPwd(MD5.md5crypt(user.getPwd()));
        }else{
            return "fail";
        }

        String token = webUsersService.findObjectByParams(user);
        if (token == ""){
            return "fail";
        }

        //将cookie 添加到返回的响应response中去 那这样就互相嵌套了 要获取用户数据缺一不可
        Cookie cookie = new Cookie("token",token);
        //设置过期时间 20分钟 与redis的过期时间保持同步
        cookie.setMaxAge(20*60);
        cookie.setPath("/webusers");
        //将cookie 添加到response
        response.addCookie(cookie);
        return "success";
    }

    /**
     * 定义一个方法 供其他模块的请求访问，返回cookie中含有的token。
     * @return
     */
    @RequestMapping("getCookieValue")
    @ResponseBody
    public String getCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            //遍历cookies  看是否有叫token的值 有就返回其值 否者返回空字符串
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 在该方法中同时做清除cookie中值和redis中值的操作(清除：将value值置"")
     * @param token
     * @return
     */
    @RequestMapping("signOut")
    @ResponseBody
    public String signOut(String token, HttpServletRequest request, HttpServletResponse response){
        //清除cookie值
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    //将cookie的超时时间设为0 就是删除该cookie
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    String s = webUsersService.deleteFormRedis(token);
                    System.out.println("redis删除 "+ s);
                    return "success";
                }
            }
        }
        return "fail";
    }

    /**
     * 根据token获取到redis 中的用户信息
     * @return
     */
    @RequestMapping("getUserEntity")
    @ResponseBody
    public WebUsersEntity getUserEntity(String token){
        return webUsersService.getUserEntity(token);
    }
}
