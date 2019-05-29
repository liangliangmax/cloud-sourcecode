package com.liang.provider.db;


import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.ResultCode;
import com.liang.api.entity.User;
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
    public NeuabcRestApiResult<List<User>> selectAll(@RequestParam("env") String env){

        try {
            return NeuabcRestApiResult.OK(dbUserService.selectAll(env));
        }catch (Exception e){

            e.printStackTrace();
            return NeuabcRestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }
    }

}
