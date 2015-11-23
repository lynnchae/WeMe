package com.daoke.mobileserver.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenmaomao on 2015/4/16.
 */
public class NumberUtil {
    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
