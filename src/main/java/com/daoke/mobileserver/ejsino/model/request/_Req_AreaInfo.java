package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "AreaInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_AreaInfo", propOrder = {"companyno", "citycode"})
public class _Req_AreaInfo {

    @XmlElement(name = "companyno")
    private String companyno;

    @XmlElement(name = "citycode")
    private String citycode;


    public String getCompanyno() {
        return companyno;
    }

    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @Override
    public String toString() {
        return "_Req_AreaInfo{" +
                "companyno='" + companyno + '\'' +
                ", citycode='" + citycode + '\'' +
                '}';
    }
}
