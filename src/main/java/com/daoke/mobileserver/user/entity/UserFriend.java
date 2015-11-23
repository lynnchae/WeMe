package com.daoke.mobileserver.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public class UserFriend implements Serializable{

    private static final long serialVersionUID = -7312692879270377922L;
    private Integer recordID;
    /**
     * 用户账号ID
     */
    private String accountID;
    /**
     *好友ID
     */
    private String friendAccountID;
    /**
     *是否同意
     */
    private Integer isAgree;
    /**
     *创建时间
     */
    private Timestamp createDate;
    /**
     *修改时间
     */
    private Timestamp modifyDate;


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

    public String getFriendAccountID() {
        return friendAccountID;
    }

    public void setFriendAccountID(String friendAccountID) {
        this.friendAccountID = friendAccountID;
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
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

    @Override
    public String toString() {
        return "UserFriend{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", friendAccountID='" + friendAccountID + '\'' +
                ", isAgree=" + isAgree +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
