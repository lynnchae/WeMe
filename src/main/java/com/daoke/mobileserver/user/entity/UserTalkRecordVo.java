package com.daoke.mobileserver.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户聊天记录vo
 * Created by chenmaomao on 5/12 0012.
 */
public class UserTalkRecordVo implements Serializable {

    private String accountID; //聊天发起者用户ID
    private String accountHeadName; //用户头像
    private String accountNickName; //用户昵称
    private String friendAccountID; //好友用户ID
    private String friendAccountHeadName; //好友头像
    private String friendAccountNickName; //好友昵称
    private String talkContent; //聊天记录
    private Timestamp createTime; //创建时间

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountHeadName() {
        return accountHeadName;
    }

    public void setAccountHeadName(String accountHeadName) {
        this.accountHeadName = accountHeadName;
    }

    public String getAccountNickName() {
        return accountNickName;
    }

    public void setAccountNickName(String accountNickName) {
        this.accountNickName = accountNickName;
    }

    public String getFriendAccountID() {
        return friendAccountID;
    }

    public void setFriendAccountID(String friendAccountID) {
        this.friendAccountID = friendAccountID;
    }

    public String getFriendAccountHeadName() {
        return friendAccountHeadName;
    }

    public void setFriendAccountHeadName(String friendAccountHeadName) {
        this.friendAccountHeadName = friendAccountHeadName;
    }

    public String getFriendAccountNickName() {
        return friendAccountNickName;
    }

    public void setFriendAccountNickName(String friendAccountNickName) {
        this.friendAccountNickName = friendAccountNickName;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
