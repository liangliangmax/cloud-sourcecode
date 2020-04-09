package com.liang.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        int i = 1/0;
        return "aa";
    }
}
