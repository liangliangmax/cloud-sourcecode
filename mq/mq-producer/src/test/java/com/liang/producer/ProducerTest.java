package com.liang.producer;

import com.liang.producer.fanout.FanoutProducer;
import com.liang.producer.queue.QueueSender;
import com.liang.producer.topic.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {


    @Autowired
    private FanoutProducer fanoutProducer;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void test() throws Exception {
        queueSender.stringSend();
    }


    @Test
    public void fanoutTest(){

        fanoutProducer.fanoutSend();
    }

    @Test
    public void topicTest(){

        topicProducer.topicTopic1Send();
        topicProducer.topicTopic2Send();
        topicProducer.topicTopic3Send();

    }





}
