package com.example.java.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huan
 * @date 2019/12/8下午3:54
 **/
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在该类中做的操作就是 将session 在中的user字段 取出 看是否为空 为空就跳转到login 页面

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null){
            //不为空就放行
            return true;
        }else{
            //否则携带数据转发回登录界面
            request.setAttribute("backMsg","请先登录!");
            request.getRequestDispatcher("/model/toLogin").forward(request,response);
            //表示不放行
            return false;
        }
    }
}
