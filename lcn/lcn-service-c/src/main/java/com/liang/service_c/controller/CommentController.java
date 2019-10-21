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

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) throws Throwable{

        try {
            commentService.addComment(comment);

        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

        return ResponseEntity.ok("ok");
    }

}
