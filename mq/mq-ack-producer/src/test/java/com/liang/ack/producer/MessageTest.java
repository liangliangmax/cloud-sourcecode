package com.liang.ack.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage(){

        Map map = new HashMap();

        map.put("name","liane");
        map.put("password","123456");

        rabbitTemplate.convertAndSend("ackSmsTopicExchange","sms.ack",map);


    }
}
