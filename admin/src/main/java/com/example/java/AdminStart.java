package com.example.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘欢
 * @Date 2019/12/7
 *
 * @ServletComponentScan("com.example.java.admin.filter") 扫描过滤器
 *
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.java.admin.mapper")
@ServletComponentScan("com.example.java.admin.filter")
public class AdminStart {
    public static void main(String[] args) {
        SpringApplication.run(AdminStart.class, args);
    }
}
