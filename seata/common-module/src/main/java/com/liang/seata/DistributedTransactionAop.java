package com.liang.seata;

import com.liang.seata.dto.RestApiResult;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DistributedTransactionAop {

    @Pointcut("execution(public * com.liang..*.service..*.*(..))")
    public void serviceDT(){}

    @Before(value = "serviceDT()")
    public void before(JoinPoint joinPoint) throws TransactionException {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("拦截到需要分布式事务的方法," + method.getName());
        // 此处可用redis或者定时任务来获取一个key判断是否需要关闭分布式事务
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        tx.begin(300000, "default-client");
        log.info("创建分布式事务完毕" + tx.getXid());
    }


    @AfterThrowing(throwing="ex",pointcut = "serviceDT()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) throws TransactionException {

        log.info("方法执行异常:{}", ex.getMessage());
        if (!StringUtils.isBlank(RootContext.getXID())) {
            log.info(RootContext.getXID()+"事务被回滚");
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }

    }

}
