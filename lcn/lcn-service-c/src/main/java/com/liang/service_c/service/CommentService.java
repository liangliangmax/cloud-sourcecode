package com.liang.service_c.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.liang.service_c.dao.CommentInfoMapper;
import com.liang.service_c.dao.CommentMapper;
import com.liang.service_c.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentInfoMapper commentInfoMapper;


    @LcnTransaction //分布式事务注解
    @Transactional
    public String addComment(Comment comment) throws Exception{
        String id = UUID.randomUUID().toString();

        comment.setId(id);

        comment.getCommentInfo().setId(UUID.randomUUID().toString());

        comment.getCommentInfo().setCommentId(comment.getId());


        commentMapper.addComment(comment);

        commentInfoMapper.addCommentInfo(comment.getCommentInfo());

        return "ok";
    }
}
