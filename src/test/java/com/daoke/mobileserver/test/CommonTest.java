package com.daoke.mobileserver.test;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghongyang on 2015/3/24.
 */
public class CommonTest {
    @Test
    public void testSplit(){
        String url = "sdasdasd,asdasd,asdasd";
       System.out.println(Arrays.asList(StringUtils.split(url,",")));
    }
    @Test
    public void testMap(){
        Map<String,Object> map = new HashMap<String, Object>();
       System.out.println(map.toString());
        map.put("aaa",123);
        System.out.println(map.toString());
    }
}
