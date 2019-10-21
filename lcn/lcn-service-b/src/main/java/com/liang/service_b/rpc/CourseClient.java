package com.liang.service_b.rpc;

import com.liang.service_b.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-a",fallbackFactory = CourseFallbackFactory.class)
public interface CourseClient {

    @PostMapping("/course/addCourse")
    ResponseEntity<String> addCourse(@RequestBody Course course) throws Throwable;
}
