package com.example.java.web.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘欢
 * @Date 2019/12/11
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.java.web.register")
@ServletComponentScan(basePackages = "com.example.java.web.register.filter")
public class RegisterStart {
    public static void main(String[] args) {
        SpringApplication.run(RegisterStart.class, args);
    }
}
