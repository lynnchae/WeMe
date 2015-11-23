package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/13.
 * 投保人信息(AplInfo)
 */
@XmlRootElement(name = "AplInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_AplInfo")
public class _Req_AplInfo {

    @XmlElement(name = "name")
    private String name;//姓名

    @XmlElement(name = "certtype")
    private String certtype;//证件类型

    @XmlElement(name = "certno")
    private String certno;//证件号码

    @XmlElement(name = "sex")
    private String sex; //性别

    @XmlElement(name = "birth")
    private String birth;//	生日

    @XmlElement(name = "email")
    private String email;//	邮箱

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCerttype() {
        return certtype;
    }

    public void setCerttype(String certtype) {
        this.certtype = certtype;
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "_Req_AplInfo{" +
                "name='" + name + '\'' +
                ", certtype='" + certtype + '\'' +
                ", certno='" + certno + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
