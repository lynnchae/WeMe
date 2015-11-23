package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "Request")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_Request")
public class _Req_Request {

    @XmlElement(name = "AreaInfo", required = false)
    private _Req_AreaInfo areaInfo;

    @XmlElement(name = "InsurInfo", required = false)
    private _Req_InsurInfo insurInfo;

    @XmlElement(name = "PlcInfo", required = false)
    private _Req_PlcInfo plcInfo;

    @XmlElement(name = "VehicleInfo", required = false)
    private _Req_VehicleInfo vehicleInfo;

    @XmlElement(name = "RiskInfo", required = false)
    private _Req_RiskInfo riskInfo;

    @XmlElement(name = "PremInfo", required = false)
    private _Req_PremInfo premInfo;

    @XmlElement(name = "AplInfo", required = false)
    private _Req_AplInfo aplInfo;

    @XmlElement(name = "LinkInfo", required = false)
    private _Req_LinkInfo linkInfo;

    public _Req_AreaInfo getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(_Req_AreaInfo areaInfo) {
        this.areaInfo = areaInfo;
    }

    public _Req_InsurInfo getInsurInfo() {
        return insurInfo;
    }

    public void setInsurInfo(_Req_InsurInfo insurInfo) {
        this.insurInfo = insurInfo;
    }

    public _Req_PlcInfo getPlcInfo() {
        return plcInfo;
    }

    public void setPlcInfo(_Req_PlcInfo plcInfo) {
        this.plcInfo = plcInfo;
    }

    public _Req_VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(_Req_VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public _Req_RiskInfo getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(_Req_RiskInfo riskInfo) {
        this.riskInfo = riskInfo;
    }

    public _Req_PremInfo getPremInfo() {
        return premInfo;
    }

    public void setPremInfo(_Req_PremInfo premInfo) {
        this.premInfo = premInfo;
    }

    public _Req_AplInfo getAplInfo() {
        return aplInfo;
    }

    public void setAplInfo(_Req_AplInfo aplInfo) {
        this.aplInfo = aplInfo;
    }

    public _Req_LinkInfo getLinkInfo() {
        return linkInfo;
    }

    public void setLinkInfo(_Req_LinkInfo linkInfo) {
        this.linkInfo = linkInfo;
    }

    @Override
    public String toString() {
        return "_Req_Request{" +
                "areaInfo=" + areaInfo +
                ", insurInfo=" + insurInfo +
                ", plcInfo=" + plcInfo +
                ", vehicleInfo=" + vehicleInfo +
                ", riskInfo=" + riskInfo +
                ", premInfo=" + premInfo +
                ", aplInfo=" + aplInfo +
                ", linkInfo=" + linkInfo +
                '}';
    }
}
