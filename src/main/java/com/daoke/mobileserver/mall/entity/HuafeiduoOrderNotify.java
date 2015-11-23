package com.daoke.mobileserver.mall.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 话费多订单回调实体
 * <p/>
 * User: chenlong
 * Date: 2014/12/24
 * Time: 19:13
 */
public class HuafeiduoOrderNotify implements Serializable {


    private static final long serialVersionUID = 4905741896475176542L;
    /**
     * 记录号
     */
    private int recordId;
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 话费多订单号
     */
    private String huafeiduoOrderId;
    /**
     *  面值
     */
    private double cardWorth;

    /**
     * 充值成本
     */
    private double price;

    /**
     * 订单处理结果	该值只可能为"success"和"failure"其中一项 "success"为充值成功,"failure"为充值失败
     */
    private String status;

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

    public HuafeiduoOrderNotify() {
    }


    public HuafeiduoOrderNotify(String orderId, String huafeiduoOrderId, double cardWorth, double price, String status, String phoneNumber, String remark) {
        this.orderId = orderId;
        this.huafeiduoOrderId = huafeiduoOrderId;
        this.cardWorth = cardWorth;
        this.price = price;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.remark = remark;
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

    public String getHuafeiduoOrderId() {
        return huafeiduoOrderId;
    }

    public void setHuafeiduoOrderId(String huafeiduoOrderId) {
        this.huafeiduoOrderId = huafeiduoOrderId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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


}
