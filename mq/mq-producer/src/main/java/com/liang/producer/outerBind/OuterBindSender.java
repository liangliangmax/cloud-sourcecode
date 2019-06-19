package com.liang.producer.outerBind;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class OuterBindSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void stringSend() throws Exception{
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[liang] send msg:" + dateString);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        // 第一个参数为刚刚定义的队列名称
        for(int i = 0;i<10;i++){
            rabbitTemplate.convertAndSend("liangTopicExchange", "liang.topic.msg", dateString,correlationId);
            Thread.sleep(1000);
        }

    }
}
