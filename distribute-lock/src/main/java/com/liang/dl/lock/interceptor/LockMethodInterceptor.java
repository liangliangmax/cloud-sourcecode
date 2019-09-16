package com.liang.dl.lock.interceptor;


import com.alibaba.fastjson.JSON;
import com.liang.dl.NeuabcRestApiResult;
import com.liang.dl.ResultCode;
import com.liang.dl.lock.RedisLockHelper;
import com.liang.dl.lock.annotation.DistributeLock;
import com.liang.dl.lock.keyGenerator.LockKeyGenerator;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

@Slf4j
@Aspect
@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class LockMethodInterceptor {


    @Autowired
    public LockMethodInterceptor(RedisLockHelper redisLockHelper, LockKeyGenerator lockKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.lockKeyGenerator = lockKeyGenerator;
    }

    private final RedisLockHelper redisLockHelper;
    private final LockKeyGenerator lockKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.liang.dl.lock.annotation.DistributeLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        DistributeLock lock = method.getAnnotation(DistributeLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key can not be null...");
        }
        final String lockKey = lockKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();

        // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
        System.out.println(lockKey);
        System.out.println(value);
        final boolean success = redisLockHelper.tryLock(lockKey, value, lock.expire(), lock.timeUnit());
        if (success) {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable.getMessage());
            }finally {
                // TODO 如果演示的话需要注释该代码;实际应该放开
                redisLockHelper.releaseLock(lockKey, value);
                log.info(lockKey+"被释放了");
            }
        }else {
            return JSON.toJSONString(NeuabcRestApiResult.ERROR(ResultCode.ERROR,"重复提交"));
        }


    }


}
