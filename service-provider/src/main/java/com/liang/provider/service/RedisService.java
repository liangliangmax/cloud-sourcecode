package com.liang.provider.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public void exec(){



    }

}
