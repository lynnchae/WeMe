package com.daoke.mobileserver.ejsino.model.response;


import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PackageList")
@XmlType(name = "_Res_PackageList")
public class _Res_PackageList {

    @XmlElement(name = "Package")
    private _Res_Package _package;

    public _Res_Package get_package() {
        return _package;
    }

    public void set_package(_Res_Package _package) {
        this._package = _package;
    }

    @Override
    public String toString() {
        return "_PackageList{" +
                "_package=" + _package +
                '}';
    }
}