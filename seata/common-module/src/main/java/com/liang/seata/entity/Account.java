package com.liang.seata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Table(name = "seata_account")
public class Account {

    @Id
    @Column(name = "a_id")
    private Integer id;

    @Column(name = "a_money")
    private Double money;

    @Column(name = "a_create_time")
    private Timestamp createTime;

    @Column(name = "a_mark")
    private String mark;
}