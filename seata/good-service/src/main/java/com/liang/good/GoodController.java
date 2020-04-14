package com.liang.good;

import com.liang.feign.GoodClient;
import com.liang.good.service.GoodService;
import com.liang.seata.dto.RestApiResult;
import com.liang.seata.dto.ResultCode;
import com.liang.seata.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodController implements GoodClient {
    /**
     * 商品业务逻辑
     */
    @Autowired
    private GoodService goodService;

    /**
     * 查询商品信息
     *
     * @param goodId {@link Good#getId()}
     * @return
     */
    @Override
    public Goods findById(Integer goodId) {
        return goodService.findById(goodId);
    }

    /**
     * 查询商品信息
     *
     * @param goodId {@link Good#getId()}
     * @return
     */
    @Override
    public RestApiResult<Goods> findByIdRest(Integer goodId) {
        Goods goods = goodService.findById(goodId);
        return RestApiResult.OK(goods);
    }

    /**
     * 扣减商品库存
     *
     * @param goodId {@link Good#getId()}
     * @param stock  减少库存的数量
     */
    @Override
    public void reduceStock(Integer goodId, int stock) {
        goodService.reduceStock(goodId, stock);
    }

    /**
     * 扣减商品库存
     *
     * @param goodId {@link Good#getId()}
     * @param stock  减少库存的数量
     */
    @Override
    public RestApiResult<Boolean> reduceStockRest(Integer goodId, int stock) {
        try {
            goodService.reduceStock(goodId, stock);
            return RestApiResult.OK(true);
        }catch (Exception e){
            e.printStackTrace();
            return RestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }

    }
}