package com.liang.websocket.entity;

import java.io.Serializable;

public class LockInfo implements Serializable {

    private String teacherId;

    private String time;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
