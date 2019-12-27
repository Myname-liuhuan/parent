package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 刘欢
 * @Date 2019/12/7
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerOne {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerOne.class, args);
    }
}
