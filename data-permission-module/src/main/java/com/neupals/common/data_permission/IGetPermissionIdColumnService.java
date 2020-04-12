package com.neupals.common.data_permission;

public interface IGetPermissionIdColumnService {

    //数据权限控制的列
    String getCheckColumn();

    //数据权限列对应的值
    String getCheckValue();
}
