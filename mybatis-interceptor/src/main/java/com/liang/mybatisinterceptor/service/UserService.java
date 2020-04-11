package com.liang.mybatisinterceptor.service;

import com.github.pagehelper.PageHelper;
import com.liang.mybatisinterceptor.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public int findUser(String id,String des){

        PageHelper.startPage(1,10);
        System.out.println(userMapper.findUser(id,des));

        return 1;
    }

}
