package com.neupals.common.data_permission.info;

import com.alibaba.fastjson.JSON;
import com.neupals.common.data_permission.IGetPermissionIdColumnService;
import com.neupals.common.data_permission.constant.RequestHeaderConstant;
import com.neupals.common.dto.RestException;
import com.neupals.common.dto.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ms系统对应的权限控制列及其赋值
 */
@Component("msmsGetPermissionIdColumn")
public class MsMsGetPermissionIdColumnImpl implements IGetPermissionIdColumnService {


    @Override
    public String getCheckColumn() {
        return RequestHeaderConstant.USER_ID_COLUMN;
    }

    @Override
    public String getCheckValue() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String userId = request.getHeader(RequestHeaderConstant.USER_ID);

        List<String> roles = JSON.parseArray(request.getHeader(RequestHeaderConstant.ROLES),String.class);



        if(StringUtils.isBlank(userId))
            throw new RestException(ResultCode.ERROR,"无法验证身份，请联系管理员");

        return userId;
    }
}





















