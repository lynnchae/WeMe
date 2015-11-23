package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "PlcInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_PlcInfo")
public class _Res_PlcInfo {

    @XmlElement(name = "payno")
    private String payno		;//支付号码

    @XmlElement(name = "bizplcno")
    private String bizplcno		;//商业险保单号

    @XmlElement(name = "forceplcno")
    private String forceplcno;//	交强险保单号

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getBizplcno() {
        return bizplcno;
    }

    public void setBizplcno(String bizplcno) {
        this.bizplcno = bizplcno;
    }

    public String getForceplcno() {
        return forceplcno;
    }

    public void setForceplcno(String forceplcno) {
        this.forceplcno = forceplcno;
    }
}
