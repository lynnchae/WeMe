package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_Definition")
@XmlRootElement(name = "Definition")
public class _Res_Definition {

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlValue
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "_Definition{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
