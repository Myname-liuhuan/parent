package com.example.java.admin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huan
 * @date 2019/12/8下午4:40
 *
 * 用来实现 web.xml 中过滤器对页面请求以及响应的编码格式的转化
 *@WebFilter(urlPatterns = "/*") : 表示所有的请求和响应都要经过该过滤器
 *
 **/
@WebFilter(urlPatterns = "/*")
public class CharSetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //实现编码格式的转换 先将形参强转为 HttpServletRequest HttpServletResponse  然后调用方法设置编码,最后放行
        //要转化为HttpServletRequest 的原因是 tomcat中的request 和 response 默认是实现  HttpServletRequest HttpServletResponse的
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");
        //放行 并且以转化格式后的request 和response 作为参数
        filterChain.doFilter(request,response);
    }
}
