package com.liang.service_c.controller;

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
    public ResponseEntity<String> addComment(@RequestBody Comment comment, HttpServletRequest request){

        Enumeration<String> enumeration =  request.getHeaderNames();

        if(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();

            String values = request.getHeader(name);

            System.out.println(name+" --> "+values);
        }

        try {
            //调用时候直接将服务停掉，事务会回滚
            //System.exit(-1);
            comment.setUserId("fdj8fj89sja9jf98d");
            commentService.addComment(comment);

        }catch (Exception e){
            e.printStackTrace();

            //这个status对feign的状态有影响，返回Status.INTERNAL_SERVER_ERROR的话调用方会捕获到错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

        return ResponseEntity.ok("ok");
    }

}
