package com.liang.sidecar.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SidecarConsumerBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SidecarConsumerBootstrapApplication.class,args);
    }
}
