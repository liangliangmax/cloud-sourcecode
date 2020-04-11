package com.liang.mybatisinterceptor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "sales_order_detail")
public class SalesOrderDetail {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 订单编号
     */
    @Column(name = "sales_order_bn")
    private String salesOrderBn;

    /**
     * 明细序号
     */
    @Column(name = "list_no")
    private String listNo;

    /**
     * 订单区分(1.销售商品、2.赠品明细)
     *
     */
    @Column(name = "list_category")
    private String listCategory;

    /**
     * 杂志类型
     */
    @Column(name = "magazine_type")
    private String magazineType;

    /**
     * 杂志名称
     */
    @Column(name = "magazine_type_name")
    private String magazineTypeName;

    /**
     * 邮寄类型
     */
    @Column(name = "post_type")
    private String postType;


    /**
     * 出版社
     */
    @Column(name = "publisher_bn")
    private String publisherBn;

    @Column(name = "publisher_nm")
    private String publisherNm;

    /**
     * 杂志起始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM",timezone = "GMT+8")
    @Column(name = "beg_date")
    private Date begDate;

    /**
     * 杂志结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM",timezone = "GMT+8")
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 数量
     */
    @Column(name = "sales_num")
    private Integer salesNum;

    /**
     * 杂志价格
     */
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    /**
     *
     */
    @Column(name = "goods_type")
    private String goodsType;

    /**
     * 商品id
     */
    @Column(name = "goods_bn")
    private String goodsBn;

    /**
     * 商品名称
     */
    @Column(name = "goods_nm")
    private String goodsNm;


    /**
     * 库存状态： 1 有货 2 无货  3次品有货
     */
    @Column(name = "stock_status")
    private String stockStatus;

    /**
     * 创建人
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 创建人姓名
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "edit_user_id")
    private String editUserId;

    /**
     * 修改人姓名
     */
    @Column(name = "edit_user_name")
    private String editUserName;

    /**
     * 修改时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 有效标识
     */
    private String flag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesOrderBn() {
        return salesOrderBn;
    }

    public void setSalesOrderBn(String salesOrderBn) {
        this.salesOrderBn = salesOrderBn;
    }

    public String getListNo() {
        return listNo;
    }

    public void setListNo(String listNo) {
        this.listNo = listNo;
    }

    public String getListCategory() {
        return listCategory;
    }

    public void setListCategory(String listCategory) {
        this.listCategory = listCategory;
    }

    public String getMagazineType() {
        return magazineType;
    }

    public void setMagazineType(String magazineType) {
        this.magazineType = magazineType;
    }

    public String getMagazineTypeName() {
        return magazineTypeName;
    }

    public void setMagazineTypeName(String magazineTypeName) {
        this.magazineTypeName = magazineTypeName;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPublisherBn() {
        return publisherBn;
    }

    public void setPublisherBn(String publisherBn) {
        this.publisherBn = publisherBn;
    }

    public String getPublisherNm() {
        return publisherNm;
    }

    public void setPublisherNm(String publisherNm) {
        this.publisherNm = publisherNm;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsBn() {
        return goodsBn;
    }

    public void setGoodsBn(String goodsBn) {
        this.goodsBn = goodsBn;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(String editUserId) {
        this.editUserId = editUserId;
    }

    public String getEditUserName() {
        return editUserName;
    }

    public void setEditUserName(String editUserName) {
        this.editUserName = editUserName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}