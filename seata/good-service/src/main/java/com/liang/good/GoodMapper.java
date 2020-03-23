package com.liang.good;

import com.liang.seata.entity.Goods;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface GoodMapper  extends Mapper<Goods>, MySqlMapper<Goods> {
}
