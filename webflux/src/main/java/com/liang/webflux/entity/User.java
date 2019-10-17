package com.liang.webflux.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 108523162944529730L;

    @Id
    private String id;                      //主键uuid

    @Column(name = "login_name")
    private String loginName;               //登录账号

    @Column(name = "contact_info")
    private String contactInfo;             //联系方式

    private String password;                 //密码

    private String role;                    //角色

    @Column(name = "reg_type")
    private String regType;                 //注册类型

    @Column(name = "user_livel")
    private Integer userLivel;              //用户等级

    @Column(name = "create_time")
    private Date createTime;                //创建时间

    @Column(name = "create_user_id")
    private String createUserId;            //创建人id

    @Column(name = "edit_time")
    private Date editTime;                  //修改时间

    @Column(name = "edit_user_id")
    private String editUserId;              //修改人id

    private String flag;                    //作废标识

    @Column(name = "first_landing")
    private String firstLanding;           //判断是否是首次登陆

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType == null ? null : regType.trim();
    }

    public Integer getUserLivel() {
        return userLivel;
    }

    public void setUserLivel(Integer userLivel) {
        this.userLivel = userLivel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(String editUserId) {
        this.editUserId = editUserId == null ? null : editUserId.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getFirstLanding() {
        return firstLanding;
    }

    public void setFirstLanding(String firstLanding) {
        this.firstLanding = firstLanding;
    }
}