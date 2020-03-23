package com.liang.order;

import com.liang.seata.entity.Order;
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
