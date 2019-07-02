package com.liang.ack.consumer;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MqListener {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 单发短信队列
     * 分好多queue是为了业务上切分明确，但是大家发送的短信格式都是一样的，所以统一进行监听即可
     * @param smsInfo
     */
    @RabbitListener(queues = {
            "ack.queue"
    })
    @RabbitHandler
    public void sendSms(@Payload Map<String,String> smsInfo, Channel channel, @Headers Map<String,Object> headers,Message message){

        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);

        try {

            System.out.println(smsInfo);

            System.out.println(headers);

            int i = 1/0;
            channel.basicAck(deliveryTag,false);

            System.out.println(message.getMessageProperties().getDeliveryTag());

            System.out.println(deliveryTag);

        } catch (Exception e) {
            log.error(e.getMessage());
            try {
                channel.basicNack(deliveryTag,false,true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
