package com.daoke.mobileserver.util;

import java.util.Random;

/**
 * Created by wangzp on 2015/5/23.
 */
public class GenerateCodeUtil {

    public static  String generateCode(){

       return  ((int)((Math.random()*9+1)*100000))+"";
    }
}
