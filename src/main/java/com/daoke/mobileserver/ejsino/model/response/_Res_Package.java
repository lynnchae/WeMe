package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Package")
@XmlType(name = "_Res_Package")
public class _Res_Package {
    @XmlElement(name="Header")
    private _Res_Header header;

    @XmlElement(name="Response")
    private _Res_Response response;

    public _Res_Header getHeader() {
        return header;
    }

    public void setHeader(_Res_Header header) {
        this.header = header;
    }

    public _Res_Response getResponse() {
        return response;
    }

    public void setResponse(_Res_Response response) {
        this.response = response;
    }


    @Override
    public String toString() {
        return "_Package{" +
                "header=" + header +
                ", response=" + response +
                '}';
    }
}
