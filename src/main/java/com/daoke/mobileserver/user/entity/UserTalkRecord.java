package com.daoke.mobileserver.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户聊天记录
 * Created by chenmaomao on 5/12 0012.
 */
public class UserTalkRecord implements Serializable {

    private Integer id; //主键
    private String accountID; //聊天发起者用户ID
    private String friendAccountID; //好友用户ID
    private String talkContent; //聊天记录
    private Integer isValid; //是否有效
    private Long createTime; //创建时间
    private Long updateTime; //修改时间

    public UserTalkRecord() {
    }

    public UserTalkRecord(Integer id, String accountID, String friendAccountID, String talkContent, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.accountID = accountID;
        this.friendAccountID = friendAccountID;
        this.talkContent = talkContent;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserTalkRecord{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", friendAccountID='" + friendAccountID + '\'' +
                ", talkContent='" + talkContent + '\'' +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
