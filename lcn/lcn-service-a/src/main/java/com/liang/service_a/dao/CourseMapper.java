package com.liang.service_a.dao;

import com.liang.service_a.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    int addCourse(Course course);
}
