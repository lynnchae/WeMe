package com.daoke.mobileserver.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Calendar;
import java.util.Random;

/**

 import java.util.Calendar;
 import java.util.Random;

 import org.apache.commons.lang.RandomStringUtils;
 import org.apache.commons.lang.math.RandomUtils;



 /**
 * 产生随机数据工具类
 * @author chenlong
 * @date Oct 30, 2012 4:51:21 PM
 * @version V1.0
 */

public class RandomNumberUtil {
    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
     *
     * @param digit
     *            用户指定随机数据的位数
     */
    public static long randomLong(int digit) {
        if (digit >= 19 || digit <= 0)
            throw new IllegalArgumentException(
                    "digit should between 1 and 18(1<=digit<=18)");
        String s = RandomStringUtils.randomNumeric(digit - 1);
        return Long.parseLong(getPrefix() + s);
    }

    /**
     * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit
     *
     * @param minDigit
     *            用户指定随机数据的最小位数 minDigit>=1
     * @param maxDigit
     *            用户指定随机数据的最大位数 maxDigit<=18
     */
    public static long randomLong(int minDigit, int maxDigit) {
        if (minDigit > maxDigit) {
            throw new IllegalArgumentException("minDigit > maxDigit");
        }
        if (minDigit <= 0 || maxDigit >= 19) {
            throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
        }
        return randomLong(minDigit + getDigit(maxDigit - minDigit));
    }

    private static int getDigit(int max) {
        return RandomUtils.nextInt(max + 1);
    }

    /**
     * 保证第一位不是零
     *
     * @return
     */
    private static String getPrefix() {
        return prefix[RandomUtils.nextInt(9)] + "";
    }


    /**
     * 产生随机数
     *
     * @param id_len
     *            用户指定随机数据的位数
     */
    public static int genRandomNum(int id_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < id_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if(count == 0 && i ==0) {
                continue;
            }
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return Integer.valueOf(pwd.toString());
    }



    /**
     * 产生用户ID
     *
     *            用户指定随机数据的位数
     */
    public static int getRandomNumUserid() {
        int id_len =9;
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer randomid = new StringBuffer("");
        Random r = new Random();
        while (count < id_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if(count == 0 && (i ==0 || i == 3)) {
                continue;
            }
            if (i >= 0 && i < str.length) {
                randomid.append(str[i]);
                count++;
            }
        }
        //加入日期
        Calendar calendar = Calendar.getInstance();
        @SuppressWarnings("static-access")
        int day  =calendar.get(calendar.DAY_OF_MONTH);
        if(day <10){
            return Integer.valueOf(randomid.toString());
        }else{
            return Integer.valueOf( randomid.toString());
        }
    }




    /**
     * 产生随机动态ID
     *
     */
    public static int genRandomDynamicId() {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int id_len = 7;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer randomid = new StringBuffer("");
        Random r = new Random();
        while (count < id_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if(count == 0 && i ==0) {
                continue;
            }
            if (i >= 0 && i < str.length) {
                randomid.append(str[i]);
                count++;
            }
        }
        return Integer.valueOf(randomid.toString());
    }

    /**
     *生成随机码
     * @return
     */
    public static String genRandom4Ticket() {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int id_len = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer randomid = new StringBuffer("");
        Random r = new Random();
        while (count < id_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if(count == 0 && i ==0) {
                continue;
            }
            if (i >= 0 && i < str.length) {
                randomid.append(str[i]);
                count++;
            }
        }
        String numStr=randomid.toString();
        int sumNum=0;
        for(i=0;i<numStr.length();i++){
            sumNum=sumNum+Integer.parseInt(numStr.charAt(i)+"");
        }
        if(sumNum<10){
            return sumNum +numStr+"0";
        }else{
            return (sumNum%10)+numStr+""+(sumNum/10);
        }
    }

}
