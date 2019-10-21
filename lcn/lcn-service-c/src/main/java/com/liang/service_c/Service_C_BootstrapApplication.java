package com.liang.service_c;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDistributedTransaction
@EnableTransactionManagement
@SpringBootApplication
public class Service_C_BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(Service_C_BootstrapApplication.class,args);
    }
}
