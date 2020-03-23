package com.liang.seata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "seata_order")
public class Order {


    @Id()
    @Column(name = "o_id")
    private Integer id;

    @Column(name = "o_good_id")
    private Integer goodId;

    @Column(name = "o_account_id")
    private Integer accountId;

    @Column(name = "o_price")
    private Double price;
}