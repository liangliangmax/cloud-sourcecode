package com.liang.consumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * feign转发时候需要将消息头中的所有字段进行转发
 */
@Slf4j
@Configuration
@ConditionalOnClass({RequestInterceptor.class})
public class FeignHeaderConfig implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if(attributes !=null){
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);

                    //log.info("正在处理的消息头是："+name+" 值为："+values);
                    //一下所有的字段是从request的header中取出的，别的都没影响，唯独加上Content-Length之后报错，
                    //这个value取出来是0，可能是这个0导致了发送的请求没有被正确处理，总是提示接受数据之前连接关闭
                    if(name.equalsIgnoreCase("Content-Length")
                            && values.equals("0")){

                        continue;
                    }
                    requestTemplate.header(name, values);

                }
            }
        }

    }

}
