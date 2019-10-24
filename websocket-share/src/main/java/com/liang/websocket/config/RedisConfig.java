package com.liang.websocket.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-23 8:33
 */
@ConditionalOnClass(name = {"org.springframework.data.redis.core.RedisOperations"})
@ConditionalOnProperty(prefix = "spring.redis",name = "enable",havingValue = "true")
@Configuration
public class RedisConfig {



    @Bean
    public RedisTemplate<String, Integer> intRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<String, Integer>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }


    @Bean
    @Primary
    public RedisTemplate<String, Object> objRedisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

    @Bean
    ValueOperations<String, String> strOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    ValueOperations<String, Integer> intOperations(RedisTemplate<String, Integer> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    ValueOperations<String, Object> objOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }
}
