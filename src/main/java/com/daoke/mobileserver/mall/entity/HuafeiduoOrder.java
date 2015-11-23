package com.daoke.mobileserver.mall.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 话费多订单实体
 * <p/>
 * User: chenlong
 * Date: 2014/12/24
 * Time: 19:13
 */
public class HuafeiduoOrder implements Serializable {


    private static final long serialVersionUID = 4905741896475176542L;
    /**
     * 记录号
     */
    private int recordId;
    /**
     * 帐户ID
     */
    private String accountId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     *  面值
     */
    private double cardWorth;

    /**
     * 充值成本
     */
    private double price;

    /**
     * 冲值状态  0 未成功  1 成功 2 冲值中
     */
    private short status;

    /**
     *充值的手机号
     */
    private String phoneNumber;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 是否有效 1 有效 0无效
     */
    private short isValid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    public HuafeiduoOrder() {
    }

    public HuafeiduoOrder(double cardWorth, double price, short status, String phoneNumber, String remark, short isValid,String accountId) {
        this.cardWorth = cardWorth;
        this.price = price;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.remark = remark;
        this.isValid = isValid;
        this.accountId = accountId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getCardWorth() {
        return cardWorth;
    }

    public void setCardWorth(double cardWorth) {
        this.cardWorth = cardWorth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public short getIsValid() {
        return isValid;
    }

    public void setIsValid(short isValid) {
        this.isValid = isValid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "HuafeiduoOrder{" +
                "recordId=" + recordId +
                ", accountId='" + accountId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", cardWorth=" + cardWorth +
                ", price=" + price +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", isValid=" + isValid +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
