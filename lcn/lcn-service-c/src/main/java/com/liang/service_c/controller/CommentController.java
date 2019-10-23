package com.liang.service_c.controller;

import com.liang.service_c.dto.NeuabcResult;
import com.liang.service_c.dto.ResultCode;
import com.liang.service_c.entity.Comment;
import com.liang.service_c.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PostMapping("/addComment")
    public ResponseEntity addComment(@RequestBody Comment comment, HttpServletRequest request){

        try {
            //调用时候直接将服务停掉，事务会回滚
            //System.exit(-1);
            comment.setUserId("fdj8fj89sja9jf98d");
            commentService.addComment(comment);
            int i = 1/0;

        }catch (Exception e){
            e.printStackTrace();

            //这个status对feign的状态有影响，返回Status.INTERNAL_SERVER_ERROR的话调用方会捕获到错误
            return NeuabcResult.error(ResultCode.ERROR,e.getMessage());

        }

        return NeuabcResult.ok("请求成功了");
    }

}
