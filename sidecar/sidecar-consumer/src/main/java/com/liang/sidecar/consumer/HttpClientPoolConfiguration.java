package com.liang.sidecar.consumer;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * feign提供了一个httpclient的连接池，但是好像没有生效，所以需要一个配置类让连接池生效
 */
@Slf4j
@Configuration
@ConditionalOnClass(Feign.class)
public class HttpClientPoolConfiguration {

    @Autowired
    private FeignHttpClientProperties feignHttpClientProperties;

    @Bean
    public HttpClient httpClient(){

        log.info("init feign httpclient configuration " );
        // 生成默认请求配置
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // 超时时间
        requestConfigBuilder.setSocketTimeout(5 * 1000);
        // 连接时间
        requestConfigBuilder.setConnectTimeout(feignHttpClientProperties.getConnectionTimeout());
        RequestConfig defaultRequestConfig = requestConfigBuilder.build();
        // 连接池配置
        // 长连接保持30秒
        final PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(feignHttpClientProperties.getTimeToLive(), feignHttpClientProperties.getTimeToLiveUnit());
        // 总连接数
        pollingConnectionManager.setMaxTotal(feignHttpClientProperties.getMaxConnections());
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(feignHttpClientProperties.getMaxConnectionsPerRoute());

        // httpclient 配置
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
        HttpClient client = httpClientBuilder.build();


        // 启动定时器，定时回收过期的连接
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //        System.out.println("=====closeIdleConnections===");
                pollingConnectionManager.closeExpiredConnections();
                pollingConnectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
            }
        }, 10 * 1000, 5 * 1000);

        log.info("===== Apache httpclient 初始化连接池===");

        return client;
    }
}
