package com.liang.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.liang")
@ComponentScan(basePackages = "com.liang")
public class OrderBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBootstrapApplication.class,args);
    }
}
