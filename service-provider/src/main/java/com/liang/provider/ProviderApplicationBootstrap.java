package com.liang.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value = {"com.liang.provider","com.liang.api"})

public class ProviderApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicationBootstrap.class,args);
    }
}
