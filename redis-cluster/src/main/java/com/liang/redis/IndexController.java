package com.liang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public void test(){

        redisTemplate.opsForValue().set("ahahah","liang");

        System.out.println(redisTemplate.opsForValue().get("ahahah"));

    }
}
