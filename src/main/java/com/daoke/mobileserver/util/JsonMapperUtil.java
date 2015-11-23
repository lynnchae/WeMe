package com.daoke.mobileserver.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenmaomao on 2015/3/28.
 */
public class JsonMapperUtil {

    public static String mapperJson2String(String errorcode,Object object){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ERRORCODE",errorcode);
        map.put("RESULT",object);
        String mapStr = "";
        try {
            mapStr = JsonMapper.toJson(map,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapStr;
    }
    public static String mapperJson2String(String errorcode){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ERRORCODE",errorcode);
        String mapStr = "";
        try {
            mapStr = JsonMapper.toJson(map,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapStr;
    }
}
