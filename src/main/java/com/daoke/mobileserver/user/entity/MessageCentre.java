package com.daoke.mobileserver.user.entity;


import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * Created by chenmaomao on 5/14 0014.
 */
public class MessageCentre implements Serializable {
    private static final long serialVersionUID = 4997136933831675519L;
    private Integer id;
    private String msgTitle;
    private String accountID;
    private String content;
    private String senderAccountID;
    private String senderUserHeadName;
    private String messageType;
    private Integer isRead;
    private String isAgree;
    private String param;
    @JsonIgnore
    private Integer isValid;
    private Long createTime;
    @JsonIgnore
    private Long updateTime;

    public MessageCentre() {
    }

    public MessageCentre(Integer id, String msgTitle, String accountID, String content, String senderAccountID, String senderUserHeadName, String messageType, Integer isRead, String param, Integer isValid, String isAgree, Long createTime, Long updateTime) {
        this.id = id;
        this.msgTitle = msgTitle;
        this.accountID = accountID;
        this.content = content;
        this.senderAccountID = senderAccountID;
        this.senderUserHeadName = senderUserHeadName;
        this.messageType = messageType;
        this.isRead = isRead;
        this.isAgree = isAgree;
        this.param = param;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MessageCentre{" +
                "id=" + id +
                ", msgTitle='" + msgTitle + '\'' +
                ", accountID='" + accountID + '\'' +
                ", content='" + content + '\'' +
                ", senderAccountID='" + senderAccountID + '\'' +
                ", senderUserHeadName='" + senderUserHeadName + '\'' +
                ", messageType='" + messageType + '\'' +
                ", isAgree='" + isAgree + '\'' +
                ", isRead=" + isRead +
                ", param='" + param + '\'' +
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

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderAccountID() {
        return senderAccountID;
    }

    public void setSenderAccountID(String senderAccountID) {
        this.senderAccountID = senderAccountID;
    }

    public String getSenderUserHeadName() {
        return senderUserHeadName;
    }

    public void setSenderUserHeadName(String senderUserHeadName) {
        this.senderUserHeadName = senderUserHeadName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
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
