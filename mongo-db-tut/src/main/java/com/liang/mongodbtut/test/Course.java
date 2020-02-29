package com.liang.mongodbtut.test;

import java.util.Date;

public class Course {

    private String id;

    private String courseName;

    private String level;

    private Date courseTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Date courseTime) {
        this.courseTime = courseTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", level='" + level + '\'' +
                ", courseTime=" + courseTime +
                '}';
    }
}
