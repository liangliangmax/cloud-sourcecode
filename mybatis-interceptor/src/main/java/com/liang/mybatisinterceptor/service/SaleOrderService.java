package com.liang.mybatisinterceptor.service;

import com.github.pagehelper.PageHelper;
import com.liang.mybatisinterceptor.entity.SalesOrderDto;
import com.liang.mybatisinterceptor.mapper.SalesOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderService {

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    public int test(){

        SalesOrderDto salesOrderDto = new SalesOrderDto();
        salesOrderDto.setPostType("1");


        PageHelper.startPage(1,10);
        salesOrderMapper.listSalesOrders(salesOrderDto);

        return 1;
    }

}
