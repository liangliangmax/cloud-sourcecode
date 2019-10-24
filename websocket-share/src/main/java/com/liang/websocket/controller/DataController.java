package com.liang.websocket.controller;

import com.alibaba.fastjson.JSON;
import com.liang.websocket.entity.Teacher;
import com.liang.websocket.util.RedisLockHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/teachers")
    public String getTeacherTable(){

        String[] times = new String[]{"2019-09-09 16:00:00","2019-09-09 17:00:00","2019-09-09 18:00:00","2019-09-09 19:00:00"};

        List<Teacher> list = new ArrayList<>();

        for(int i = 0 ;i<50;i++){
            Teacher teacher = new Teacher();

            teacher.setTeacherId(i+"");
            teacher.setTeacherName(i+"老师");

            teacher.setTimes(Arrays.asList(times));


            list.add(teacher);

        }

        return JSON.toJSONString(list);
    }


    @RequestMapping("/refreshLock")
    public String refreshLock(){

        Set<String> set = redisTemplate.keys("shareTable:*");

        if(set.size()!=0){

            List<String> list = new ArrayList();
            Iterator<String> iterator = set.iterator();

            while (iterator.hasNext()){
                String item = iterator.next();

                item = item.replaceAll("shareTable:","")
                        .replaceFirst(":","_")
                        .replaceAll("-","")
                        .replaceAll(":","")
                        .replaceAll(" ","");

                list.add(item);

            }


            return JSON.toJSONString(list);
        }

        return JSON.toJSONString(set);

    }
}
