package com.liang.mybatisinterceptor;

public interface ISqlFilter {

    // 判断是否需要过滤
    boolean needFilter(String id,String sql);

}
