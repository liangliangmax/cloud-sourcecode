package com.liang.mybatisinterceptor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SqlFilterImpl implements ISqlFilter{

    private List<String> whiteList = new ArrayList<String>() {{
        add("AccountMapper.getUserInfo");
        add("AccountMapper.getEmployeeInfo");
        add("AccountMapper.getUserByloginName");

        add("SysdataBaseMapper.getmaxsysdataid");

    }};

    /**
     * 判断 是否 在白名单 里面 或者 是租户管理的查询sql
     * @param id
     * @param sql
     * @return
     */
    @Override
    public boolean needFilter(String id, String sql) {
        return true;
        //return !(whiteList.contains(id) || id.startsWith("com.ywsoftware.oa.mybatis.multitenant.TenantMapper"));
    }
}
