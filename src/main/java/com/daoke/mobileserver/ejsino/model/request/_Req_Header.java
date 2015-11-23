package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_Header")
public class _Req_Header {

    @XmlElement(name = "version", required = false)
    private Integer version;

    @XmlElement(name ="ordertype", required = false)
    private String ordertype;

    @XmlElement(name ="username", required = false)
    private String username;

    @XmlElement(name ="password", required = false)
    private String password;

    @XmlElement(name = "outerno")
    private String outerno;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOuterno() {
        return outerno;
    }

    public void setOuterno(String outerno) {
        this.outerno = outerno;
    }

    @Override
    public String toString() {
        return "_Req_Header{" +
                "version=" + version +
                ", ordertype='" + ordertype + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", outerno='" + outerno + '\'' +
                '}';
    }
}
