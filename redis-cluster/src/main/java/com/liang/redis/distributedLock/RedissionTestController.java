package com.liang.redis.distributedLock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redission")
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributedLocker distributedLocker;

    @RequestMapping("/init")
    public void init(){

        redissonClient.getBucket("result").set(1000);

    }


    @RequestMapping("/test2")
    public void test2(){

        RLock lock = redissonClient.getFairLock("aaa");
        try {
            lock.lock();
            Integer result = (Integer) redissonClient.getBucket("result").get();
            if(result-- >0){
                redissonClient.getBucket("result").set(result);
                System.out.println(result);
            }else {
                System.out.println("商品库存不够");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            lock.unlock();
        }


    }

    @RequestMapping("/test")
    public void redissonTest() {
        String key = "redisson_key";
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.err.println("=============线程开启============"+Thread.currentThread().getName());
					/*distributedLocker.lock(key,10L); //直接加锁，获取不到锁则一直等待获取锁
					 Thread.sleep(100); //获得锁之后可以进行相应的处理
					 System.err.println("======获得锁后进行相应的操作======"+Thread.currentThread().getName());
					 distributedLocker.unlock(key);  //解锁
					 System.err.println("============================="+Thread.currentThread().getName());*/
                        boolean isGetLock =  distributedLocker.tryLock(key, TimeUnit.SECONDS,5L,10L); //尝试获取锁，等待5秒，自己获得锁后一直不解锁则10秒后自动解锁
                        if(isGetLock){
                            Thread.sleep(100); //获得锁之后可以进行相应的处理
                            System.err.println("======获得锁后进行相应的操作======"+Thread.currentThread().getName());
                            //distributedLocker.unlock(key);
                            System.err.println("============================="+Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}
