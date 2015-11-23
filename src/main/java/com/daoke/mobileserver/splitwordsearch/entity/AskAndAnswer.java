package com.daoke.mobileserver.splitwordsearch.entity;

import java.util.Date;
/**
 * 问答表
 * @author zhuoshuang
 * @data 2015-5-13
 */
public class AskAndAnswer {

    /***主键*/
    private int id;

    /**
     * 问题分类 0：与设置有关，1：与设备有关，2：与数据有关，3：与金钱有关，4：其他问题
     */
    private int questionType;

    /***问题*/
    private String question;

    /***对问题的回答*/
    private String answer;

    /***url*/
    private String fileUrl;

    /***当前记录是否有效 1 有效 0 无效*/
    private int isValid;

    /***创建记录的时间*/
    private Date createDate;

    /***修改记录的时间*/
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "AskAndAnswer{" +
                "id=" + id +
                ", questionType=" + questionType +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
