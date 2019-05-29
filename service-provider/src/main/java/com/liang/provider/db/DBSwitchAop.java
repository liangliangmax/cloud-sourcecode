package com.liang.provider.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 拦截器：记录用户操作日志，
 */
@Aspect
@Component
@Order(1)
public class DBSwitchAop {
    private static final Logger logger = LoggerFactory.getLogger(DBSwitchAop.class);

    @Pointcut("execution(public * com.liang.*.controller..*.*(..))")
    public void dbSwitch(){}


    @AfterThrowing(throwing="ex",pointcut = "dbSwitch()")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable ex){

    }

    @Before("dbSwitch()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        System.out.println("开始进行数据源拦截");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String env = request.getHeader("env");

        System.out.println(env);

        setDataSourceByEnvironment(env);

    }


    private void setDataSourceByEnvironment(String environment){
        if (DatabaseType.DAM.getValue().equals(environment)){
            DatabaseContextHolder.setDatabaseType(DatabaseType.DAM);
        }
        if (DatabaseType.NEUABC.getValue().equals(environment)){
            DatabaseContextHolder.setDatabaseType(DatabaseType.NEUABC);
        }
    }

}