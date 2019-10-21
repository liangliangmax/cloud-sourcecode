package com.liang.service_a.dao;

import com.liang.service_a.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseInfoMapper {

    int addCourseInfo(CourseInfo courseInfo);


}
