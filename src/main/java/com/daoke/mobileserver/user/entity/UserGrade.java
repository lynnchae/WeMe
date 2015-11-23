package com.daoke.mobileserver.user.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

/**用户等级
 * Created by chenmaomao on 2015/4/1.
 */
public class UserGrade implements Serializable {
    private static final long serialVersionUID = -9147085340468411722L;
    /**
     * 记录号
     */
    @JsonIgnore
    private Integer recordID;
    /**
     * 用户ID
     */
    private String accountID;
    /**
     * 用户头像
     */
    private String userHeadName;
    /**
     * 昵称
     */
    private  String nickName;
    /**
     * 等级
     */
    private Integer grade = 0;
    /**
     * 谢尔值
     */
    private Integer rochelle = 0;

    /**
     * 用户月谢尔值
     */
    private Integer monthRochelle = 0;
    private String userAreaCode;
    private short gender;

    private Integer rewardRochelleMonth = 0;
    /**
     * 是否有效
     */
    @JsonIgnore
    private Integer isValid;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Timestamp createDate;
    /**
     * 修改时间
     */
    @JsonIgnore
    private Timestamp modifyDate;

    public UserGrade() {
    }

    public UserGrade(Integer recordID, String accountID, String userHeadName, String nickName, Integer grade, Integer rochelle, Integer monthRochelle, String userAreaCode, short gender, Integer rewardRochelleMonth, Integer isValid, Timestamp createDate, Timestamp modifyDate) {
        this.recordID = recordID;
        this.accountID = accountID;
        this.userHeadName = userHeadName;
        this.nickName = nickName;
        this.grade = grade;
        this.rochelle = rochelle;
        this.monthRochelle = monthRochelle;
        this.userAreaCode = userAreaCode;
        this.gender = gender;
        this.rewardRochelleMonth = rewardRochelleMonth;
        this.isValid = isValid;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserHeadName() {
        return userHeadName;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getUserAreaCode() {
        return userAreaCode;
    }

    public void setUserAreaCode(String userAreaCode) {
        this.userAreaCode = userAreaCode;
    }

    public void setUserHeadName(String userHeadName) {
        this.userHeadName = userHeadName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getRochelle() {
        return rochelle;
    }

    public void setRochelle(Integer rochelle) {
        this.rochelle = rochelle;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getMonthRochelle() {
        return monthRochelle;
    }

    public void setMonthRochelle(Integer monthRochelle) {
        this.monthRochelle = monthRochelle;
    }

    public Integer getRewardRochelleMonth() {
        return rewardRochelleMonth;
    }

    public void setRewardRochelleMonth(Integer rewardRochelleMonth) {
        this.rewardRochelleMonth = rewardRochelleMonth;
    }

    @Override
    public String toString() {
        return "UserGrade{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", userHeadName='" + userHeadName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", grade=" + grade +
                ", rochelle=" + rochelle +
                ", monthRochelle=" + monthRochelle +
                ", userAreaCode='" + userAreaCode + '\'' +
                ", gender=" + gender +
                ", rewardRochelleMonth=" + rewardRochelleMonth +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
