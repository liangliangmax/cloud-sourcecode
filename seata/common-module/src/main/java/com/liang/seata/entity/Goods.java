package com.liang.seata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "seata_good")
public class Goods {

    @Id()
    @Column(name = "g_id")
    private Integer id;

    @Column(name = "g_name")
    private String name;

    @Column(name = "g_stock")
    private Integer stock;

    @Column(name = "g_price")
    private Double price;
}