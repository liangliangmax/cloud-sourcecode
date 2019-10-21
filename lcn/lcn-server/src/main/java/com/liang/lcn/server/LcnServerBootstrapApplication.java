package com.liang.lcn.server;


import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTransactionManagerServer
@SpringBootApplication
public class LcnServerBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnServerBootstrapApplication.class,args);
    }
}
