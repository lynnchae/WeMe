package com.daoke.mobileserver.user.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.sql.Timestamp;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public class UserRankDetail {
    /**
     * 记录ID 主键
     */
    @JsonIgnore
    private Integer recordID;
    /**
     * 用户账号ID
     */
    private String accountID;
    /**
     * 全国排名
     */
    private Integer rankAll;
    /**
     * 本月排名
     */
    private Integer rankMonth;
    @JsonIgnore
    private Timestamp createDate;
    @JsonIgnore
    private Timestamp modifyDate;
    @JsonIgnore
    private Short isValid;


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

    public Integer getRankAll() {
        return rankAll;
    }

    public void setRankAll(Integer rankAll) {
        this.rankAll = rankAll;
    }

    public Integer getRankMonth() {
        return rankMonth;
    }

    public void setRankMonth(Integer rankMonth) {
        this.rankMonth = rankMonth;
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

    public Short getIsValid() {
        return isValid;
    }

    public void setIsValid(Short isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "UserRankDetail{" +
                "accountID='" + accountID + '\'' +
                ", rankAll=" + rankAll +
                ", rankMonth=" + rankMonth +
                '}';
    }
}
