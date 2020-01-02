package com.example.web.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 刘欢
 * @Date 2019/12/28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@MapperScan("com.example.web.seckill.mapper")
public class SeckillStart {
    public static void main(String[] args) {
        SpringApplication.run(SeckillStart.class,args);
    }
}
