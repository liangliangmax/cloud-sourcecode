package com.liang.account;

import com.liang.seata.entity.Account;
import com.liang.seata.entity.Info;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface InfoMapper extends Mapper<Info>, MySqlMapper<Info> {
}
