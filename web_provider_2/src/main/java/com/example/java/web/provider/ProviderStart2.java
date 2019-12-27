package com.example.java.web.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘欢
 * @Date 2019/12/16
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.java.web.provider.mapper")
@ServletComponentScan("com.example.java.web.provider.filter")
@EnableDiscoveryClient
public class ProviderStart2 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderStart2.class, args);
    }
}
