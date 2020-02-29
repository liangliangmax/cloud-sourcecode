package com.liang.mongodbtut.neuabc;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@CompoundIndexes({
        @CompoundIndex(name = "user_courseTime_index", def = "{'user_id': 1, 'course_time': -1}")
})
@Document("his_course")
public class HisCourseMsgDto implements Serializable{

    private String classin_id;
    private String ocs_id;

    @Indexed(unique=true)
    private String only_id;

    private Date plan_open_time;
    private Date plan_close_time;
    private Date open_time;
    private Date close_time;
    private Date s_in_time;
    private Date s_out_time;
    private Date t_in_time;
    private Date t_out_time;
    private String r_state;
    private String r_flag;

    private String s_id;


    @Indexed
    private String user_id;

    private String user_sub_id;
    private String nickname;
    private String real_name;
    private String fic_user_id;
    private String user_sub_course_id;
    private String course_id;
    private String course_name;
    private String course_rel_id;
    private String lesson_id;
    private String lesson_name;
    private String lesson_level;
    private String lesson_unit;
    private Date course_time;
    private String s_state;
    private Integer consumption_hours;
    private String url;
    private String s_flag;
    private String t_id;
    private String t_user_id;
    private String t_fic_user_id;
    private String t_state;
    private Long commission;
    private String settlement_id;
    private String t_flag;


    private String user_name;
    private String given_name;
    private String middle_name;


    private String teacherFullName;

    private String t_mail;
    private String t_tel;
    private String p_tel;
    private String reg_type;
    private String r_id;


    private String updateFlag;//修改评论标识，小于0则不可编辑


    public String getClassin_id() {
        return classin_id;
    }

    public void setClassin_id(String classin_id) {
        this.classin_id = classin_id;
    }

    public String getOcs_id() {
        return ocs_id;
    }

    public void setOcs_id(String ocs_id) {
        this.ocs_id = ocs_id;
    }

    public String getOnly_id() {
        return only_id;
    }

    public void setOnly_id(String only_id) {
        this.only_id = only_id;
    }

    public Date getPlan_open_time() {
        return plan_open_time;
    }

    public void setPlan_open_time(Date plan_open_time) {
        this.plan_open_time = plan_open_time;
    }

    public Date getPlan_close_time() {
        return plan_close_time;
    }

    public void setPlan_close_time(Date plan_close_time) {
        this.plan_close_time = plan_close_time;
    }

    public Date getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Date open_time) {
        this.open_time = open_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getS_in_time() {
        return s_in_time;
    }

    public void setS_in_time(Date s_in_time) {
        this.s_in_time = s_in_time;
    }

    public Date getS_out_time() {
        return s_out_time;
    }

    public void setS_out_time(Date s_out_time) {
        this.s_out_time = s_out_time;
    }

    public Date getT_in_time() {
        return t_in_time;
    }

    public void setT_in_time(Date t_in_time) {
        this.t_in_time = t_in_time;
    }

    public Date getT_out_time() {
        return t_out_time;
    }

    public void setT_out_time(Date t_out_time) {
        this.t_out_time = t_out_time;
    }

    public String getR_state() {
        return r_state;
    }

    public void setR_state(String r_state) {
        this.r_state = r_state;
    }

    public String getR_flag() {
        return r_flag;
    }

    public void setR_flag(String r_flag) {
        this.r_flag = r_flag;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_sub_id() {
        return user_sub_id;
    }

    public void setUser_sub_id(String user_sub_id) {
        this.user_sub_id = user_sub_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getFic_user_id() {
        return fic_user_id;
    }

    public void setFic_user_id(String fic_user_id) {
        this.fic_user_id = fic_user_id;
    }

    public String getUser_sub_course_id() {
        return user_sub_course_id;
    }

    public void setUser_sub_course_id(String user_sub_course_id) {
        this.user_sub_course_id = user_sub_course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_rel_id() {
        return course_rel_id;
    }

    public void setCourse_rel_id(String course_rel_id) {
        this.course_rel_id = course_rel_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLesson_level() {
        return lesson_level;
    }

    public void setLesson_level(String lesson_level) {
        this.lesson_level = lesson_level;
    }

    public String getLesson_unit() {
        return lesson_unit;
    }

    public void setLesson_unit(String lesson_unit) {
        this.lesson_unit = lesson_unit;
    }

    public Date getCourse_time() {
        return course_time;
    }

    public void setCourse_time(Date course_time) {
        this.course_time = course_time;
    }

    public String getS_state() {
        return s_state;
    }

    public void setS_state(String s_state) {
        this.s_state = s_state;
    }

    public Integer getConsumption_hours() {
        return consumption_hours;
    }

    public void setConsumption_hours(Integer consumption_hours) {
        this.consumption_hours = consumption_hours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getS_flag() {
        return s_flag;
    }

    public void setS_flag(String s_flag) {
        this.s_flag = s_flag;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(String t_user_id) {
        this.t_user_id = t_user_id;
    }

    public String getT_fic_user_id() {
        return t_fic_user_id;
    }

    public void setT_fic_user_id(String t_fic_user_id) {
        this.t_fic_user_id = t_fic_user_id;
    }

    public String getT_state() {
        return t_state;
    }

    public void setT_state(String t_state) {
        this.t_state = t_state;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public String getSettlement_id() {
        return settlement_id;
    }

    public void setSettlement_id(String settlement_id) {
        this.settlement_id = settlement_id;
    }

    public String getT_flag() {
        return t_flag;
    }

    public void setT_flag(String t_flag) {
        this.t_flag = t_flag;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public String getT_mail() {
        return t_mail;
    }

    public void setT_mail(String t_mail) {
        this.t_mail = t_mail;
    }

    public String getT_tel() {
        return t_tel;
    }

    public void setT_tel(String t_tel) {
        this.t_tel = t_tel;
    }

    public String getP_tel() {
        return p_tel;
    }

    public void setP_tel(String p_tel) {
        this.p_tel = p_tel;
    }

    public String getReg_type() {
        return reg_type;
    }

    public void setReg_type(String reg_type) {
        this.reg_type = reg_type;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    @Override
    public String toString() {
        return "HisCourseMsgDto{" +
                "classin_id='" + classin_id + '\'' +
                ", ocs_id='" + ocs_id + '\'' +
                ", only_id='" + only_id + '\'' +
                ", plan_open_time=" + plan_open_time +
                ", plan_close_time=" + plan_close_time +
                ", open_time=" + open_time +
                ", close_time=" + close_time +
                ", s_in_time=" + s_in_time +
                ", s_out_time=" + s_out_time +
                ", t_in_time=" + t_in_time +
                ", t_out_time=" + t_out_time +
                ", r_state='" + r_state + '\'' +
                ", r_flag='" + r_flag + '\'' +
                ", s_id='" + s_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_sub_id='" + user_sub_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", real_name='" + real_name + '\'' +
                ", fic_user_id='" + fic_user_id + '\'' +
                ", user_sub_course_id='" + user_sub_course_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_rel_id='" + course_rel_id + '\'' +
                ", lesson_id='" + lesson_id + '\'' +
                ", lesson_name='" + lesson_name + '\'' +
                ", lesson_level='" + lesson_level + '\'' +
                ", lesson_unit='" + lesson_unit + '\'' +
                ", course_time=" + course_time +
                ", s_state='" + s_state + '\'' +
                ", consumption_hours=" + consumption_hours +
                ", url='" + url + '\'' +
                ", s_flag='" + s_flag + '\'' +
                ", t_id='" + t_id + '\'' +
                ", t_user_id='" + t_user_id + '\'' +
                ", t_fic_user_id='" + t_fic_user_id + '\'' +
                ", t_state='" + t_state + '\'' +
                ", commission=" + commission +
                ", settlement_id='" + settlement_id + '\'' +
                ", t_flag='" + t_flag + '\'' +
                ", user_name='" + user_name + '\'' +
                ", given_name='" + given_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", teacherFullName='" + teacherFullName + '\'' +
                ", t_mail='" + t_mail + '\'' +
                ", t_tel='" + t_tel + '\'' +
                ", p_tel='" + p_tel + '\'' +
                ", reg_type='" + reg_type + '\'' +
                ", r_id='" + r_id + '\'' +
                ", updateFlag='" + updateFlag + '\'' +
                '}';
    }
}
