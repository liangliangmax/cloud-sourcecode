package com.liang.good;

import com.liang.seata.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodService {

    @Autowired
    private GoodMapper goodMapper;

    /**
     * 查询商品详情
     *
     * @param goodId {@link Good#getId()}
     * @return {@link Good}
     */
    public Goods findById(Integer goodId) {
        return goodMapper.selectByPrimaryKey(goodId);
    }

    /**
     * {@link EnhanceMapper} 具体使用查看ApiBoot官网文档http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html
     * 扣除商品库存
     *
     * @param goodId {@link Good#getId()}
     * @param stock  扣除的库存数量
     */
    public void reduceStock(Integer goodId, int stock) {
        Goods good = goodMapper.selectByPrimaryKey(goodId);
        if (ObjectUtils.isEmpty(good)) {
            throw new RuntimeException("商品：" + goodId + ",不存在.");
        }
        if (good.getStock() - stock < 0) {
            throw new RuntimeException("商品：" + goodId + "库存不足.");
        }
        good.setStock(good.getStock() - stock);
        goodMapper.updateByPrimaryKeySelective(good);

        int i = 1/0;

    }
}