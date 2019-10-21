package com.liang.service_b.dao;

import com.liang.service_b.entity.UserParent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserParentMapper {

    int addParent(UserParent userParent);
}
