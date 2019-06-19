package com.liang.consumer.outerBind;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单独起一个项目进行绑定是不好用的
 * 在生产者中或者消费者中或者两个都放就行，
 * 但是消费者如果不进行绑定，先启动消费者，会直接报错
 * 如果生产者不进行绑定先启动生产者，代码正常运行，只有在调用到发消息时候才会报错
 * 所以我认为放置在消费者中进行绑定可能比较合适
 */
@Configuration
public class MqBindConfig {

    @Bean
    public Queue liang() {
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
    public Binding bindingTopicExchangeWithLiang() {
        return BindingBuilder.bind(liang()).to(liangTopicExchange()).with("liang.topic.msg");
    }
}
