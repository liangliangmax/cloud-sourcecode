package com.liang.order.service;

import com.liang.order.OrderMapper;
import com.liang.seata.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void addOrder(Order order) {
        orderMapper.insertSelective(order);
    }
}
