package com.liang.mongodbtut.neuabc;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 既是数据库转存MongoDB的对象
 * 也是页面展示的对象
 */
@Document("his_course")
public class HisCourseDto implements Serializable {

    @Column(name = "classin_id")
    private String classinId;

    @Indexed
    @Column(name = "only_id")
    private String onlyId;

    @Column(name = "plan_open_time")
    private Date planOpenTime;
    @Column(name = "plan_close_time")
    private Date planCloseTime;

    @Column(name = "open_time")
    private Date openTime;
    @Column(name = "close_time")
    private Date closeTime;

    @Column(name = "s_in_time")
    private Date sInTime;
    @Column(name = "s_out_time")
    private Date sOutTime;
    @Column(name = "t_in_time")
    private Date tInTime;
    @Column(name = "t_out_time")
    private Date tOutTime;

    @Column(name = "r_state")
    private String rState;
    @Column(name = "r_flag")
    private String rFlag;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_sub_id")
    private String userSubId;

    @Column(name = "nickname")
    private String nickname;
    @Column(name = "real_name")
    private String realname;

    @Column(name = "fic_user_id")
    private String ficUserId;
    @Column(name = "fic_user_id")
    private String userSubCourseId;

    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_rel_id")
    private String courseRelId;

    @Column(name = "lesson_id")
    private String lessonId;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "lesson_level")
    private String lessonLevel;
    @Column(name = "lesson_unit")
    private String lessonUnit;

    @Indexed
    @Column(name = "course_time")
    private Date courseTime;

    @Column(name = "s_state")
    private String sState;
    @Column(name = "consumption_hours")
    private Integer consumptionHours;
    @Column(name = "url")
    private String url;

    @Column(name = "s_flag")
    private String sFlag;

    @Column(name = "t_user_id")
    private String tUserId;

    @Column(name = "t_fic_user_id")
    private String tFicUserId;
    @Column(name = "t_state")
    private String tState;

    @Column(name = "commission")
    private BigDecimal commission;
    @Column(name = "settlement_id")
    private String settlementId;

    @Column(name = "t_flag")
    private String tFlag;


    @Column(name = "user_name")
    private String userName;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "middle_name")
    private String middleName;

    private String teacherFullName;

    @Indexed
    @Column(name = "t_mail")
    private String tMail;
    @Column(name = "t_tel")
    private String tTel;
    @Indexed
    @Column(name = "p_tel")
    private String pTel;

    @Column(name = "reg_type")
    private String regType;

    @Column(name = "ocs_id")
    private String ocsId;

    private String octId;
    @Column(name = "r_id")
    private String ocrId;


    private String tCommentId;
    private String sCommentId;

    private String updateFlag;//修改评论标识，小于0则不可编辑



    public String getClassinId() {
        return classinId;
    }

    public void setClassinId(String classinId) {
        this.classinId = classinId;
    }

    public String getOnlyId() {
        return onlyId;
    }

    public void setOnlyId(String onlyId) {
        this.onlyId = onlyId;
    }

    public Date getPlanOpenTime() {
        return planOpenTime;
    }

    public void setPlanOpenTime(Date planOpenTime) {
        this.planOpenTime = planOpenTime;
    }

    public Date getPlanCloseTime() {
        return planCloseTime;
    }

    public void setPlanCloseTime(Date planCloseTime) {
        this.planCloseTime = planCloseTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getsInTime() {
        return sInTime;
    }

    public void setsInTime(Date sInTime) {
        this.sInTime = sInTime;
    }

    public Date getsOutTime() {
        return sOutTime;
    }

    public void setsOutTime(Date sOutTime) {
        this.sOutTime = sOutTime;
    }

    public Date gettInTime() {
        return tInTime;
    }

    public void settInTime(Date tInTime) {
        this.tInTime = tInTime;
    }

    public Date gettOutTime() {
        return tOutTime;
    }

    public void settOutTime(Date tOutTime) {
        this.tOutTime = tOutTime;
    }

    public String getrState() {
        return rState;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getrFlag() {
        return rFlag;
    }

    public void setrFlag(String rFlag) {
        this.rFlag = rFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSubId() {
        return userSubId;
    }

    public void setUserSubId(String userSubId) {
        this.userSubId = userSubId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFicUserId() {
        return ficUserId;
    }

    public void setFicUserId(String ficUserId) {
        this.ficUserId = ficUserId;
    }

    public String getUserSubCourseId() {
        return userSubCourseId;
    }

    public void setUserSubCourseId(String userSubCourseId) {
        this.userSubCourseId = userSubCourseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseRelId() {
        return courseRelId;
    }

    public void setCourseRelId(String courseRelId) {
        this.courseRelId = courseRelId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonLevel() {
        return lessonLevel;
    }

    public void setLessonLevel(String lessonLevel) {
        this.lessonLevel = lessonLevel;
    }

    public String getLessonUnit() {
        return lessonUnit;
    }

    public void setLessonUnit(String lessonUnit) {
        this.lessonUnit = lessonUnit;
    }

    public Date getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Date courseTime) {
        this.courseTime = courseTime;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public Integer getConsumptionHours() {
        return consumptionHours;
    }

    public void setConsumptionHours(Integer consumptionHours) {
        this.consumptionHours = consumptionHours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getsFlag() {
        return sFlag;
    }

    public void setsFlag(String sFlag) {
        this.sFlag = sFlag;
    }

    public String gettUserId() {
        return tUserId;
    }

    public void settUserId(String tUserId) {
        this.tUserId = tUserId;
    }

    public String gettFicUserId() {
        return tFicUserId;
    }

    public void settFicUserId(String tFicUserId) {
        this.tFicUserId = tFicUserId;
    }

    public String gettState() {
        return tState;
    }

    public void settState(String tState) {
        this.tState = tState;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String gettFlag() {
        return tFlag;
    }

    public void settFlag(String tFlag) {
        this.tFlag = tFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public String gettMail() {
        return tMail;
    }

    public void settMail(String tMail) {
        this.tMail = tMail;
    }

    public String gettTel() {
        return tTel;
    }

    public void settTel(String tTel) {
        this.tTel = tTel;
    }

    public String getpTel() {
        return pTel;
    }

    public void setpTel(String pTel) {
        this.pTel = pTel;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getOcsId() {
        return ocsId;
    }

    public void setOcsId(String ocsId) {
        this.ocsId = ocsId;
    }

    public String getOctId() {
        return octId;
    }

    public void setOctId(String octId) {
        this.octId = octId;
    }

    public String getOcrId() {
        return ocrId;
    }

    public void setOcrId(String ocrId) {
        this.ocrId = ocrId;
    }

    public String gettCommentId() {
        return tCommentId;
    }

    public void settCommentId(String tCommentId) {
        this.tCommentId = tCommentId;
    }

    public String getsCommentId() {
        return sCommentId;
    }

    public void setsCommentId(String sCommentId) {
        this.sCommentId = sCommentId;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

}
