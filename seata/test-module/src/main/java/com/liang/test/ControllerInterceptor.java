package com.liang.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 拦截器：记录用户操作日志，
 */
@Aspect
@Component
@Order(1)
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Pointcut("execution(public * com.liang..*.controller..*.*(..))")
    public void webLog(){}

    @Autowired
    private ObjectMapper objectMapper;

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        StringBuffer sb = getBuffer(joinPoint);

        if(sb!=null){
            // 记录下请求内容
            logger.info(sb.toString());
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) throws Throwable {
        // 处理完请求，返回内容
        StringBuffer sb = getBuffer(joinPoint);

        if(sb!=null){
            sb.append("  -》 执行结果【成功】");

            //logger.info("RESPONSE : " + ret);

            logger.info(sb.toString());
        }

    }

    @AfterThrowing(throwing="ex",pointcut = "webLog()")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable ex){
        StringBuffer sb = getBuffer(joinPoint);

        if(sb!=null){
            StackTraceElement stackTraceElement= ex.getStackTrace()[0];
            sb.append("  -》 执行结果【失败】 -》 错误消息【").append(ex.getMessage()).append("】")
                    .append("【line = ").append(stackTraceElement.getLineNumber()).append("】");

            logger.error(sb.toString());
        }

    }

    private String getParam(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        for (Object object : objects){

            if(object instanceof MultipartFile){
                sb.append("MultipartFile:"+object);
                continue;
            }else if(object instanceof ServletRequest
                    || object instanceof ServletResponse){

                sb.append("request:"+object);
                continue;
            }else {
                String str2 = JSON.toJSONString(object);
                sb.append(str2);
            }

        }

        try {
            objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }
        return sb.toString();
    }



    private StringBuffer getBuffer(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes !=null){
            HttpServletRequest request = attributes.getRequest();

        }

        StringBuffer sb = new StringBuffer();


        sb.append("类名称为【").append(joinPoint.getTarget().getClass()).append("】 -》 ");

        try {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();

            Method methodFromClass = joinPoint.getSignature().getDeclaringType().getDeclaredMethod(joinPoint.getSignature().getName(),signature.getParameterTypes());

            sb.append("方法名称为【").append(methodFromClass.getName()).append("】  -》 ");


        } catch (NoSuchMethodException e) {

            //没有这个方法的话说明是代理对象中的方法
            try {
                Method methodFromProxy = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName());
                sb.append("方法名称为【").append(methodFromProxy.getName()).append("】  -》 ");
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }

        sb.append("参数列表为【").append( getParam(joinPoint)).append("】 ");

        return sb;

    }
}