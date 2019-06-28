package com.liang.provider.distributelock.keyGenerator;

import com.liang.api.entity.User;
import com.liang.provider.distributelock.annotation.CacheParam;
import com.liang.provider.distributelock.annotation.DistributeLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
public class DefaultLockKeyGenerator implements LockKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint pjp) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();

        //默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
        boolean useCacheParam = false;
        for (int i = 0; i < parameters.length; i++) {

            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            //如果被CacheParam标注了，则用标注的内容
            if (annotation != null) {
                builder.append(distributeLock.delimiter());

                //将获取的规则进行md5运算，这样可以减少key的长度，还可以保证数据一致
                String key = DigestUtils.md5DigestAsHex(annotation.name().getBytes());
                builder.append(key);

                useCacheParam = true;

            }

        }

        //如果参数列表没有被标注的，就从request中取一下requestId
        if(!useCacheParam){

            //如果没有标记，也没有requestId,则直接把参数都序列化当做key，这个key 可能会很大
            for (int i = 0; i < parameters.length; i++) {
                builder.append(distributeLock.delimiter());
                //将获取的规则进行md5运算，这样可以减少key的长度，还可以保证数据一致

                String key = DigestUtils.md5DigestAsHex(toByteArray(args[i]));
                builder.append(key);

            }

        }


        return distributeLock.prefix() + builder.toString();

    }


    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setPassword("123456");

        user.setUsername("123456");
        user.setAgeInt(123);

        System.out.println(DigestUtils.md5DigestAsHex(toByteArray(user)));
    }

}
