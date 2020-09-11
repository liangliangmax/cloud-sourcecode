package com.liang.batch;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest

@RunWith(SpringRunner.class)
class BatchApplicationTests {

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    @Qualifier("userJob")
    private Job userJob;

    @Test
    void contextLoads() {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();


        //这个参数加了但是后面不用，是为了防止不传参数的时候springbatch认为几次执行的参数都一样，执行一遍就不执行了
        jobParametersBuilder.addString("now", System.currentTimeMillis()+"");

        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        try {
            jobLauncher.run(userJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            System.out.println(e.getCause());
        } catch (JobRestartException e) {
            System.out.println(e.getCause());
        } catch (JobInstanceAlreadyCompleteException e) {
            System.out.println(e.getCause());
        } catch (JobParametersInvalidException e) {
            System.out.println(e.getCause());
        }
    }

}
