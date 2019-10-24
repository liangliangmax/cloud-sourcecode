package com.liang.websocket;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketEventHandler {

    private Map<String, Object> socketMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(SocketEventHandler.class);

    @Autowired
    private SocketIOServer server;

    int userNum = 0;

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

    @OnEvent(value = "add user")
    public void addUser(SocketIOClient client, AckRequest ackRequest, ChatMessage chat) {


        logger.info("接收到客户端消息");


        ++userNum;

        client.set("username",chat.getUsername());

        Map map1 = new HashMap();
        map1.put("numUsers",userNum);

        client.sendEvent("login",JSON.toJSONString(map1));

        Collection<SocketIOClient> allClients = server.getAllClients();

        for( SocketIOClient socket : allClients ) {
            if(socket == client) continue;

            Map map = new HashMap();
            map.put("username",chat.getUsername());
            map.put("numUsers",userNum);
            socket.sendEvent( "user joined", JSON.toJSONString(map) );
        }

    }

    // 消息接收入口
    @OnEvent(value = "new message")
    public void newMessage(SocketIOClient client, AckRequest ackRequest, ChatMessage chat) {
        logger.info("接收到客户端消息");
        Collection<SocketIOClient> allClients = server.getAllClients();

        for( SocketIOClient socket : allClients ) {
            if(socket == client) continue;

            Map map = new HashMap();
            map.put("username",chat.getUsername());
            map.put("message",chat.getMessage());
            socket.sendEvent( "new message", JSON.toJSONString(map));
        }
    }

    @OnEvent(value = "typing")
    public void typing(SocketIOClient client, AckRequest ackRequest) {
        logger.info("接收到客户端消息");
        Collection<SocketIOClient> allClients = server.getAllClients();

        for( SocketIOClient socket : allClients ) {
            if(socket == client) continue;
            Map map = new HashMap();
            map.put("username",client.get("username"));
            socket.sendEvent( "typing", JSON.toJSONString(map));
        }
    }

    @OnEvent(value = "stop typing")
    public void stopTyping(SocketIOClient client, AckRequest ackRequest) {
        logger.info("接收到客户端消息");
        Collection<SocketIOClient> allClients = server.getAllClients();

        for( SocketIOClient socket : allClients ) {
            if(socket == client) continue;
            Map map = new HashMap();
            map.put("username",client.get("username"));
            socket.sendEvent( "stop typing", JSON.toJSONString(map));
        }
    }


    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        client.disconnect();

        --userNum;
        Collection<SocketIOClient> allClients = server.getAllClients();
        for( SocketIOClient socket : allClients ) {
            if(socket == client) continue;
            Map map = new HashMap();
            map.put("username",client.get("username"));
            map.put("numUsers",userNum);
            socket.sendEvent( "user left", JSON.toJSONString(map));
        }
    }

}