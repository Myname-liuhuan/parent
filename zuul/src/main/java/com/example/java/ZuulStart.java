package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 刘欢
 * @Date 2019/12/10
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulStart {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStart.class,args);
    }
}
