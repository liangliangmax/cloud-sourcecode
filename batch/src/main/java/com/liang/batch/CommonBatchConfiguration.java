package com.liang.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CommonBatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CommonBatchConfiguration.class);

    @Bean
    public JobRepositoryFactoryBean jobRepositoryFactoryBean(DataSource dataSource, PlatformTransactionManager transactionManager){
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        try {
            jobRepositoryFactoryBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("创建jobRepositoryFactoryBean异常：{}",e);
        }
        return  jobRepositoryFactoryBean;
    }

    @Bean
    public JobRepository jobRepository(JobRepositoryFactoryBean jobRepositoryFactoryBean){
        JobRepository jobRepository = null;
        try {
            jobRepository = jobRepositoryFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("创建jobRepository异常{}",e);
        }
        return jobRepository;
    }

    @Bean
    public SimpleJobLauncher simpleJobLauncher(JobRepository jobRepository){
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        try {
            simpleJobLauncher.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("创建simpleJobLauncher异常：{}",e);
        }
        return simpleJobLauncher;
    }

    @Bean
    public RunIdIncrementer runIdIncrementer(){
        return new RunIdIncrementer();
    }

}
