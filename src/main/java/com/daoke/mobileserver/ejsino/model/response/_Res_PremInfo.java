package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/13.
 */
@XmlRootElement(name = "PremInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_PremInfo")
public class _Res_PremInfo {

    @XmlElement(name = "remark1")
    private String remark1;//	车辆损失险

    @XmlElement(name = "remark2")
    private String remark2;//	商业第三者责任险

    @XmlElement(name = "remark3")
    private String remark3;//	全车盗抢险

    @XmlElement(name = "remark4")
    private String remark4;//	司机座位责任险

    @XmlElement(name = "remark5")
    private String remark5;//	乘客座位责任险

    @XmlElement(name = "remark6")
    private String remark6;//	自燃损失险

    @XmlElement(name = "remark7")
    private String remark7;//	车身划痕险

    @XmlElement(name = "remark8")
    private String remark8;//	玻璃单独破碎险

    @XmlElement(name = "remark9")
    private String remark9;//	车辆损失险不计免赔

    @XmlElement(name = "remark10")
    private String remark10;//	商业第三者责任险 不计免赔

    @XmlElement(name = "remark11")
    private String remark11;//	全车盗抢险不计免赔

    @XmlElement(name = "remark12")
    private String remark12;//	司机座位责任险不计免赔

    @XmlElement(name = "remark13")
    private String remark13;//	涉水行使损失险

    @XmlElement(name = "remark14")
    private String remark14;//	附加险不计免赔

    @XmlElement(name = "remark15")
    private String remark15;//	乘客座位责任险不计免赔

    @XmlElement(name = "forcepremium")
    private String forcepremium; //交强险

    @XmlElement(name = "vehicletaxamount")
    private String vehicletaxamount; //车船税

    @XmlElement(name = "businesspremium")
    private String businesspremium;  //商业险保费(折扣后

    @XmlElement(name = "realpremium")//实际保费
    private String realpremium;

    @XmlElement(name = "totalremium")
    private String totalremium;  //应缴总保费(折扣


    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public String getRemark7() {
        return remark7;
    }

    public void setRemark7(String remark7) {
        this.remark7 = remark7;
    }

    public String getRemark8() {
        return remark8;
    }

    public void setRemark8(String remark8) {
        this.remark8 = remark8;
    }

    public String getRemark9() {
        return remark9;
    }

    public void setRemark9(String remark9) {
        this.remark9 = remark9;
    }

    public String getRemark10() {
        return remark10;
    }

    public void setRemark10(String remark10) {
        this.remark10 = remark10;
    }

    public String getRemark11() {
        return remark11;
    }

    public void setRemark11(String remark11) {
        this.remark11 = remark11;
    }

    public String getRemark12() {
        return remark12;
    }

    public void setRemark12(String remark12) {
        this.remark12 = remark12;
    }

    public String getRemark13() {
        return remark13;
    }

    public void setRemark13(String remark13) {
        this.remark13 = remark13;
    }

    public String getRemark14() {
        return remark14;
    }

    public void setRemark14(String remark14) {
        this.remark14 = remark14;
    }

    public String getRemark15() {
        return remark15;
    }

    public void setRemark15(String remark15) {
        this.remark15 = remark15;
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

    public String getBusinesspremium() {
        return businesspremium;
    }

    public void setBusinesspremium(String businesspremium) {
        this.businesspremium = businesspremium;
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
}
