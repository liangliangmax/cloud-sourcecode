package com.liang.order;

import com.liang.seata.entity.Order;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends Mapper<Order>, MySqlMapper<Order> {
}
