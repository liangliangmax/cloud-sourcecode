package com.liang.consumer.outerBind;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "liang")
public class OuterBindReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg, Channel channel, Message message) throws IOException {

        try {
            System.out.println("[liang] recieved message:" + msg);

            System.out.println(channel);
            System.out.println(message);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);

        }



    }
}
