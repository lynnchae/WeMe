package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/16.
 * 信息
 */
@XmlRootElement(name = "InForm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_InForm")
public class _Res_InForm {

    @XmlElement(name = "bizInform")
    private String bizInform;//商业险确认页面(URL)

    @XmlElement(name = "forceInform")
    private String forceInform;// 交强险确认页面(URL)

    @XmlElement(name = "appnt")
    private String appnt;//特别约定

    @XmlElement(name = "remark")
    private String remark;//告知

    public String getBizInform() {
        return bizInform;
    }

    public void setBizInform(String bizInform) {
        this.bizInform = bizInform;
    }

    public String getForceInform() {
        return forceInform;
    }

    public void setForceInform(String forceInform) {
        this.forceInform = forceInform;
    }

    public String getAppnt() {
        return appnt;
    }

    public void setAppnt(String appnt) {
        this.appnt = appnt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
