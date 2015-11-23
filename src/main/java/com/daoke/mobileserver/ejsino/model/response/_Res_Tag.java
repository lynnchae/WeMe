package com.daoke.mobileserver.ejsino.model.response;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzp on 2014/12/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Res_Tag")
@XmlRootElement(name = "Tag")
public class _Res_Tag {

    @XmlElement(name = "Definition")
    private List<_Res_Definition> definitions = new ArrayList<_Res_Definition>();

    public List<_Res_Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<_Res_Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "definitions=" + definitions +
                '}';
    }
}
