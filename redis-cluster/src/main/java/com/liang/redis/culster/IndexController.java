package com.liang.redis.culster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连接redis集群的测试类
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public void test(){

        redisTemplate.opsForValue().set("ahahah","liang");

        System.out.println(redisTemplate.opsForValue().get("ahahah"));

        redisTemplate.opsForValue().set("stock",10);


        long result = redisTemplate.opsForValue().increment("stock",-1);

        System.out.println(result);

        System.out.println(redisTemplate.opsForValue().get("stock"));
    }
}
