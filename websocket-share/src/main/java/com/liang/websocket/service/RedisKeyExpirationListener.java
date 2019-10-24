package com.liang.websocket.service;


import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

    @Autowired
    private SocketIOServer server;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String redisKey = message.toString();
        logger.info("即将要失效的Key:" + redisKey);

        if(redisKey.startsWith("shareTable")){

            System.out.println("开始解锁");

            String teacherInfo = redisKey.replace("shareTable:","");

            Collection<SocketIOClient> clients = server.getAllClients();
            String key = teacherInfo.replaceFirst(":","_").replaceAll("-","").replaceAll(" ","").replaceAll(":","");
            System.out.println(key);

            for(SocketIOClient socketIOClient:clients){

                Map map = new HashMap();
                map.put("lockKey",key);
                socketIOClient.sendEvent("lock release", JSON.toJSONString(map));

            }
        }
    }


}
