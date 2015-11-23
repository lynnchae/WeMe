package com.daoke.mobileserver.mall.entity;

/**
 * 商城banner图
 * Created by wangzp on 2014/12/23.
 */
public class MallBanner {

    private Integer bannerId;

    /**
     * 内容
     */
    private String content;

    /**
     * 跳转类型 1：url 2:商品id
     */
    private Integer redirectType;

    /**
     * 城市代码
     */
    private Integer cityCode;
    /**
     * 图片地址
     */
    private String bannerImg;


    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
