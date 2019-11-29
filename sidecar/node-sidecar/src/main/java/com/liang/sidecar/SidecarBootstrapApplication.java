package com.liang.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableEurekaClient
@EnableSidecar
public class SidecarBootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(SidecarBootstrapApplication.class,args);
    }
}
