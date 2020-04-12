package com.neupals.common.data_permission;

public interface ISqlFilter {

    // 判断是否需要过滤
    //输入类名称和方法名称，判断该方法是否需要被拦截
    boolean needFilter(String clazzName, String method);


}
