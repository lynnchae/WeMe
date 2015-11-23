package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/13.
 * 保费信息
 */

@XmlRootElement(name = "PremInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_PremInfo")
public class _Req_PremInfo {

    @XmlElement(name = "businesspremium")
    private String businesspremium ;//商业险保费

    @XmlElement(name = "forcepremium")
    private String forcepremium;    //交强险保费

    @XmlElement(name = "vehicletaxamount")
    private String vehicletaxamount;//车船税

    @XmlElement(name = "realpremium")
    private String realpremium;     //实际保费

    @XmlElement(name = "totalremium")
    private String totalremium;     //应缴总保费

    public String getBusinesspremium() {
        return businesspremium;
    }

    public void setBusinesspremium(String businesspremium) {
        this.businesspremium = businesspremium;
    }

    public String getForcepremium() {
        return forcepremium;
    }

    public void setForcepremium(String forcepremium) {
        this.forcepremium = forcepremium;
    }

    public String getVehicletaxamount() {
        return vehicletaxamount;
    }

    public void setVehicletaxamount(String vehicletaxamount) {
        this.vehicletaxamount = vehicletaxamount;
    }

    public String getRealpremium() {
        return realpremium;
    }

    public void setRealpremium(String realpremium) {
        this.realpremium = realpremium;
    }

    public String getTotalremium() {
        return totalremium;
    }

    public void setTotalremium(String totalremium) {
        this.totalremium = totalremium;
    }


    @Override
    public String toString() {
        return "_Req_PremInfo{" +
                "businesspremium='" + businesspremium + '\'' +
                ", forcepremium='" + forcepremium + '\'' +
                ", vehicletaxamount='" + vehicletaxamount + '\'' +
                ", realpremium='" + realpremium + '\'' +
                ", totalremium='" + totalremium + '\'' +
                '}';
    }
}
