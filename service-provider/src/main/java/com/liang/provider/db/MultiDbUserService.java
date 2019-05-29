package com.liang.provider.db;

import com.liang.api.entity.User;
import com.liang.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiDbUserService {

    @Autowired
    private UserMapper userMapper;

    public void setDataSourceByEnvironment(String environment){
        if (environment.equals(DatabaseType.DAM.getValue())){
            DatabaseContextHolder.setDatabaseType(DatabaseType.DAM);
        }
        if (environment.equals(DatabaseType.NEUABC.getValue())){
            DatabaseContextHolder.setDatabaseType(DatabaseType.NEUABC);
        }
    }


    public List<User> selectAll(String environment){
        setDataSourceByEnvironment(environment);
        return userMapper.selectAll();
    }

}
