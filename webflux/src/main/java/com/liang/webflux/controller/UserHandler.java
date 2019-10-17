package com.liang.webflux.controller;

import com.liang.webflux.dao.UserMapper;
import com.liang.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHandler {

    @Autowired
    private UserMapper userMapper;

    public List<User> list(){
        List<User> list = userMapper.list();

        return list;
    }
}
