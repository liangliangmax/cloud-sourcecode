package com.liang.provider.distributelock.interceptor;


import com.alibaba.fastjson.JSON;
import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.ResultCode;
import com.liang.provider.distributelock.RedisLockHelper;
import com.liang.provider.distributelock.annotation.DistributeLock;
import com.liang.provider.distributelock.keyGenerator.LockKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Configuration
public class LockMethodInterceptor {


    @Autowired
    public LockMethodInterceptor(RedisLockHelper redisLockHelper, LockKeyGenerator lockKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.lockKeyGenerator = lockKeyGenerator;
    }

    private final RedisLockHelper redisLockHelper;
    private final LockKeyGenerator lockKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.liang.provider.distributelock.annotation.DistributeLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        DistributeLock lock = method.getAnnotation(DistributeLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = lockKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            System.out.println(lockKey);
            System.out.println(value);
            final boolean success = redisLockHelper.tryLock(lockKey, value, lock.expire(), lock.timeUnit());
            if (!success) {
                throw new RuntimeException("重复提交");
            }
            try {
                Thread.sleep(10000);

                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSONString(NeuabcRestApiResult.ERROR(ResultCode.ERROR,"重复提交"));
        }finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            redisLockHelper.releaseLock(lockKey, value);
        }
    }


}
