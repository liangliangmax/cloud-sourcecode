package com.liang.mongodbtut.test;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("demo")
public class DemoEntity {

    private String nickname;

    private String real_name;

    private Date course_time;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Date getCourse_time() {
        return course_time;
    }

    public void setCourse_time(Date course_time) {
        this.course_time = course_time;
    }
}
