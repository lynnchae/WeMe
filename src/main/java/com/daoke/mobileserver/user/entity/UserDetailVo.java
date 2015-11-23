package com.daoke.mobileserver.user.entity;

import java.sql.Timestamp;

/**
 * 用户详情封装类
 * Created by chenmaomao on 2015/5/11.
 */
public class UserDetailVo {

    private String accountID; //用户ID
    private String userHeadName; //用户头像
    private String gender; //性别
    private String userAreaCode; //地区
    private Integer isAllowedOpinion; //是否允许添加为好友 1:允许 0:不允许
    private Integer isVerifyOpinion; //添加好友是否需要验证 1:是 0:否
    private String nickName; //用户昵称

    public UserDetailVo() {
    }

    public UserDetailVo(String accountID, String userHeadName, String gender, String userAreaCode, Integer isAllowedOpinion, Integer isVerifyOpinion, String nickName) {
        this.accountID = accountID;
        this.userHeadName = userHeadName;
        this.gender = gender;
        this.userAreaCode = userAreaCode;
        this.isAllowedOpinion = isAllowedOpinion;
        this.isVerifyOpinion = isVerifyOpinion;
        this.nickName = nickName;
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

    public void setUserHeadName(String userHeadName) {
        this.userHeadName = userHeadName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserAreaCode() {
        return userAreaCode;
    }

    public void setUserAreaCode(String userAreaCode) {
        this.userAreaCode = userAreaCode;
    }

    public Integer getIsAllowedOpinion() {
        return isAllowedOpinion;
    }

    public void setIsAllowedOpinion(Integer isAllowedOpinion) {
        this.isAllowedOpinion = isAllowedOpinion;
    }

    public Integer getIsVerifyOpinion() {
        return isVerifyOpinion;
    }

    public void setIsVerifyOpinion(Integer isVerifyOpinion) {
        this.isVerifyOpinion = isVerifyOpinion;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
