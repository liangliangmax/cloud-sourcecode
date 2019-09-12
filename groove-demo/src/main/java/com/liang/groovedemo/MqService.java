package com.liang.groovedemo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public class MqService {


    public void service(String message){
        System.out.println(message);
    }
}
