package com.example.java.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘欢
 * @Date 2019/12/9
 *
 * @ServletComponentScan(basePackages = "com.example.java.web.filter") 扫描过滤器
 */
@SpringBootApplication
@MapperScan("com.example.java.web.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.example.java.web.filter")
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class,args);
    }
}
