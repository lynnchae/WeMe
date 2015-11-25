package com.daoke.mobileserver.channel.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * 服务频道
 * Created by chenmaomao on 5/26 0026.
 */
public class ServerChannel implements Serializable {

    private static final long serialVersionUID = 2636430898136194335L;

    private Integer id; //主键
    private String icon; //图标
    private String name; //名称
    private String summary; //简介
    private  String functionIntro; //功能介绍
    @JsonIgnore
    private Integer isValid; //是否有效 1:是 0:否
    private Long createTime; //创建时间
    @JsonIgnore
    private Long updateTime; //修改时间

    public ServerChannel() {
    }

    public ServerChannel(Integer id, String icon, String name, String summary, String functionIntro, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.summary = summary;
        this.functionIntro = functionIntro;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ServerChannel{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", functionIntro='" + functionIntro + '\'' +
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFunctionIntro() {
        return functionIntro;
    }

    public void setFunctionIntro(String functionIntro) {
        this.functionIntro = functionIntro;
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
