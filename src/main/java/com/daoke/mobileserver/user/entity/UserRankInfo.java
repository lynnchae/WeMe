package com.daoke.mobileserver.user.entity;

import java.io.Serializable;

/**
 * Created by wangzp on 2015/5/11.
 */
public class UserRankInfo implements Serializable {

    private String accountID;//

    private Integer actionType;//

    private String channelNumber;//

    private String memberAccountID;//

    private Integer id;//

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getMemberAccountID() {
        return memberAccountID;
    }

    public void setMemberAccountID(String memberAccountID) {
        this.memberAccountID = memberAccountID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
