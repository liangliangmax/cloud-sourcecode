package com.neupals.common.data_permission.filter;


import com.neupals.common.data_permission.ISqlFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("msmsSqlFilter")
public class MsMsSqlFilterImpl implements ISqlFilter {


    /**
     * 判断 是否 在白名单 里面 或者 是租户管理的查询sql
     * @param id
     * @param sql
     * @return
     */
    @Override
    public boolean needFilter(String id, String sql) {


        return true;
    }
}
