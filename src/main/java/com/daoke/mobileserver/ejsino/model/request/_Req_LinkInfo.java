package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/13.
 *联系人
 */

@XmlRootElement(name = "LinkInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_LinkInfo")
public class _Req_LinkInfo {

    @XmlElement(name = "name")
    private String name;	//姓名

    @XmlElement(name = "mobile")
    private String mobile;	//手机

    @XmlElement(name = "address")
    private String address;	//地址

    @XmlElement(name = "invoice")
    private String invoice;	//发票抬头

    @XmlElement(name = "zipcode")
    private String zipcode;	//邮编

    @XmlElement(name = "email")
    private String email;	//邮箱

    @XmlElement(name = "paytype")
    private Integer paytype;	//支付方式

    @XmlElement(name = "realpaymode")
    private String realpaymode;	//支付类型


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getRealpaymode() {
        return realpaymode;
    }

    public void setRealpaymode(String realpaymode) {
        this.realpaymode = realpaymode;
    }


    @Override
    public String toString() {
        return "_Req_LinkInfo{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", invoice='" + invoice + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", email='" + email + '\'' +
                ", paytype='" + paytype + '\'' +
                ", realpaymode='" + realpaymode + '\'' +
                '}';
    }
}
