package com.liang.service_b;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableTransactionManagement
@EnableDistributedTransaction
@SpringBootApplication
public class Service_B_BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(Service_B_BootstrapApplication.class,args);
    }
}
