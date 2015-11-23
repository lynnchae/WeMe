package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/13.
 */

@XmlRootElement(name = "InFrom")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_InFrom")
public class _Res_InFrom {


    @XmlElement(name = "configbeforejudge")
    private String configbeforejudge;   //是否投保确认

    public String getConfigbeforejudge() {
        return configbeforejudge;
    }

    public void setConfigbeforejudge(String configbeforejudge) {
        this.configbeforejudge = configbeforejudge;
    }

    @Override
    public String toString() {
        return "_Res_InFrom{" +
                "configbeforejudge='" + configbeforejudge + '\'' +
                '}';
    }
}
