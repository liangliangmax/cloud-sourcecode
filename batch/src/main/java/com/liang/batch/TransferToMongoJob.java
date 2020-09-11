package com.liang.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class TransferToMongoJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    //注入数据读取器
    @Autowired
    @Qualifier("userJdbcPagingItemReader")
    private ItemReader<User> userJdbcPagingItemReader;





    //////////////////////////////////////////////////////////////////////

    //第二步，根据时间范围进行数据初始化迁移
    @Bean
    public Step readUserStep() {
        return stepBuilderFactory.get("readUserStep")
                .<User,User>chunk(2)
                .reader(userJdbcPagingItemReader)
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(1)

                .processor((Function<? super User, ? extends User>) user -> {
                    System.out.println("---->"+user);
                    return user;
                })

                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(1)

                .writer(list -> System.out.println(list))
                .taskExecutor(new SimpleAsyncTaskExecutor("importHisCourse")) //开启多线程模式执行
                .throttleLimit(6)  //核心线程数
                .build();
    }




    /////////////////////////将上面的具体的每一步组合成一个job/////////////////////////////////////


    /**
     * 通过onlyId进行数据迁移
     * 一般是自动执行
     * @return
     */
    @Bean("userJob")
    public Job userJob(){
        return jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer())
                .start(readUserStep())  //根据onlyId将数据先删除
                .build();
    }



}
