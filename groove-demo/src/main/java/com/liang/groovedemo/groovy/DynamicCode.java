package com.liang.groovedemo.groovy;

import com.liang.groovedemo.MqService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${queue}")
public class DynamicCode extends MqService{

    @Override
    @RabbitHandler
    public void service(String message){
        super.service(message);
    }
}