package com.liang.ack.consumer;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {


    @Bean
    public Queue smsQueue() {
        return new Queue("ack.queue",true);
    }


    @Bean
    public TopicExchange smsTopicExchange(){

        return new TopicExchange("ackSmsTopicExchange");
    }

    @Bean
    Binding bindingSmsTopicExchangeForNeupals() {
        // 绑定routing队列routingQueueMail到topicExchange交换机,并指定routing路由规则;
        return BindingBuilder.bind(smsQueue()).to(smsTopicExchange())
                .with("sms.ack");
    }

}
