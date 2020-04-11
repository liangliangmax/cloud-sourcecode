package com.liang.mybatisinterceptor.mapper;

import com.liang.mybatisinterceptor.entity.Infodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Infodes> findUser(@Param("id") String id,@Param("des") String des);

}
