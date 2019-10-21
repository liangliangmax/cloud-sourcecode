package com.liang.service_b.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
public class User implements Serializable {

    @Id
    private String id;                      //主键uuid

    @Column(name = "username")
    private String username;               //登录账号

    @Column(name = "password")
    private String password;             //联系方式

    private UserParent userParent;

    private Course course;

    private Comment comment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserParent getUserParent() {
        return userParent;
    }

    public void setUserParent(UserParent userParent) {
        this.userParent = userParent;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}