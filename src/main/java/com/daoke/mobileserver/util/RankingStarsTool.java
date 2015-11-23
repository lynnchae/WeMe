package com.daoke.mobileserver.util;

/**
 * Created by huanghongyang on 2015/4/20.
 */

import com.daoke.mobileserver.user.entity.IndexRankingDetail;

import java.util.List;
import java.util.Map;

/**
 * 计算 排名中星星数工具类
 */
public class RankingStarsTool {
    /**
     * 开始的时候三颗空星星
     * 1天完成300km 3颗星星填满 ；
     * 3天完成300km 两颗半满星星；
     * 7天完成300km 两颗满星星；
     * 15天完成300km 一颗半满星星；
     * 25天完成300km 一颗满星星；
     * 31天完成 半颗满星星
     *
     * @param rankingDetails
     */
    public static void setMileageCostTimeStar(List<IndexRankingDetail> rankingDetails) {
        float number = 0F;
        for (IndexRankingDetail detail : rankingDetails) {
            int value = detail.getIndexValue();
            if (value > 0 && value <= 1) {
                number = 3.0F;
            } else if (value > 1 && value <= 3) {
                number = 2.5F;
            } else if (value > 3 && value <= 7) {
                number = 2.0F;
            } else if (value > 7 && value <= 15) {
                number = 1.5F;
            } else if (value > 15 && value <= 25) {
                number = 1.0F;
            } else if (value > 25 && value <= 31) {
                number = 0.5F;
            } else {
                number = 0F;
            }
            detail.setStars(number);
        }
    }

    /**
     * 开始的时候三颗空星星
     * 每月捐献100密点 半颗满星星；
     * 500密点 一颗满星星；
     * 1000密点 一颗半满星星；
     * 2000密点 两颗满星星；
     * 3000密点 两颗半满星星；
     * 4000密点 三颗满星星；
     */
    public static void setMePointStar(List<IndexRankingDetail> rankingDetails) {
        float value = 0;
        for (IndexRankingDetail detail : rankingDetails) {
            int mePoint = detail.getIndexValue();
            if (mePoint >= 100 & mePoint < 500) {
                value = 0.5F;
            } else if (mePoint >= 500 && mePoint < 1000) {
                value = 1.0F;
            } else if (mePoint >= 1000 && mePoint < 2000) {
                value = 1.5F;
            } else if (mePoint >= 2000 && mePoint < 3000) {
                value = 2.0F;
            } else if (mePoint >= 3000 && mePoint < 4000) {
                value = 2.5F;
            } else if (mePoint >= 4000) {
                value = 3.0F;
            }
            detail.setStars(value);
        }
    }

    /**
     * 开始的时候三颗空星星
     * 驾驶1天 半颗星星；
     * 连续驾驶5天 一颗星星；
     * 连续驾驶10天 一颗半星星；
     * 连续驾驶15天 两颗星星；
     * 连续驾驶20天 两颗半星星；
     * 连续驾驶25天 3颗星星；
     */
    public static void setDriverDaysStar(List<IndexRankingDetail> rankingDetails) {
        float value = 0;
        for (IndexRankingDetail detail : rankingDetails) {
            int driverDays = detail.getIndexValue();
            if (driverDays >= 1 && driverDays < 5) {
                value = 0.5F;
            } else if (driverDays >= 5 && driverDays < 10) {
                value = 1.0F;
            } else if (driverDays >= 10 && driverDays < 15) {
                value = 1.5F;
            } else if (driverDays >= 15 && driverDays < 20) {
                value = 2.0F;
            } else if (driverDays >= 20 && driverDays < 25) {
                value = 2.5F;
            } else if (driverDays >= 25) {
                value = 3.0F;
            }
            detail.setStars(value);
        }
    }

    /**
     * 开始的时候三颗空星星
     * 新增300km 半颗星星；
     * 新增500km,一颗星星；
     * 新增1000km,一颗半星星；
     * 新增1600km,两颗星星；
     * 新增2500km 两颗半星星；
     * 新增3500km 三颗星星
     */
    public static void setMileageSumStar(List<IndexRankingDetail> rankingDetails) {
        float value = 0;
        for (IndexRankingDetail detail : rankingDetails) {
            int mileageSum = detail.getIndexValue();
            if (mileageSum >= 100 && mileageSum < 500) {
                value = 0.5F;
            } else if (mileageSum >= 500 && mileageSum < 1000) {
                value = 1.0F;
            } else if (mileageSum >= 1000 && mileageSum < 1600) {
                value = 1.5F;
            } else if (mileageSum >= 1600 && mileageSum < 2500) {
                value = 2.0F;
            } else if (mileageSum >= 2500 && mileageSum < 3500) {
                value = 2.5F;
            } else if (mileageSum >= 3500) {
                value = 3.0F;
            }
            detail.setStars(value);
        }
    }

    /**
     * 每天的开关机做计算，
     * 在daoke_usergrade数据库中取到每天的驾驶评分的平均值（需要告诉任心伦一下）
     * 70分 半颗星星，
     * 75分 一颗星星，
     * 80分 一颗半星星，
     * 85分 两颗星星，
     * 90分 两颗半星星，
     * 95分 三颗星星；
     */
    public static void setDriverGradeStar(List<IndexRankingDetail> rankingDetails) {
        float value = 0;
        for (IndexRankingDetail detail : rankingDetails) {
            int driverGrade = detail.getIndexValue();
            if (driverGrade >= 70 && driverGrade < 75) {
                value = 0.5F;
            } else if (driverGrade >= 75 && driverGrade < 80) {
                value = 1.0F;
            } else if (driverGrade >= 80 && driverGrade < 85) {
                value = 1.5F;
            } else if (driverGrade >= 85 && driverGrade < 90) {
                value = 2.0F;
            } else if (driverGrade >= 90 && driverGrade < 95) {
                value = 2.5F;
            } else if (driverGrade >= 95) {
                value = 3.0F;
            }
            detail.setStars(value);
        }
    }
    /**
     *
     */
    public static void setTweetCountStar(List<IndexRankingDetail> rankingDetails){

    }

    public static List<Map<String,Object>> toPairKey(List<Map<String,Object>> map1 ,List<Map<String,Object>>  map2,String Key){
          if (map1==null||map2==null)
              return null;

        for (Map<String,Object> m1 : map1){
            for (Map<String,Object> m2 : map2){
                  if (m1.get(Key).equals(m2.get(Key))) {
                      m1.putAll(m2);
                      break;
                  }
            }
        }
        return  map1;
    }
    public static String getColumNameWithRankType(int RandType) {
        String sRankType = "-1";
        switch (RandType) {
            case 1:
                sRankType = "mileageSum";
                break;
            case 2:
                sRankType = "mileageCostTime";
                break;
            case 3:
                sRankType = "mePoint";
                break;
            case 4:
                sRankType = "driverDays";
                break;
            case 5:
                sRankType = "driverGrade";
                break;
            case 6:
                sRankType = "tweetCount";
                break;
            case 7:
                sRankType = "taskIndex";
                break;
            case 8:
                sRankType = "environmentalIndex";
                break;
        }
        return sRankType;
    }

    public static float getRuleTextWithRankType (int rankType,int valueItem){
        float value = 0;
        switch (rankType) {
            case 1:
                if(valueItem < 300){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 300 & valueItem < 500){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 500 & valueItem < 1000){
                    value = 1f;
                    break;
                }else if(valueItem >= 1000 & valueItem < 1600){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 1600 & valueItem < 2500){
                    value = 2f;
                    break;
                }else if(valueItem >= 2500 & valueItem < 3500){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
            case 2:
                if(valueItem <= 1 & valueItem > 0){
                    value = 3.0f;
                    break;
                }else if(valueItem > 1 & valueItem <= 3){
                    value = 2.5f;
                    break;
                }else if(valueItem > 3 & valueItem <= 7){
                    value = 2.0f;
                    break;
                }else if(valueItem > 7 & valueItem <= 15){
                    value = 1.5f;
                    break;
                }else if(valueItem > 15 & valueItem <= 25){
                    value = 1f;
                    break;
                }else if(valueItem > 25 & valueItem <= 30){
                    value = 0.5f;
                    break;
                }else{
                    value = 0f;
                    break;
                }
            case 3:
                if(valueItem < 100){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 100 & valueItem < 500){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 500 & valueItem < 1000){
                    value = 1f;
                    break;
                }else if(valueItem >= 1000 & valueItem < 2000){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 2000 & valueItem < 3000){
                    value = 2f;
                    break;
                }else if(valueItem >= 3000 & valueItem < 4000){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
            case 4:
              /*  sRankType = "驾驶天数：本月行驶天数。\n" +
                        "任务规则：1天，半颗星星； 5天，一颗星星；\n" +
                        "          10天，一颗半星星；15天，两颗星星；\n" +
                        "          20天，两颗半星星；25天，三颗星星。\n";*/
                if(valueItem < 1){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 1 & valueItem < 5){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 5 & valueItem < 10){
                    value = 1f;
                    break;
                }else if(valueItem >= 10 & valueItem < 15){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 15 & valueItem < 20){
                    value = 2f;
                    break;
                }else if(valueItem >= 20 & valueItem < 25){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
           /* sRankType = "驾驶评分：根据用户驾驶行为综合评定。例如，急刹车、超速等行为都会影响评分。\n" +
                    "任务规则：70分，半颗星星； 75分，一颗星星；\n" +
                    "          80分，一颗半星星；85分，两颗星星；\n" +
                    "          90分，两颗半星星；95分，三颗星星。\n";*/
            case 5:
                if(valueItem < 70){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 70 & valueItem < 75){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 75 & valueItem < 80){
                    value = 1f;
                    break;
                }else if(valueItem >= 80 & valueItem < 85){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 85 & valueItem < 90){
                    value = 2f;
                    break;
                }else if(valueItem >= 90 & valueItem < 95){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
            case 6:
                break;
            case 7:
                if(valueItem < 10){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 10 & valueItem < 40){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 40 & valueItem < 50){
                    value = 1f;
                    break;
                }else if(valueItem >= 50 & valueItem < 60){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 60 & valueItem < 70){
                    value = 2f;
                    break;
                }else if(valueItem >= 70 & valueItem < 80){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
            case 8:
                if(valueItem < 60){
                    value = 0.0f;
                    break;
                }else if(valueItem >= 60 & valueItem < 70){
                    value = 0.5f;
                    break;
                }else if(valueItem >= 70 & valueItem < 80){
                    value = 1f;
                    break;
                }else if(valueItem >= 80 & valueItem < 85){
                    value = 1.5f;
                    break;
                }else if(valueItem >= 85 & valueItem < 90){
                    value = 2f;
                    break;
                }else if(valueItem >= 90 & valueItem < 95){
                    value = 2.5f;
                    break;
                }else{
                    value = 3f;
                    break;
                }
            default:
        }
        return   value;
    }
    public static String getRuleTextWithRankType (int rankType){
        String  sRankType = null;
        switch (rankType) {
            case 1:
                sRankType =
                        "300公里，半颗星星；500公里，一颗星星\n1000公里，一颗半星星；1600公里，两颗星星；\n" +
                        "2500公里，两颗半星星；3500公里，三颗星星。";
                break;
            case 2:
                sRankType =
                        "30天，半颗星星；25天，一颗星星；\n" +
                        "15天，一颗半星星；7天，两颗星星；\n" +
                        "3天，两颗半星星；1天，三颗星星。";
                break;
            case 3:
                sRankType =
                        "100密点，半颗星星；500密点，一颗星星；\n" +
                        "1000密点，一颗半星星；2000密点，两颗星星；\n" +
                        "3000密点，两颗半星星；4000密点，三颗星星。";
                break;
            case 4:
                sRankType =
                        "1天，半颗星星；5天，一颗星星；\n" +
                        "10天，一颗半星星；15天，两颗星星；\n" +
                        "20天，两颗半星星；25天，三颗星星。";
                break;
            case 5:
                sRankType =
                        "70分，半颗星星；75分，一颗星星；\n" +
                        "80分，一颗半星星；85分，两颗星星；\n" +
                        "90分，两颗半星星；95分，三颗星星。";
                break;
            case 6:
                sRankType = "";
                break;
            case 7:
                sRankType =
                        "10，半颗星星；40，一颗星星；\n" +
                        "50，一颗半星星；60，两颗星星；\n" +
                        "70，两颗半星星；80，三颗星星。";
                break;
            case 8:
                sRankType =
                        "60，半颗星星；70，一颗星星；\n" +
                        "80，一颗半星星；85，两颗星星；\n" +
                        "90，两颗半星星；95，三颗星星。";
                break;
            default:
                sRankType="";
        }
        return   sRankType;
    }
    public static String getRuleTextWithRankTypeTitle (int rankType){
        String  sRankType = null;
        switch (rankType) {
            case 1:
                sRankType = "本月新增的行驶里程。";

                break;
            case 2:
                sRankType = "本月行驶300公里所需天数";
                break;
            case 3:
                sRankType = "本月捐献的密点数。";
                break;
            case 4:
                sRankType = "本月驾驶天数。";
                break;
            case 5:
                sRankType = "根据用户驾驶行为综合评定。例如，急刹车、超速等行为都会影响评分。";
                break;
            case 6:
                sRankType = "";
                break;
            case 7:
                sRankType = "本月完成的任务数与总任务数的比值。";
                break;
            case 8:
                sRankType = "单次行驶距离大于3公里为环保。环保次数与本月总行驶次数的比值。";
                break;
            default:
                sRankType="";
        }
        return   sRankType;
    }
}
