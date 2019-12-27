package com.example.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 刘欢
 * @Date 2019/12/18
 *
 * @EnableScheduling 在本模块中允许spring task 的任务调度
 *
 */
@SpringBootApplication
@MapperScan("com.example.product.mapper")
@EnableEurekaClient
@EnableScheduling
public class ProductStart {
    public static void main(String[] args) {
        SpringApplication.run(ProductStart.class, args);
    }
}
