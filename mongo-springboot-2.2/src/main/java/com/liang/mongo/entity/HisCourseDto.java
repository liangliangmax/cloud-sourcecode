package com.liang.mongo.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 既是数据库转存MongoDB的对象
 * 也是页面展示的对象
 */
@Document("his_course")
//创建复合索引
@CompoundIndexes({
        @CompoundIndex(name = "courseTime_nickname_onlyId",def = "{'courseTime':-1,'onlyId':1}")
})
public class HisCourseDto implements Serializable {

    private String classinId;

    @Indexed(unique=true)
    private String onlyId;

    private Date planOpenTime;
    private Date planCloseTime;

    private Date openTime;
    private Date closeTime;

    private Date sInTime;
    private Date sOutTime;
    private Date tInTime;
    private Date tOutTime;

    private String rState;
    private String rFlag;

    private String userId;
    private String userSubId;

    private String nickname;
    private String realname;

    private String ficUserId;
    private String userSubCourseId;

    private String courseId;
    private String courseName;
    private String courseRelId;

    private String lessonId;
    private String lessonName;
    private String lessonLevel;
    private String lessonUnit;

    //降序
    @Indexed(direction = IndexDirection.DESCENDING)
    private Date courseTime;

    private String sState;
    private Integer consumptionHours;
    private String url;

    private String sFlag;

    private String tUserId;

    private String tFicUserId;

    private String tState;

    private BigDecimal commission;
    private String settlementId;

    private String tFlag;


    private String userName;
    private String givenName;
    private String middleName;

    private String teacherFullName;

    @Indexed
    private String tMail;
    private String tTel;
    @Indexed
    private String pTel;

    private String regType;

    //his_course_stu的id字段
    private String ocsId;

    //his_course_tch的id字段
    private String octId;

    //his_course_room的id字段
    private String ocrId;

    private String tClassinUid;

    private String sClassinUid;

    public String gettClassinUid() {
        return tClassinUid;
    }

    public void settClassinUid(String tClassinUid) {
        this.tClassinUid = tClassinUid;
    }

    public String getsClassinUid() {
        return sClassinUid;
    }

    public void setsClassinUid(String sClassinUid) {
        this.sClassinUid = sClassinUid;
    }

    private String tCommentId;
    private String sCommentId;

    private String updateFlag;//修改评论标识，小于0则不可编辑

    /////////////////////////////////////////////////////////////////////////////////////////////


    //冗余字段，是hisCourseLog里面的字段
    private String courseMemo;
    private String courseState;
    //冗余字段，是comment里面的字段
    private String commentId;
    private String commentText;

    //数据指纹，验证数据正确性
    private String dataFingerprint;


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


    public String getCourseMemo() {
        return courseMemo;
    }

    public void setCourseMemo(String courseMemo) {
        this.courseMemo = courseMemo;
    }

    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getDataFingerprint() {
        return dataFingerprint;
    }

    public void setDataFingerprint(String dataFingerprint) {
        this.dataFingerprint = dataFingerprint;
    }
}
