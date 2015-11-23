package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "Package")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_Package",propOrder = {"header", "request"})
public class _Req_Package {

    @XmlElement(name = "Header")
    private _Req_Header header;

    @XmlElement(name = "Request")
    private _Req_Request request;

    public _Req_Header getHeader() {
        return header;
    }

    public void setHeader(_Req_Header header) {
        this.header = header;
    }

    public _Req_Request getRequest() {
        return request;
    }

    public void setRequest(_Req_Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "_Req_Package{" +
                "header=" + header +
                ", request=" + request +
                '}';
    }
}
