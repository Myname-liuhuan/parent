package com.example.web.shoppingcart.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 刘欢
 * @Date 2019/12/10
 *
 * 定义过滤器 设置允许跨域访问
 */
@WebFilter(urlPatterns = "/*")
public class MyWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //得到HTTP请求响应对象
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //设置请求响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //设置响应正文的字符编码
        response.setContentType("text/templates.html;charset=UTF-8");
        //设置跨域问题的响应的权限
        //不再允许所有ip访问 只能让指定ip访问，指定允许的访问路径
        String[] allowStr = {"http://localhost:8060","http://localhost:8889"};
        Set allowOrigins = new HashSet(Arrays.asList(allowStr));
        String originHeads = request.getHeader("Origin");
        if (allowOrigins.contains(originHeads)){
            //设置允许跨域的配置
            // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP），即第二个参数为发送请求的模块的ip+端口号
            response.setHeader("Access-Control-Allow-Origin", originHeads);
        }
        //response.setHeader("Access-Control-Allow-Origin", "*"); // 设置允许所有跨域访问
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
