package com.liang.producer.queue;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class QueueSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void stringSend() throws Exception{
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[string] send msg:" + dateString);
        // 第一个参数为刚刚定义的队列名称
        for(int i = 0;i<10;i++){
            this.rabbitTemplate.convertAndSend("test1", dateString);
            Thread.sleep(1000);
        }

    }
}
