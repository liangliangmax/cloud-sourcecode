package com.neupals.common.data_permission;

public interface ISqlFilter {

    // 判断是否需要过滤
    boolean needFilter(String id, String sql);

}
