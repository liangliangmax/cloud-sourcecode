package com.neupals.common.data_permission.info;

import com.neupals.common.data_permission.IGetPermissionIdColumn;
import org.springframework.stereotype.Component;

/**
 * ms系统对应的权限控制列及其赋值
 */
@Component("msmsGetPermissionIdColumn")
public class MsMsGetPermissionIdColumnImpl implements IGetPermissionIdColumn {


    @Override
    public String getPermissionIdColumn() {
        return "create_user_id";
    }

    @Override
    public String getPermissionId() {
        return "1";
    }
}
