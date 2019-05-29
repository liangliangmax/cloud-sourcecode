package com.liang.provider.controller;


import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.ResultCode;
import com.liang.api.entity.User;
import com.liang.provider.db.MultiDbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mdbUser")
public class MultiDbUserController {

    @Autowired
    private MultiDbUserService dbUserService;

    @RequestMapping("/selectAll")
    public NeuabcRestApiResult<List<User>> selectAll(){

        try {
            return NeuabcRestApiResult.OK(dbUserService.selectAll());
        }catch (Exception e){

            e.printStackTrace();
            return NeuabcRestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }
    }

    @RequestMapping("/add")
    public NeuabcRestApiResult add(){
        try {
            dbUserService.add();
            return NeuabcRestApiResult.OK();
        }catch (Exception e){
            e.printStackTrace();
            return NeuabcRestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }
    }

}
