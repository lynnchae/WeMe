package com.daoke.mobileserver.test;

import com.daoke.mobileserver.shareTimesWeekly.service.impl.ShareTimesWeeklyServiceImpl;
import com.daoke.mobileserver.util.AbDateUtil;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by chenmaomao on 2015/4/9.
 */
public class DateTest {

    public static void main(String[] args){
//        ShareTimesWeeklyServiceImpl st = new ShareTimesWeeklyServiceImpl();
//        try {
//           boolean flag =  st.judgeThisWeek(new Timestamp(System.currentTimeMillis()));
//            System.out.println(flag);
//
//            String pattern = "yyyyMMdd";
//            String firstDay = AbDateUtil.getFirstDayOfMonth(pattern);
//            String lastDay  = AbDateUtil.getLastDayOfMonth(pattern);
//            System.out.println(firstDay);
//            System.out.println(lastDay);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String appkey="7686787";
        Integer cityCode=null;
        String channelNumber="3432212";
        Integer catalogID=123;
        String channelName="wrew";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appKey", appkey);
        param.put("cityCode", cityCode+"");
        param.put("channelNumber", channelNumber);
        param.put("catalogID", catalogID+"");
        param.put("channelName", channelName);
        for (String key : param.keySet()) {
            System.out.println("key= "+ key + " and value= " + param.get(key));
        }
        List<Object> objList = new ArrayList<Object>();
        objList.add(null);
        objList.add(1);
        objList.add(2);
        for(Object obj : objList){
            System.out.println(obj);
        }
    }
}
