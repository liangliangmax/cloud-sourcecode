package com.liang.provider;


import com.liang.api.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private AtomicLong atomicLong = new AtomicLong();

    /**
     * 测试list放入
     * 一个key相当于一张表，每个表中可以存很多行数据
     */
    @Test
    public void testPush(){

        for(int i = 0;i<5;i++){
            User user = new User();
            user.setId(atomicLong.addAndGet(1L)+"");
            user.setUsername("user"+i);
            user.setAge(i);

            redisTemplate.opsForList().leftPush("1",user);
            System.out.println("放到redis中了");
        }

    }

    //测试list 获取
    @Test
    public void testGet(){
        redisTemplate.opsForList().range("1",0,20).stream().forEach(System.out::println);
    }

    /**
     * 测试清空redis
     */
    @Test
    public void flushdb(){

        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    /**
     *测试存放map，一个key相当于一个对象，一张表可以存多个键值对，当key相同时会覆盖
     */
    @Test
    public void setMap(){

//        for (int i =0;i<5;i++){
//            User user = new User();
//            user.setId(atomicLong.addAndGet(1L)+"");
//            user.setUsername("user"+i);
//            user.setAge(i);
//
//            redisTemplate.opsForHash().put("userHash",user.getUsername(),user);
//        }

        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(uuid,"name","zhangsan");
        redisTemplate.opsForHash().put(uuid,"age","23");
        redisTemplate.opsForHash().put(uuid,"add","fsdfdsa");


        uuid = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(uuid,"name","lisi");
        redisTemplate.opsForHash().put(uuid,"age","22");
        redisTemplate.opsForHash().put(uuid,"add","1111");


    }

    /**
     * 测试map中获取所有的key
     */
    @Test
    public void getMap(){

        redisTemplate.opsForHash().entries("userHash").keySet().stream().forEach(System.out::println);
    }

    /**
     * 测试动态选择分片
     */
    @Test
    public void testDb(){

        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        lettuceConnectionFactory.setDatabase(4);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(uuid,"name","lisi");
        redisTemplate.opsForHash().put(uuid,"age","22");
        redisTemplate.opsForHash().put(uuid,"add","1111");

    }

    @Test
    public void testString(){
        redisTemplate.opsForValue().set("aaa:bbb:ccc","this is a : test");

        redisTemplate.opsForHash().put("aaa:bbb",1,"this is key test");

    }


    @Test
    public void testHash(){

        for (int i = 0;i<5;i++){
            User user = new User();
            user.setId(atomicLong.addAndGet(1L)+"");
            user.setUsername("zhangsan"+i);
            user.setAge(i);

            redisTemplate.opsForHash().put("users",user.hashCode(),user);
            System.out.println(redisTemplate.opsForHash().get("users",user.hashCode()));

        }

    }

    /**
     * 想要进行值加1
     * 使用RedisTemplate<String, Serializable> redisTemplate 这个模板在执行的时候回报错ERR value is not an integer or out of range
     * 使用了StringRedisSerializer就可以而使用默认序列化器就不行
     *
     * 使用StringRedisTemplate stringRedisTemplate就可以
     *
     * 测试方法如下
     *
     * 可以：将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
     * ValueOperations<String, String>  operations = template.opsForValue();
     * template.setKeySerializer(new StringRedisSerializer());
     * template.setValueSerializer(new StringRedisSerializer());
     * operations.set("StringRedisSerializer", "1");
     *
     * 可以：将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
     * template.setKeySerializer(new GenericToStringSerializer<String>(String.class));
     * template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
     * operations.set("GenericToStringSerializer", "1");
     *
     * 不可以：是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1
     * template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
     * template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
     * operations.set("GenericJackson2JsonRedisSerializer", "1");
     *
     * 不可以：是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1
     * template.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
     * template.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
     * operations.set("Jackson2JsonRedisSerializer", "1");
     *
     * 不可以：使用的jdk对象序列化，序列化后的值有类信息、版本号等，所以是一个包含很多字母的字符串，所以根本无法加1,
     * template.setKeySerializer(new JdkSerializationRedisSerializer());
     * template.setValueSerializer(new JdkSerializationRedisSerializer());
     * operations.set("JdkSerializationRedisSerializer", "1");
     *
     */
    @Test
    public void testSerializable(){

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        /**
         * Simple String to byte[] (and back) serializer. Converts Strings into bytes and vice-versa using the specified charset
         * (by default UTF-8).
         * <p>
         * Useful when the interaction with the Redis happens mainly through Strings.
         * <p>
         * Does not perform any null conversion since empty strings are valid keys/values.
         *
         */
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        operations.set("StringRedisSerializer", "1");
        operations.increment("StringRedisSerializer", 1);

        /**
         * Generic String to byte[] (and back) serializer. Relies on the Spring {@link ConversionService} to transform objects
         *  * into String and vice versa. The Strings are convert into bytes and vice-versa using the specified charset (by default
         *  * UTF-8). <b>Note:</b> The conversion service initialization happens automatically if the class is defined as a Spring
         *  * bean. <b>Note:</b> Does not handle nulls in any special way delegating everything to the container.
         */
        stringRedisTemplate.setKeySerializer(new GenericToStringSerializer<String>(String.class));
        stringRedisTemplate.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        operations.set("GenericToStringSerializer", "1");
        operations.increment("GenericToStringSerializer", 1);



        stringRedisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        operations.set("GenericJackson2JsonRedisSerializer", "1");
        operations.increment("GenericJackson2JsonRedisSerializer", 1);



        stringRedisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        stringRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        operations.set("Jackson2JsonRedisSerializer", "1");
        operations.increment("Jackson2JsonRedisSerializer", 1);


        stringRedisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        operations.set("JdkSerializationRedisSerializer", "1");
        operations.increment("JdkSerializationRedisSerializer", 1);


    }

    /**
     * 测试hash值加1
     */
    @Test
    public void testIncr(){
        stringRedisTemplate.opsForHash().put("user:111111","xihuan","5");

        stringRedisTemplate.opsForHash().increment("user:111111","xihuan",1);

        System.out.println(stringRedisTemplate.opsForHash().get("user:111111","xihuan"));

        stringRedisTemplate.opsForHash().put("user:222222","xihuan","56");

    }

    @Test
    public void testSet(){

        stringRedisTemplate.opsForSet().add("set:number","1","2","3","4");

        stringRedisTemplate.opsForSet().add("set:number5","1","2","5","6");

        //取属于第一个但不属于第二个的集合
        System.out.println(stringRedisTemplate.opsForSet().difference("set:number","set:number5"));

        //取并集
        System.out.println(stringRedisTemplate.opsForSet().union("set:number","set:number5"));

        //取并集并且保存
        System.out.println(stringRedisTemplate.opsForSet().unionAndStore("set:number","set:number5","set:number7"));

        //取交集
        System.out.println(stringRedisTemplate.opsForSet().intersect("set:number","set:number5"));

        //随机取一个成员
        System.out.println(stringRedisTemplate.opsForSet().randomMember("set:number"));

        Cursor<String> cursor = stringRedisTemplate.opsForSet().scan("set:number", ScanOptions.NONE);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    /**
     * 测试zset设置内容
     */
    @Test
    public void testZSet(){
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>("zset-5",9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<String>("zset-6",9.9);

        Set<ZSetOperations.TypedTuple<String>> tuples1 = new HashSet<ZSetOperations.TypedTuple<String>>();

        tuples1.add(objectTypedTuple1);
        tuples1.add(objectTypedTuple2);


        stringRedisTemplate.opsForZSet().add("zset:number1", tuples1);
        stringRedisTemplate.opsForZSet().add("zset:number2", tuples1);

        stringRedisTemplate.opsForZSet().range("zset:number1",0,5).stream().forEach(System.out::println);

        System.out.println(stringRedisTemplate.hasKey("zset:number1"));

    }


}
