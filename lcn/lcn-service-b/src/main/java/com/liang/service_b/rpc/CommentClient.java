package com.liang.service_b.rpc;

import com.liang.service_b.entity.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-c",fallbackFactory = CommentFallbackFactory.class)
public interface CommentClient {

    @PostMapping("/comment/addComment")
    ResponseEntity<String> addComment(@RequestBody Comment comment) throws Throwable;
}
