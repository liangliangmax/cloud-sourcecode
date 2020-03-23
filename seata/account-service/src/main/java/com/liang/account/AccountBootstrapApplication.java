package com.liang.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.liang")
public class AccountBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountBootstrapApplication.class,args);
    }
}
