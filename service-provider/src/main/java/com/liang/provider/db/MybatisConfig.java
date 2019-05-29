package com.liang.provider.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisConfig {

    @Autowired
    Environment environment;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    @Value("${spring.datasource.dam.url}")
    private String damUrl;

    @Value("${spring.datasource.dam.username}")
    private String damUsername;

    @Value("${spring.datasource.dam.password}")
    private String damPassword;

    @Value("${spring.datasource.neuabc.url}")
    private String neuabcUrl;

    @Value("${spring.datasource.neuabc.username}")
    private String neuabcUsername;

    @Value("${spring.datasource.neuabc.password}")
    private String neuabcPassword;


    /**
     * 创建 dam dataSource
     * @throws Exception
     */
    @Bean(name="dataSourceDam")
    public DataSource dataSourceDam() throws Exception{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(damUrl);
        dataSource.setUsername(damUsername);
        dataSource.setPassword(damPassword);

        return dataSource;
    }

    /**
     * 创建 neuabc dataSource
     * @throws Exception
     */
    @Bean(name="dataSourceNeuabc")
    public DataSource dataSourceNeuabc() throws Exception{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(neuabcUrl);
        dataSource.setUsername(neuabcUsername);
        dataSource.setPassword(neuabcPassword);

        return dataSource;
    }

    /**
     * 1、创建动态数据源
     * @throws Exception
     * @Primary该注解表示在同一个接口有多个类可以注入的时候，默认选择哪个，而不是让@Autowired报错
     */
    @Bean(name="dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("dataSourceDam") DataSource dataSourceDam,
                                        @Qualifier("dataSourceNeuabc") DataSource dataSourceNeuabc){
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DatabaseType.DAM, dataSourceDam);
        targetDataSource.put(DatabaseType.NEUABC, dataSourceNeuabc);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSourceNeuabc);

        return dataSource;
    }

    /**
     * 2、根据数据源创建SqlSessionFactory
     * @throws Exception
     */
//    @Bean(name="sessionFactory")
//    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDataSource")DynamicDataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactoryBean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapperLocations")));    //*Mapper.xml位置
//        return sessionFactoryBean.getObject();
//    }

    //提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dynamicDataSource")DynamicDataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);

        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapper-locations")));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }
}
