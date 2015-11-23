package com.daoke.mobileserver.map.entity;

/**
 * 热点vo
 * Created by chenmaomao on 5/19 0019.
 */
public class PowerOffHotVo {

    private Integer longitude; //经度
    private Integer latitude; //纬度
    private String provinceName;

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "PowerOffHotVo{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
