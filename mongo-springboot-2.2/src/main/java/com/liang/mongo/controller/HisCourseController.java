package com.liang.mongo.controller;

import com.liang.mongo.entity.HisCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HisCourseController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @RequestMapping("/test")
    public String test(){

        List<HisCourseDto> all = mongoTemplate.findAll(HisCourseDto.class);

        System.out.println(all);
        return "ok";

    }

}
