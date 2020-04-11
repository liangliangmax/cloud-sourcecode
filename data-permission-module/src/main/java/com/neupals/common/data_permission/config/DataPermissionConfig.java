package com.neupals.common.data_permission.config;

import com.neupals.common.data_permission.IDataPermissionInterceptor;
import com.neupals.common.data_permission.interceptor.MsMsPermissionHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class DataPermissionConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private IDataPermissionInterceptor dataPermissionInterceptor;

    @PostConstruct
    public void addMyInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(dataPermissionInterceptor);
        }
    }
}
