package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "PlcInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_PlcInfo", propOrder = {"plcstartdate", "plcenddate"})
public class _Req_PlcInfo {

    @XmlElement(name = "plcstartdate")
    private String plcstartdate;

    @XmlElement(name = "plcenddate")
    private String plcenddate;

    public String getPlcstartdate() {
        return plcstartdate;
    }

    public void setPlcstartdate(String plcstartdate) {
        this.plcstartdate = plcstartdate;
    }

    public String getPlcenddate() {
        return plcenddate;
    }

    public void setPlcenddate(String plcenddate) {
        this.plcenddate = plcenddate;
    }

    @Override
    public String toString() {
        return "_Req_PlcInfo{" +
                "plcstartdate='" + plcstartdate + '\'' +
                ", plcenddate='" + plcenddate + '\'' +
                '}';
    }
}
