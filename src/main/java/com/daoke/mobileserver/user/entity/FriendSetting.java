package com.daoke.mobileserver.user.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 好友设置
 * Created by chenmaomao on 5/9 0009.
 */
public class FriendSetting implements Serializable {

    private static final long serialVersionUID = 4439767344025896295L;
    @JsonIgnore
    private Integer recordId; //主键

    private String accountID; //用户ID

    private Integer isAllowedOpinion; //是否允许被添加好友

    private Integer isVerifyOpinion;   //添加好友是否需要验证

    private Integer isReceiveNotifyOpinion; //是否接收好友上线通知
    @JsonIgnore
    private Integer isValid; //是否有效 1:是 0:否
    @JsonIgnore
    private Timestamp createTime;//创建时间
    @JsonIgnore
    private Timestamp updateTime;//修改时间

    public FriendSetting() {
    }

    public FriendSetting(Integer recordId, String accountID, Integer isAllowedOpinion, Integer isVerifyOpinion, Integer isReceiveNotifyOpinion,
                         Integer isValid, Timestamp createTime, Timestamp updateTime) {
        this.recordId = recordId;
        this.accountID = accountID;
        this.isAllowedOpinion = isAllowedOpinion;
        this.isVerifyOpinion = isVerifyOpinion;
        this.isReceiveNotifyOpinion = isReceiveNotifyOpinion;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public Integer getIsReceiveNotifyOpinion() {
        return isReceiveNotifyOpinion;
    }

    public void setIsReceiveNotifyOpinion(Integer isReceiveNotifyOpinion) {
        this.isReceiveNotifyOpinion = isReceiveNotifyOpinion;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FriendSetting{" +
                "recordId=" + recordId +
                ", accountID='" + accountID + '\'' +
                ", isAllowedOpinion=" + isAllowedOpinion +
                ", isVerifyOpinion=" + isVerifyOpinion +
                ", isReceiveNotifyOpinion=" + isReceiveNotifyOpinion +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
