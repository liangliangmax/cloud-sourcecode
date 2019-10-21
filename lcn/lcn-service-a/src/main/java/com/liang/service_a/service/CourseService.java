package com.liang.service_a.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.liang.service_a.dao.CourseInfoMapper;
import com.liang.service_a.dao.CourseMapper;
import com.liang.service_a.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseInfoMapper courseInfoMapper;


    @LcnTransaction //分布式事务注解
    @Transactional
    public String addCourse(Course course) throws Exception{
        String id = UUID.randomUUID().toString();

        course.setId(id);

        course.getCourseInfo().setId(UUID.randomUUID().toString());

        course.getCourseInfo().setCourseId(course.getId());


        courseMapper.addCourse(course);

        int i = 1/0;
        courseInfoMapper.addCourseInfo(course.getCourseInfo());

        return "ok";
    }
}
