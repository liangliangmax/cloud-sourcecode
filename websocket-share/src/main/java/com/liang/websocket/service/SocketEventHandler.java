package com.liang.websocket.service;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.liang.websocket.entity.LockInfo;
import com.liang.websocket.util.RedisLockHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SocketEventHandler {

    private Map<String, Object> socketMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(SocketEventHandler.class);

    @Autowired
    private SocketIOServer server;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisLockHelper redisLockHelper;

    private int userNum = 0;

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            String sessionId = client.getSessionId().toString();
            logger.info("连接成功, sessionId=" + sessionId);
        } else {
            logger.error("客户端为空");
        }
    }

    @OnEvent(value = "try locking")
    public void tryLock(SocketIOClient client, AckRequest ackRequest, LockInfo lockInfo) {

        logger.info("接收到客户端消息");

        String lockKey = "shareTable:"+lockInfo.getTeacherId()+":"+lockInfo.getTime();
        boolean lock = redisLockHelper.tryLock(lockKey,client.getSessionId().toString(),60, TimeUnit.SECONDS);

        if(lock){
            System.out.println("被上锁了");
            Collection<SocketIOClient> clients = server.getAllClients();

            for(SocketIOClient socketIOClient:clients){
                if(true || client != socketIOClient){
                    Map map = new HashMap();
                    map.put("lockKey",lockInfo.getTeacherId()+"_"+lockInfo.getTime().replaceAll("-","").replaceAll(" ","").replaceAll(":",""));
                    socketIOClient.sendEvent("lock success",JSON.toJSONString(map));
                }
            }

        }else {
            System.out.println("上锁失败");
        }

    }





    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        client.disconnect();


    }

}