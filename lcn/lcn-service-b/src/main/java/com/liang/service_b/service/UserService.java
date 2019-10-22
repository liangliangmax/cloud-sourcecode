package com.liang.service_b.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.liang.service_b.dao.UserMapper;
import com.liang.service_b.dao.UserParentMapper;
import com.liang.service_b.entity.Course;
import com.liang.service_b.entity.User;
import com.liang.service_b.rpc.CommentClient;
import com.liang.service_b.rpc.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserParentMapper userParentMapper;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private CommentClient commentClient;


    @LcnTransaction //分布式事务注解
    @Transactional
    public String addUser(User user) throws Exception{

        String id = UUID.randomUUID().toString();
        user.setId(id);

        user.getUserParent().setId(UUID.randomUUID().toString());
        user.getCourse().setUserId(user.getId());
        user.getUserParent().setUserId(user.getId());
        user.getComment().setUserId(id);

        try {

            userMapper.addUser(user);

            ResponseEntity<String> responseEntity = courseClient.addCourse(user.getCourse());
            responseEntity.getBody();

            commentClient.addComment(user.getComment());
            userParentMapper.addParent(user.getUserParent());

        }catch (Throwable e){
            System.out.println("我在抛异常这里");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return "ok";

    }
}
