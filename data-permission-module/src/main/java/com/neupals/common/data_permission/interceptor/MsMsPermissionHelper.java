package com.neupals.common.data_permission.interceptor;

import com.neupals.common.data_permission.IDataPermissionInterceptor;
import com.neupals.common.data_permission.IGetPermissionIdColumnService;
import com.neupals.common.data_permission.ISqlFilter;
import com.neupals.common.data_permission.annotation.DataPermission;
import com.neupals.common.data_permission.util.JSqlParserUtil;
import com.neupals.common.util.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义的sql拦截器，
 */
@Slf4j
@Component
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class }) })
public class MsMsPermissionHelper implements IDataPermissionInterceptor {

    @Autowired
    private ISqlFilter sqlFilter;

    @Autowired
    @Qualifier("msmsGetPermissionIdColumn")
    private IGetPermissionIdColumnService getPermissionIdColumn;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        //由于mappedStatement中有我们需要的方法id,但却是protected的，所以要通过反射获取
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
        //获取sql
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        //获取方法id
        String id = mappedStatement.getId();

        log.info("需要进行数据级权限拦截的sql为：{}",id);

        String clazzName = id.substring(0,id.lastIndexOf("."));

        String method = id.substring(id.lastIndexOf(".")+1);

        if(sqlFilter.needFilter(clazzName,method)){

            JSqlParserUtil jSqlParserUtil = new JSqlParserUtil();

            //还有一种是pagehelper发起的查询数量的请求，这种方法上面不能被@DataPermission标记，
            // 但是这种方法有个规律，就是在原始的方法名称后面加个_COUNT,
            // 所以去掉_COUNT之后，这个方法被@DataPermission标记了，也是需要呗处理的
            if(method.contains("_COUNT")){
                String guessMethod = method.substring(0,method.lastIndexOf("_"));
                DataPermission dataPermission = (DataPermission) AnnotationUtil.getAnnotation(clazzName, guessMethod, DataPermission.class);

                //取出方法上的注解，如果有默认值，则给默认值，如果没有默认值，就调用IGetPermissionIdColumnService接口的实现类注入值
                if(dataPermission !=null && StringUtils.isNotBlank(dataPermission.checkColumn()) && StringUtils.isNotBlank(dataPermission.checkValue())){
                    jSqlParserUtil.addColumn(dataPermission.checkColumn());
                    jSqlParserUtil.addValue(dataPermission.checkValue());
                }else {
                    jSqlParserUtil.addColumn(getPermissionIdColumn.getCheckColumn());
                    jSqlParserUtil.addValue(getPermissionIdColumn.getCheckValue());
                }

            }else {
                DataPermission dataPermission = (DataPermission) AnnotationUtil.getAnnotation(clazzName, method, DataPermission.class);

                //取出方法上的注解，如果有默认值，则给默认值，如果没有默认值，就调用IGetPermissionIdColumnService接口的实现类注入值
                if(dataPermission !=null && StringUtils.isNotBlank(dataPermission.checkColumn()) && StringUtils.isNotBlank(dataPermission.checkValue())){
                    jSqlParserUtil.addColumn(dataPermission.checkColumn());
                    jSqlParserUtil.addValue(dataPermission.checkValue());
                }else {
                    jSqlParserUtil.addColumn(getPermissionIdColumn.getCheckColumn());
                    jSqlParserUtil.addValue(getPermissionIdColumn.getCheckValue());
                }
            }

            String newSQL =  jSqlParserUtil.addWhere(sql);
            if (newSQL != null) {
                log.info("处理完成后的句子为{}",newSQL);
                statementHandler.setValue("delegate.boundSql.sql", newSQL);
            }
        }

        return invocation.proceed();

    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(1111111);
    }
}
