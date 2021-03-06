package com.liang.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {RedisApplicationBootstrap.class})
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){

        redisTemplate.opsForValue().set("ahahah","liang");

        System.out.println(redisTemplate.opsForValue().get("ahahah"));
    }
}
