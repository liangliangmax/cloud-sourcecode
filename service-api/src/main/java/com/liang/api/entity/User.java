package com.liang.api.entity;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    private String age;

    private int ageInt;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getAgeInt() {
        return ageInt;
    }

    public void setAgeInt(int ageInt) {
        this.ageInt = ageInt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", ageInt=" + ageInt +
                '}';
    }
}
