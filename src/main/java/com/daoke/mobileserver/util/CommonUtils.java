package com.daoke.mobileserver.util;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public class CommonUtils {
    public static boolean notEmpty(List target){
        if(target!=null&&target.size()>0){
            return true;
        }
        return  false;
    }

    public static boolean notEmpty(String target){
        if(StringUtils.isNotEmpty(target)){
            return true;
        }
        return  false;
    }
}
