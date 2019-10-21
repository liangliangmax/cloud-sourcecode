package com.liang.service_b.rpc;

import com.liang.service_b.entity.Comment;
import com.liang.service_b.entity.Course;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentFallbackFactory implements FallbackFactory<CommentClient> {
    @Override
    public CommentClient create(Throwable cause) {
        return new CommentClient() {
            @Override
            public ResponseEntity<String> addComment(Comment comment) throws Throwable {
                throw cause;
            }
        };
    }
}
