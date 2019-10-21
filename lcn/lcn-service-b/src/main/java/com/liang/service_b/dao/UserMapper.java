package com.liang.service_b.dao;

import com.liang.service_b.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list();

    int addUser(User user);
}
