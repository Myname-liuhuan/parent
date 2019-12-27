package com.example.java.web.cousumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 刘欢
 * @Date 2019/12/14
 *
 * @EnableDiscoveryClient : 是一种升级版的 @EnableEurekaClient
 * 前者可以适用域所有的注册中心(即包括非Eureka 的注册中心)
 * 后者只能适用于 Eureka 的注册中心
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ServletComponentScan("com.example.java.web.cousumer.filter")
public class CousumerStart {

    public static void main(String[] args) {
        SpringApplication.run(CousumerStart.class, args);
    }


    /**
     * @return 具有http通信的类对象 用于模块间转发请求
     *
     * @Bean : 表示将该返回的类对象添加到spring 管理的bean中  只有这样才能对该对象使用依赖注入
     * @LoadBalanced : 标记该返回的对象 会被用来处理负载均衡
     * RestTemplate 一个用于http通信的类
     *
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
