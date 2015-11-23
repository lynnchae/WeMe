package com.daoke.mobileserver.channel.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * 服务频道
 * Created by chenmaomao on 5/26 0026.
 */
public class ServerChannelMessage implements Serializable {

    private static final long serialVersionUID = -3990726989877200708L;
    private Integer id; //主键
    private Integer serverChannelID; //服务频道ID
    private String messageType; //消息类型 1:文本 2:图片 3:图文 4:视频 5:语音
    private String body; //消息体
    private String url; //跳转链接
    @JsonIgnore
    private Integer isValid; //是否有效 1:是 0:否
    private Long createTime; //创建时间
    @JsonIgnore
    private Long updateTime; //修改时间

    public ServerChannelMessage() {
    }

    public ServerChannelMessage(Integer id, Integer serverChannelID, String messageType, String body,String url , Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.serverChannelID = serverChannelID;
        this.messageType = messageType;
        this.body = body;
        this.url = url;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ServerChannelMessage{" +
                "id=" + id +
                ", serverChannelID=" + serverChannelID +
                ", messageType='" + messageType + '\'' +
                ", body='" + body + '\'' +
                ", url='" + url + '\'' +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerChannelID() {
        return serverChannelID;
    }

    public void setServerChannelID(Integer serverChannelID) {
        this.serverChannelID = serverChannelID;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
