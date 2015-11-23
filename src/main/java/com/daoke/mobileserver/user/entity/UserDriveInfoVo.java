package com.daoke.mobileserver.user.entity;

/**
 * Created by chenmaomao on 5/19 0019.
 */
public class UserDriveInfoVo  {

    private Integer cityNum;
    private Integer locusNum;
    private Integer hotNum;
    private Integer days;
    private Integer mileageSum;

    public Integer getCityNum() {
        return cityNum;
    }

    public void setCityNum(Integer cityNum) {
        this.cityNum = cityNum;
    }

    public Integer getLocusNum() {
        return locusNum;
    }

    public void setLocusNum(Integer locusNum) {
        this.locusNum = locusNum;
    }

    public Integer getHotNum() {
        return hotNum;
    }

    public void setHotNum(Integer hotNum) {
        this.hotNum = hotNum;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getMileageSum() {
        return mileageSum;
    }

    public void setMileageSum(Integer mileageSum) {
        this.mileageSum = mileageSum;
    }
}
