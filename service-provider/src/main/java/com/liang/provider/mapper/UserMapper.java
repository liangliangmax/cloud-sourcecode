package com.liang.provider.mapper;

import com.liang.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    void add(User user);
}
