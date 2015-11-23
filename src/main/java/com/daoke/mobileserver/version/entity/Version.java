package com.daoke.mobileserver.version.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * Created by chenmaomao on 5/30 0030.
 */
public class Version implements Serializable {
    private static final long serialVersionUID = 3617133039961483706L;

     private Integer id; //主键

    private String number; //版本号

    private String iosUpdateUrl; //iOS更新地址

    private String androidUpdateUrl; //安卓更新地址

    private String remark; //备注
    @JsonIgnore
    private Integer isValid; //是否有效 1:是 0:否

    private Long createTime; //创建时间
    @JsonIgnore
    private Long updateTime; //更新时间

    public Version() {
    }

    public Version(Integer id, String number, String iosUpdateUrl, String androidUpdateUrl, String remark, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.number = number;
        this.iosUpdateUrl = iosUpdateUrl;
        this.androidUpdateUrl = androidUpdateUrl;
        this.remark = remark;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIosUpdateUrl() {
        return iosUpdateUrl;
    }

    public void setIosUpdateUrl(String iosUpdateUrl) {
        this.iosUpdateUrl = iosUpdateUrl;
    }

    public String getAndroidUpdateUrl() {
        return androidUpdateUrl;
    }

    public void setAndroidUpdateUrl(String androidUpdateUrl) {
        this.androidUpdateUrl = androidUpdateUrl;
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
