#### 常用API
以下以RedisTemplate<String, String>为例

Key  
redisTemplate.

```java
keys(String pattern):Set<String>
hasKey(String k):Boolean
delete(String k)
expire(String k,long timeout,TimeUnit unit)
expireAt(String k,Date date)
getExpire(String k)
getExpire(String k,TimeUnit unit)
watch(String k)
watch(Collection<String> keys)
multi()
exec():List<Object>
discard()
```



String  
redisTemplate.opsForValue()

```java
set(String k, String v) :void
get(Object k) :String
getAndSet(String k, String v) :String
setIfAbsent(String k, String v) :Boolean //SETNX
increment(String k, long v) :Long //加v 返回运算后结果
increment(String k, double v) :Double //加v 返回运算后结果

```

List  
redisTemplate.opsForList()

```java
size(String k)
range(String k, long s, long e) :List<String> //返回list k的从s到e元素
leftPush(String k, String v) :Long  //还有对应的right版
leftPushIfPresent(String k, String v):Long
leftPop(String k) :String  //没有会阻塞
leftPop(String  key, long time, TimeUnit unit):String
set(String k,long index,String value)
remove(String k,long count,Object value):Long //移除count个，value相等的值，返回移除个数

```

Hash  
redisTemplate.opsForHash()

```java
put(String key, Object hashKey,Object value)
putIfAbsent(String key,Object hashKey,Object value):Boolean
delete(String key,Object... hashKeys):Long
get(String key, Object hashKey):Object
increment(String key,Object hashKey,long delta):Long  //还有Double版 返回计算结果
size(String key):Long
hasKey(key, hashKey):Boolean
keys(key):Set<Object>
values(key):List<Object>
entries(key):Map<Object,Object>

```


#### redis序列化

想要进行值加1  
使用RedisTemplate<String, Serializable> redisTemplate 这个模板在执行的时候回报错ERR value is not an integer or out of range
使用了StringRedisSerializer就可以而使用默认序列化器就不行

使用StringRedisTemplate stringRedisTemplate就可以

测试方法如下

* 可以：将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
```java
ValueOperations<String, String>  operations = template.opsForValue();
template.setKeySerializer(new StringRedisSerializer());
template.setValueSerializer(new StringRedisSerializer());
operations.set("StringRedisSerializer", "1");
```


* 可以：将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
```java
template.setKeySerializer(new GenericToStringSerializer<String>(String.class));
template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
operations.set("GenericToStringSerializer", "1");
```


* 不可以：是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1
```java
template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
operations.set("GenericJackson2JsonRedisSerializer", "1");
```


* 不可以：是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1
```java
template.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
template.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
operations.set("Jackson2JsonRedisSerializer", "1");
```


* 不可以：使用的jdk对象序列化，序列化后的值有类信息、版本号等，所以是一个包含很多字母的字符串，所以根本无法加1,
```java
template.setKeySerializer(new JdkSerializationRedisSerializer());
template.setValueSerializer(new JdkSerializationRedisSerializer());
operations.set("JdkSerializationRedisSerializer", "1");
```






