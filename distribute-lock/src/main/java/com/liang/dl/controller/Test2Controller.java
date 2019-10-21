package com.liang.dl.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class Test2Controller {

    @RequestMapping(value = "/test")
    public Object test(){

        return null;
    }

}
