package com.daoke.mobileserver.shareTimesWeekly.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by chenmaomao on 2015/4/8.
 */
public class ShareTimesWeekly implements Serializable{
    /**
     * 记录号
     */
    private Integer recordID;

    /**
     * 用户ID
     */
    private String accountID;
    /**
     * 本周分享次数
     */
    private Integer shareTimesWeekly;
    /**
     * 每周第一次分享时间
     */
    private Timestamp firstShareTimeWeekly;

    public ShareTimesWeekly() {
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

    public Integer getShareTimesWeekly() {
        return shareTimesWeekly;
    }

    public void setShareTimesWeekly(Integer shareTimesWeekly) {
        this.shareTimesWeekly = shareTimesWeekly;
    }

    public Timestamp getFirstShareTimeWeekly() {
        return firstShareTimeWeekly;
    }

    public void setFirstShareTimeWeekly(Timestamp firstShareTimeWeekly) {
        this.firstShareTimeWeekly = firstShareTimeWeekly;
    }

    @Override
    public String toString() {
        return "ShareTimesWeekly{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", shareTimesWeekly=" + shareTimesWeekly +
                ", firstShareTimeWeekly=" + firstShareTimeWeekly +
                '}';
    }

    public ShareTimesWeekly(Integer recordID, String accountID, Integer shareTimesWeekly, Timestamp firstShareTimeWeekly) {
        this.recordID = recordID;
        this.accountID = accountID;
        this.shareTimesWeekly = shareTimesWeekly;
        this.firstShareTimeWeekly = firstShareTimeWeekly;
    }
}
