package com.liang.mybatisinterceptor.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


public class SalesOrder  {
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
     * 订单类型
     */
    @Column(name = "sales_order_type")
    private String salesOrderType;

    /**
     * 发货类型
     */
    @Column(name = "delivery_type")
    private String deliveryType;

    /**
     * 订单渠道id
     */
    @Column(name = "channel_bn")
    private String channelBn;

    /**
     * 渠道类型 1渠道，2经销商
     */
    @Column(name = "channel_type")
    private String channelType;

    /**
     * 活动
     */
    @Column(name = "promotion_bn")
    private String promotionBn;

    /**
     * 收件人姓名
     */
    @Column(name = "addressee_nm")
    private String addresseeNm;

    /**
     * 收件人手机
     */
    @Column(name = "addressee_tel")
    private String addresseeTel;

    /**
     * 地址国
     */
    @Column(name = "addr_country")
    private String addrCountry;

    /**
     * 地址省
     */
    @Column(name = "addr_province")
    private String addrProvince;

    /**
     * 地址市
     */
    @Column(name = "addr_city")
    private String addrCity;

    /**
     * 地址区
     */
    @Column(name = "addr_area")
    private String addrArea;

    /**
     * 地址街道
     */
    @Column(name = "addr_street")
    private String addrStreet;

    /**
     * 地址明细
     */
    @Column(name = "addr_detail")
    private String addrDetail;

    /**
     * 订单总金额
     */
    @Column(name = "sales_order_total")
    private BigDecimal salesOrderTotal;

    /**
     * 订单备注
     */
    @Column(name = "sales_order_demo")
    private String salesOrderDemo;

    /**
     * 订单状态
     */
    @Column(name = "sales_order_status")
    private String salesOrderStatus;

    /**
     * 审核状态
     */
    @Column(name = "check_status")
    private String checkStatus;

    /**
     * 库存状态
     */
    @Column(name = "stock_status")
    private String stockStatus;

    /**
     * 下单时间
     */
    @Column(name = "sales_order_date")
    private Date salesOrderDate;

    /**
     * 销售人id
     */
    @Column(name = "sales_user_id")
    private String salesUserId;

    /**
     * 销售人名称
     */
    @Column(name = "sales_user_name")
    private String salesUserName;

    /**
     * 审核人id
     */
    @Column(name = "checker_user_id")
    private String checkerUserId;

    /**
     * 审核人名称
     */
    @Column(name = "checker_user_name")
    private String checkerUserName;

    /**
     * 审核意见
     */
    @Column(name = "check_detail")
    private String checkDetail;

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

    public String getSalesOrderType() {
        return salesOrderType;
    }

    public void setSalesOrderType(String salesOrderType) {
        this.salesOrderType = salesOrderType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getChannelBn() {
        return channelBn;
    }

    public void setChannelBn(String channelBn) {
        this.channelBn = channelBn;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPromotionBn() {
        return promotionBn;
    }

    public void setPromotionBn(String promotionBn) {
        this.promotionBn = promotionBn;
    }

    public String getAddresseeNm() {
        return addresseeNm;
    }

    public void setAddresseeNm(String addresseeNm) {
        this.addresseeNm = addresseeNm;
    }

    public String getAddresseeTel() {
        return addresseeTel;
    }

    public void setAddresseeTel(String addresseeTel) {
        this.addresseeTel = addresseeTel;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public BigDecimal getSalesOrderTotal() {
        return salesOrderTotal;
    }

    public void setSalesOrderTotal(BigDecimal salesOrderTotal) {
        this.salesOrderTotal = salesOrderTotal;
    }

    public String getSalesOrderDemo() {
        return salesOrderDemo;
    }

    public void setSalesOrderDemo(String salesOrderDemo) {
        this.salesOrderDemo = salesOrderDemo;
    }

    public String getSalesOrderStatus() {
        return salesOrderStatus;
    }

    public void setSalesOrderStatus(String salesOrderStatus) {
        this.salesOrderStatus = salesOrderStatus;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Date getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(Date salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public String getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(String salesUserId) {
        this.salesUserId = salesUserId;
    }

    public String getSalesUserName() {
        return salesUserName;
    }

    public void setSalesUserName(String salesUserName) {
        this.salesUserName = salesUserName;
    }

    public String getCheckerUserId() {
        return checkerUserId;
    }

    public void setCheckerUserId(String checkerUserId) {
        this.checkerUserId = checkerUserId;
    }

    public String getCheckerUserName() {
        return checkerUserName;
    }

    public void setCheckerUserName(String checkerUserName) {
        this.checkerUserName = checkerUserName;
    }

    public String getCheckDetail() {
        return checkDetail;
    }

    public void setCheckDetail(String checkDetail) {
        this.checkDetail = checkDetail;
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