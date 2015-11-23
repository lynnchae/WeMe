package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_Response")
@XmlRootElement(name = "Response")
public class _Res_Response {


    @XmlElement(name = "Tag", required = false)
    @XmlElementWrapper(name = "TagsCarSubjList")
    private List<_Res_Tag> tagsCarSubjList;

    @XmlElement(name = "Tag", required = false)
    @XmlElementWrapper(name="TagsCarRiskList")
    private List<_Res_Tag> tagsCarRiskList;

    @XmlElement(name = "PremInfo", required = false)
    private _Res_PremInfo premInfo;

    @XmlElement(name = "InFrom", required = false)
    private _Res_InFrom inFrom;

    @XmlElement(name = "PlcInfo", required = false)
    private _Res_PlcInfo plcInfo;

    @XmlElement(name = "PayURL", required = false)
    private _Res_PayURL payURL;

    @XmlElement(name = "Inform", required = false)
    private _Res_InForm inForm;

    public List<_Res_Tag> getTagsCarSubjList() {
        return tagsCarSubjList;
    }

    public void setTagsCarSubjList(List<_Res_Tag> tagsCarSubjList) {
        this.tagsCarSubjList = tagsCarSubjList;
    }

    public List<_Res_Tag> getTagsCarRiskList() {
        return tagsCarRiskList;
    }

    public void setTagsCarRiskList(List<_Res_Tag> tagsCarRiskList) {
        this.tagsCarRiskList = tagsCarRiskList;
    }

    public _Res_PremInfo getPremInfo() {
        return premInfo;
    }

    public void setPremInfo(_Res_PremInfo premInfo) {
        this.premInfo = premInfo;
    }

    public _Res_InFrom getInFrom() {
        return inFrom;
    }

    public void setInFrom(_Res_InFrom inFrom) {
        this.inFrom = inFrom;
    }


    public _Res_PlcInfo getPlcInfo() {
        return plcInfo;
    }

    public void setPlcInfo(_Res_PlcInfo plcInfo) {
        this.plcInfo = plcInfo;
    }

    public _Res_PayURL getPayURL() {
        return payURL;
    }

    public void setPayURL(_Res_PayURL payURL) {
        this.payURL = payURL;
    }

    public _Res_InForm getInForm() {
        return inForm;
    }

    public void setInForm(_Res_InForm inForm) {
        this.inForm = inForm;
    }

    @Override
    public String toString() {
        return "_Res_Response{" +
                "tagsCarSubjList=" + tagsCarSubjList +
                ", tagsCarRiskList=" + tagsCarRiskList +
                ", premInfo=" + premInfo +
                ", inFrom=" + inFrom +
                ", plcInfo=" + plcInfo +
                ", payURL=" + payURL +
                ", inForm=" + inForm +
                '}';
    }
}
