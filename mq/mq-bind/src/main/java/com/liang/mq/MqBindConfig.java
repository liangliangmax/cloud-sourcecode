package com.liang.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 单独弄一个项目进行绑定是不好用的
 */
@Configuration
public class MqBindConfig {

    @Bean
    public Queue liang() {
        System.out.println("liang对象生成了");
        return new Queue("liang",true);
    }

    /**
     * 定义个topic交换器
     * @return
     */
    @Bean
    TopicExchange liangTopicExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return new TopicExchange("liangTopicExchange");
    }


    /**
     * 将定义的topicA队列与topicExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithA() {
        return BindingBuilder.bind(liang()).to(liangTopicExchange()).with("liang.topic.msg");
    }
}
