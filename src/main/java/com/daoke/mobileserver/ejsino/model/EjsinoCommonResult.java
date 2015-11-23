package com.daoke.mobileserver.ejsino.model;

import com.daoke.mobileserver.ejsino.model.response.*;

import java.util.List;

/**
 * Created by wangzp on 2014/12/13.
 * 接口返回值封装类。
 */
public class EjsinoCommonResult {

    private List<Object> tagList;

    private Integer riskInfoFlag;

    private String btnText;

    private String step;

    private String message;

    private Integer resultCode;

    private _Res_PremInfo premInfo;

    private _Res_InFrom inFrom;

    private _Res_InForm inForm;

    private _Res_PlcInfo plcInfo;

    private _Res_PayURL payURL;

    public List<Object> getTagList() {
        return tagList;
    }

    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

    public Integer getRiskInfoFlag() {
        return riskInfoFlag;
    }

    public void setRiskInfoFlag(Integer riskInfoFlag) {
        this.riskInfoFlag = riskInfoFlag;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
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
}
