package com.daoke.mobileserver.splitwordsearch.entity;

import java.util.Date;

/**
 * 标题或是问题对应的关键词
 * @author  zhuoshaung
 * @data 2015/5/13.
 */
public class TitleIndex {

    /***主键*/
    private int id;

    /***关键词*/
    private String word;

    /***对应问答表的主键*/
    private int askAndAnswerID;

    /***创建记录的时间*/
    private Date crateTime;

    /***修改记录时间*/
    private Date updateTime;

    @Override
    public String toString() {
        return "TitleIndex{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", askAndAnswerID=" + askAndAnswerID +
                ", crateTime=" + crateTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAskAndAnswerID() {
        return askAndAnswerID;
    }

    public void setAskAndAnswerID(int askAndAnswerID) {
        this.askAndAnswerID = askAndAnswerID;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
