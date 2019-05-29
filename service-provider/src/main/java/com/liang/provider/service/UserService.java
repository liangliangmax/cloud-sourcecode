package com.liang.provider.service;


import com.liang.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * spring cache缓存，没有测试过，不知道好用不
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    private Map<String, User> db = new ConcurrentHashMap<>();

    @CachePut(value="user",key = "#result.id",condition = "#result!=null")
    public void add(User user){

        db.put(user.getId(),user);

        db.entrySet().stream().forEach(System.out::println);

    }

    @CachePut(value="user",key = "#result.id",condition = "#result!=null")
    public void update(User user){

        db.put(user.getId(),user);
    }

    @Cacheable(cacheNames = "user")
    public List<User> getAll(){

        return db.values().stream().collect(Collectors.toList());

    }

    @CacheEvict(value="user",allEntries = true) //清楚所有缓存
    public boolean deleteById(String id){

        return db.remove(id) == null?false:true;
    }

    @Cacheable(cacheNames = "user",key = "#root.methodName+'['+#id+']'",condition = "#id!=null && #result!=null")
    public User getById(String id){


        return db.get(id);
    }
}
