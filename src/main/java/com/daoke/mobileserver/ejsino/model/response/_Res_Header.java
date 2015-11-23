package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_Header",propOrder = {"version", "ordertype","statuscode","outerno","message"})
public class _Res_Header {

    @XmlElement(required = false)
    private Integer version;

    @XmlElement(required = false)
    private String ordertype;

    @XmlElement(required = false)
    private Integer statuscode;

    @XmlElement(required = false)
    private String outerno;

    @XmlElement(required = false)
    private String message;


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

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getOuterno() {
        return outerno;
    }

    public void setOuterno(String outerno) {
        this.outerno = outerno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Header{" +
                "version=" + version +
                ", ordertype='" + ordertype + '\'' +
                ", statuscode=" + statuscode +
                ", outerno='" + outerno + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
