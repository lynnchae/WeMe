package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/15.
 * 支付信息(PayURL)
 */
@XmlRootElement(name = "PayURL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_PayURL")
public class _Res_PayURL {


    @XmlElement(name = "paypath")
    private String paypath;


    public String getPaypath() {
        return paypath;
    }

    public void setPaypath(String paypath) {
        this.paypath = paypath;
    }

    @Override
    public String toString() {
        return "_Res_PayURL{" +
                "paypath='" + paypath + '\'' +
                '}';
    }
}
