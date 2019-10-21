package com.liang.service_b.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.liang.service_b.dao.UserMapper;
import com.liang.service_b.dao.UserParentMapper;
import com.liang.service_b.entity.User;
import com.liang.service_b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        return userMapper.list();
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws Exception{

        try {
            userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "ok";
    }

}
