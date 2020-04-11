package com.liang.mybatisinterceptor.entity;


import java.util.Date;
import java.util.List;


public class SalesOrderDto extends SalesOrder {


    private List<SalesOrderDetail> goodsList;


    private List<SalesOrderDetail> giftList;


    //////查询条件

    //下单时间   开始
    private Date salesOrderDateStart;

    //下单时间   结束
    private Date salesOrderDateEnd;

    private String postType;


    public List<SalesOrderDetail> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<SalesOrderDetail> goodsList) {
        this.goodsList = goodsList;
    }

    public List<SalesOrderDetail> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<SalesOrderDetail> giftList) {
        this.giftList = giftList;
    }

    public Date getSalesOrderDateStart() {
        return salesOrderDateStart;
    }

    public void setSalesOrderDateStart(Date salesOrderDateStart) {
        this.salesOrderDateStart = salesOrderDateStart;
    }

    public Date getSalesOrderDateEnd() {
        return salesOrderDateEnd;
    }

    public void setSalesOrderDateEnd(Date salesOrderDateEnd) {
        this.salesOrderDateEnd = salesOrderDateEnd;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
