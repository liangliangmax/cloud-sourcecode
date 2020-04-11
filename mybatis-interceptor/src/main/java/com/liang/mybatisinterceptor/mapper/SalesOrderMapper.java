package com.liang.mybatisinterceptor.mapper;


import com.liang.mybatisinterceptor.entity.SalesOrder;
import com.liang.mybatisinterceptor.entity.SalesOrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesOrderMapper {

    List<SalesOrder> listSalesOrders(SalesOrderDto order);

}