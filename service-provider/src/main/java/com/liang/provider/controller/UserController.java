package com.liang.provider.controller;

import com.liang.api.clientapi.UserApi;
import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.ResultCode;
import com.liang.api.entity.User;
import com.liang.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public NeuabcRestApiResult<String> save(@RequestBody User user){

        System.out.println(user.toString());

        return NeuabcRestApiResult.OK("保存成功");
    }


    @Override
    public NeuabcRestApiResult<User> getById(@RequestParam("id") String id){

        try {

            return NeuabcRestApiResult.OK(userService.getById(id));

        }catch (Exception e){

            e.printStackTrace();

            return NeuabcRestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }

    }

    @Override
    public NeuabcRestApiResult<String> add(@RequestBody User user){
        try {
            user.setId(UUID.randomUUID().toString());

            System.out.println(user);
            userService.add(user);
            return NeuabcRestApiResult.OK();
        }catch (Exception e){
            e.printStackTrace();
            return NeuabcRestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }

    }


}
