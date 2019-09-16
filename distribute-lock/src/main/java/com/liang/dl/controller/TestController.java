package com.liang.dl.controller;

import com.liang.dl.lock.annotation.DistributeLock;
import com.liang.dl.lock.annotation.LockParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/")
public class TestController {

    private Map<String,User> map = new ConcurrentHashMap<>();

    @DistributeLock(prefix = "user",expire = 10)
    @PostMapping("/index")
    public String test(@LockParam @RequestBody List<User> list) throws InterruptedException {

        for (User user:list){
            user.setId(UUID.randomUUID().toString());

            map.put(user.getId(),user);
            System.out.println(user);
            Thread.sleep(1000);

        }

        System.out.println(map);
        return "ok";
    }
}


class User implements Serializable {

    private String id;
    private String name;

    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}