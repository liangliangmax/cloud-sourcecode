package com.liang.mybatisinterceptor.entity;

public class Infodes {

    private String id;

    private String des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    @Override
    public String toString() {
        return "Infodes{" +
                "id='" + id + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
