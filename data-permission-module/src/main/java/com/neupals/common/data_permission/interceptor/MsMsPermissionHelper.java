package com.neupals.common.data_permission.interceptor;

import com.neupals.common.data_permission.IDataPermissionInterceptor;
import com.neupals.common.data_permission.IGetPermissionIdColumn;
import com.neupals.common.data_permission.ISqlFilter;
import com.neupals.common.data_permission.util.JSqlParserUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class }) })
public class MsMsPermissionHelper implements Interceptor {

    @Autowired
    @Qualifier("msmsSqlFilter")
    private ISqlFilter sqlFilter;

    @Autowired
    @Qualifier("msmsGetPermissionIdColumn")
    private IGetPermissionIdColumn getPermissionIdColumn;

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

        System.out.println("---->开始拦截 "+id);

        if (sqlFilter.needFilter(id, sql)) {
            //获得方法类型
            String newSQL =  new JSqlParserUtil().build(getPermissionIdColumn).addWhere(sql);
            if (newSQL != null) {
                System.out.println(newSQL);
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
