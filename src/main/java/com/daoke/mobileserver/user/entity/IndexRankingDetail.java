package com.daoke.mobileserver.user.entity;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public class IndexRankingDetail extends  UserGrade{
    /**
     * 指数 值
     */
    private Integer indexValue = 0;
    /**
     * 星星数
     */
    private float stars;

    /**
     * 是否已添加好友
     * 0 未添加
     * 1 已添加
     */
    private String friendTag = "0";

    public Integer getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Integer indexValue) {
        this.indexValue = indexValue;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getFriendTag() {
        return friendTag;
    }

    public void setFriendTag(String friendTag) {
        this.friendTag = friendTag;
    }

    @Override
    public String toString() {

        return "IndexRankingDetail{" +
                ", accountID='" + this.getAccountID() + '\'' +
                ", userHeadName='" + this.getUserHeadName() + '\'' +
                ", nickName='" + this.getNickName() + '\'' +
                ", grade=" + this.getGrade() +
                "indexValue=" + indexValue +
                ", stars=" + stars +
                ", friendTag='" + friendTag + '\'' +
                "} ";
    }
}
