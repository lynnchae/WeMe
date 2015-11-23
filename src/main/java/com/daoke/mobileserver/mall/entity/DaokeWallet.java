package com.daoke.mobileserver.mall.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * 道客钱包
 * Created by chenmaomao on 6/8 0008.
 */
public class DaokeWallet implements Serializable {

    private Integer id;
    private String name;
    private String icon;
    private String url;
    @JsonIgnore
    private Integer isValid;
    private Long createTime;
    @JsonIgnore
    private Long updateTime;

    public DaokeWallet() {
    }

    public DaokeWallet(Integer id, String name, String icon, String url, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DaokeWallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
