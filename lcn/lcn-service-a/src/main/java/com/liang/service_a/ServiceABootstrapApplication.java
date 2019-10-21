package com.liang.service_a;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDistributedTransaction
@EnableTransactionManagement
@SpringBootApplication
public class ServiceABootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceABootstrapApplication.class,args);
    }
}
