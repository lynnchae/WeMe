package com.daoke.mobileserver.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**用户谢尔明细
 * Created by chenmaomao on 2015/4/1.
 */
public class UserRochelleDetail implements Serializable {
    /**
     * 记录号
     */
    private  Integer recordID;
    /**
     * 用户ID
     */
    private  String accountID;
    /**
     * imei号
     */
    private  String imei;
    /**
     * 规则ID
     */
    private String ruleCode;
    /**
     * 谢尔值
     */
    private Integer rochelle;

    /**
     * 领取状态  0未领取 1 已领取
     */
    private Integer receiveStatus =0;
    /**
     * 是否有效
     */
    private Integer isValid;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private  Date modifyDate;

    public UserRochelleDetail() {
    }


    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Integer getRochelle() {
        return rochelle;
    }

    public void setRochelle(Integer rochelle) {
        this.rochelle = rochelle;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    @Override
    public String toString() {
        return "UserRochelleDetail{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", imei='" + imei + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", rochelle=" + rochelle +
                ", receiveStatus=" + receiveStatus +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
