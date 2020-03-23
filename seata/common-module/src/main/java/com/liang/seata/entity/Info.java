package com.liang.seata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "info_des")
public class Info {

    @Id()
    @Column(name = "id")
    private String id;

    @Column(name = "des")
    private String des;
}
