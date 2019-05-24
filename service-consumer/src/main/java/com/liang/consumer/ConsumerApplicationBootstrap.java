package com.liang.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value = {"com.liang.consumer","com.liang.api"})
public class ConsumerApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationBootstrap.class,args);
    }
}
