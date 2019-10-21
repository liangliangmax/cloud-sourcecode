package com.liang.service_a.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.liang.service_a.dao.CourseInfoMapper;
import com.liang.service_a.dao.CourseMapper;
import com.liang.service_a.entity.Course;
import com.liang.service_a.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;



    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody Course course) throws Throwable{

        try {
            courseService.addCourse(course);

        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

        return ResponseEntity.ok("ok");
    }

}
