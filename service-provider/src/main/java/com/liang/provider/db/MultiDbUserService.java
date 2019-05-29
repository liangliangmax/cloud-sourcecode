package com.liang.provider.db;

import com.liang.api.entity.User;
import com.liang.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MultiDbUserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    @Transactional
    public void add(){

        for (int i =0;i<5;i++){
            User user = new User();

            user.setId(i+"");
            user.setUsername(i+"");
            user.setPassword(i+"");

            userMapper.add(user);

//            if(i == 3){
//                throw new RuntimeException();
//            }
        }
    }

}
