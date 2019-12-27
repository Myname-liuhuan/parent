package com.example.java.admin.configureradapter;

import com.example.java.admin.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author huan
 * @date 2019/12/8下午4:26
 *
 * 代替了 spring-mvc-config.xml 中对拦截器的配置
 *
 **/
@Configuration
public class WebAppConfigurerInterceptor extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addPathPatterns 用于添加拦截规则
         * excludePathPatterns("") 指定例外的路径
         */
        //拦截所有model/*  和获取权限列表的请求(authority/authList) 但是将登录界面/model/toLogin 例外
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/model/*").addPathPatterns("/authority/authList").excludePathPatterns("/model/toLogin");
        super.addInterceptors(registry);
    }
}
