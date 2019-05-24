package com.liang.consumer.controller;

import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.User;
import com.liang.consumer.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserConsumerController {

    @Autowired
    private UserFeignClient userFeignClient;

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public NeuabcRestApiResult<String> save(@RequestBody User user){
        System.out.println(user.toString());
        System.out.println(userFeignClient);
        return userFeignClient.save(user);
    }

}
