package com.liang.provider.controller;

import com.liang.api.clientapi.UserApi;
import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public NeuabcRestApiResult<String> save(@RequestBody User user){

        System.out.println(user.toString());

        return NeuabcRestApiResult.OK("保存成功");
    }


}
