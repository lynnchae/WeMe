package com.daoke.mobileserver.ejsino.model;

import java.io.Serializable;

/**
 * Created by wangzp on 2014/12/16.
 */
public class EjsinoCityInfo implements Serializable{

    private Integer cityCode;

    private String cityName;

    private String pinYin;

    private String abbreviate;

    private Integer parentCode;

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }


    @Override
    public String toString() {
        return "EjsinoCityInfo{" +
                "cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", abbreviate='" + abbreviate + '\'' +
                ", parentCode=" + parentCode +
                '}';
    }
}
