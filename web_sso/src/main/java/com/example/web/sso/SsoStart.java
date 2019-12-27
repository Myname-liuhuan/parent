package com.example.web.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘欢
 * @Date 2019/12/22
 */
@SpringBootApplication
@MapperScan("com.example.web.sso.mapper")
@ServletComponentScan(basePackages = "com.example.web.sso.filter")
@EnableEurekaClient
public class SsoStart {
    public static void main(String[] args) {
        SpringApplication.run(SsoStart.class,args);
    }
}
