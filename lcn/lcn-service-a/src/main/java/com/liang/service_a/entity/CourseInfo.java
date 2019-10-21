package com.liang.service_a.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "course_info")
public class CourseInfo implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_info")
    private String courseInfo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }
}
