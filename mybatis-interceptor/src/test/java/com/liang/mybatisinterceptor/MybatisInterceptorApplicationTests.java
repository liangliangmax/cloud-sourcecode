package com.liang.mybatisinterceptor;

import com.liang.mybatisinterceptor.entity.SalesOrderDto;
import com.liang.mybatisinterceptor.mapper.SalesOrderMapper;
import com.liang.mybatisinterceptor.mapper.UserMapper;
import com.liang.mybatisinterceptor.service.SaleOrderService;
import com.liang.mybatisinterceptor.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisInterceptorApplication.class)
public class MybatisInterceptorApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private UserService userService;



    @Test
    public void test(){
        System.out.println(userService.findUser("121",""));
    }

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SaleOrderService saleOrderService;

    @Test
    public void test3(){
        System.out.println(saleOrderService.test());
    }

    @Test
    public void test2(){

        SalesOrderDto salesOrderDto = new SalesOrderDto();
        salesOrderDto.setPostType("1");
        System.out.println(salesOrderMapper.listSalesOrders(salesOrderDto));
    }
}
