package com.daoke.mobileserver.ejsino.model.request;


import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PackageList")
@XmlType(name = "_Req_PackageList")
public class _Req_PackageList {

    @XmlElement(name = "Package")
    private _Req_Package _package;

    public _Req_Package get_package() {
        return _package;
    }

    public void set_package(_Req_Package _package) {
        this._package = _package;
    }

    @Override
    public String toString() {
        return "_PackageList{" +
                "_package=" + _package +
                '}';
    }
}