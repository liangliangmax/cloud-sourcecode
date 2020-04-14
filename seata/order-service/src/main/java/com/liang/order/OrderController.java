package com.liang.order;

import com.liang.feign.AccountClient;
import com.liang.feign.GoodClient;
import com.liang.order.service.OrderService;
import com.liang.seata.dto.RestApiResult;
import com.liang.seata.dto.ResultCode;
import com.liang.seata.entity.Goods;
import com.liang.seata.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    /**
     * 账户服务接口
     */
    @Autowired
    private AccountClient accountClient;
    /**
     * 商品服务接口
     */
    @Autowired
    private GoodClient goodClient;
    /**
     * 订单业务逻辑
     */
    @Autowired
    private OrderService orderService;

    /**
     * 通过{@link GoodClient#reduceStock(Integer, int)}方法减少商品的库存，判断库存剩余数量
     * 通过{@link AccountClient#deduction(Integer, Double)}方法扣除商品所需要的金额，金额不足由account-service抛出异常
     *
     * @param goodId    {@link Good#getId()}
     * @param accountId {@link Account#getId()}
     * @param buyCount  购买数量
     * @return
     */
    @PostMapping(value = "/submitOrder")
    @GlobalTransactional
    public String submitOrder(
            @RequestParam("goodId") Integer goodId,
            @RequestParam("accountId") Integer accountId,
            @RequestParam("buyCount") int buyCount) {

        Goods good = goodClient.findById(goodId);

        Double orderPrice = buyCount * good.getPrice();

        accountClient.deduction(accountId, orderPrice);

        //会库存不够
        for(int i = 0;i<20;i++){
            goodClient.reduceStock(goodId, buyCount);
        }


        Order order = toOrder(goodId, accountId, orderPrice);
        orderService.addOrder(order);
        return "下单成功.";
    }

    /**
     * 测试一下rest的回滚
     *
     * 通过{@link GoodClient#reduceStock(Integer, int)}方法减少商品的库存，判断库存剩余数量
     * 通过{@link AccountClient#deduction(Integer, Double)}方法扣除商品所需要的金额，金额不足由account-service抛出异常
     *
     * @param goodId    {@link Good#getId()}
     * @param accountId {@link Account#getId()}
     * @param buyCount  购买数量
     * @return
     */
    @PostMapping(value = "/submitOrderRest")
    @GlobalTransactional
    public RestApiResult<String> submitOrderRest(
            @RequestParam("goodId") Integer goodId,
            @RequestParam("accountId") Integer accountId,
            @RequestParam("buyCount") int buyCount) {

        RestApiResult<Goods> goodsRestApiResult = goodClient.findByIdRest(goodId);

        if(goodsRestApiResult.isSuccess()){

            Double orderPrice = buyCount * goodsRestApiResult.getData().getPrice();

            Order order = toOrder(goodId, accountId, orderPrice);
            orderService.addOrder(order);

            //会库存不够
            //for(int i = 0;i<20;i++){
            RestApiResult<Boolean> booleanRestApiResult = goodClient.reduceStockRest(goodId, buyCount);
            if(!booleanRestApiResult.isSuccess()){
                return RestApiResult.ERROR(booleanRestApiResult.getCode(),booleanRestApiResult.getMessage());
            }
            //}

            RestApiResult<Boolean> booleanRestApiResult1 = accountClient.deductionRest(accountId, orderPrice);
            if(!booleanRestApiResult1.isSuccess()){
                return RestApiResult.ERROR(booleanRestApiResult1.getCode(),booleanRestApiResult1.getMessage());
            }

            return RestApiResult.OK("下单成功");
        }

        return RestApiResult.ERROR(ResultCode.ERROR,"下单失败");

    }

    private Order toOrder(Integer goodId, Integer accountId, Double orderPrice) {
        Order order = new Order();
        order.setGoodId(goodId);
        order.setAccountId(accountId);
        order.setPrice(orderPrice);
        return order;
    }
}