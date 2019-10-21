package com.liang.service_b.rpc;

import com.liang.service_b.entity.Course;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseFallbackFactory implements FallbackFactory<CourseClient> {
    @Override
    public CourseClient create(Throwable cause) {
        return new CourseClient() {
            @Override
            public ResponseEntity<String> addCourse(Course course) throws Throwable {
                System.out.println(cause);
                throw cause;
            }
        };
    }
}
