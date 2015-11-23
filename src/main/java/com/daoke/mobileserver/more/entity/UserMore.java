package com.daoke.mobileserver.more.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 更多
 * Created by cailingfeng on 2015/5/29.
 */
public class UserMore implements Serializable{

    private String picUrl;
    private String appName;
    private String url;
    private String remark;

    @JsonIgnore
    private Integer isValid;

    @JsonIgnore
    private Timestamp createDate;

    @JsonIgnore
    private Timestamp modifyDate;

    @JsonIgnore
    private Integer isAd;

    public UserMore() {
    }

    public UserMore(String picUrl, String appName, String url, String remark, Integer isValid, Timestamp createDate, Timestamp modifyDate, Integer isAd) {
        this.picUrl = picUrl;
        this.appName = appName;
        this.url = url;
        this.remark = remark;
        this.isValid = isValid;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.isAd = isAd;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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

    public Integer getIsAd() {
        return isAd;
    }

    public void setIsAd(Integer isAd) {
        this.isAd = isAd;
    }

    @Override
    public String toString() {
        return "UserMore{" +
                "picUrl='" + picUrl + '\'' +
                ", appName='" + appName + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isAd=" + isAd +
                '}';
    }
}
