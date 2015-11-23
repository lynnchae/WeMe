package com.daoke.mobileserver.user.entity;

import java.io.Serializable;

/**
 * 举报信息
 * Created by chenmaomao on 5/21 0021.
 */
public class ReportInfo implements Serializable {

    private static final long serialVersionUID = -3265222101972232138L;

    private Integer id; //主键
    private String accountID; //用户id
    private String content; //举报内容
    private Integer reportType; //举报类型 1:好友 2:频道
    private String reportObject; //举报对象
    private Integer isValid; //是否有效 1:有效 0:无效
    private Long createTime; //创建时间
    private Long updateTime; //修改时间

    public ReportInfo() {
    }

    public ReportInfo(Integer id, String accountID, String content, Integer reportType, String reportObject, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.accountID = accountID;
        this.content = content;
        this.reportType = reportType;
        this.reportObject = reportObject;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ReportInfo{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", content='" + content + '\'' +
                ", reportType=" + reportType +
                ", reportObject='" + reportObject + '\'' +
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

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportObject() {
        return reportObject;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject;
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
