package com.daoke.mobileserver.test;

import com.daoke.mobileserver.common.service.RedisService;
import com.daoke.mobileserver.user.service.IUserGradeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * User: chenlong
 * Date: 2015/3/26
 * Time: 13:36
 */
public class TestRedis  {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisHget() {
        String appKey = "11";
        String secret = null;
        String IMEI = "222";

        Object[] values = {appKey, secret, IMEI};
        System.out.println("values:" + values);
        System.out.println("values:" + values);
    }


    @Test
    public void testRedisNormal() {
        Jedis jedis = new Jedis("192.168.1.11", 6379);
        System.out.println("appKeyInfo:" + jedis.hgetAll("1560306056:appKeyInfo"));


        System.out.println("appKeyInfo:" + jedis.hget("appKeyInfo", "1560306056"));
        System.out.println("appKeyInfo:" + jedis.get("appKeyInfo"));
        System.out.println("appKeyInfo:" + jedis.get("appKey"));
        System.out.println("appKeyInfo:" + jedis.get("secret"));
        jedis.disconnect();
    }


    @Test
    public void test11() {
        try {
            long stratTime = System.currentTimeMillis();
            for (int i =0; i<100000; i++){
                HashSet set = new HashSet();
                set.add("111");
                set.add("222");
                set.add("333");
                set.add("444");
                set.add("555");

                Iterator iterator = set.iterator();
                while(iterator.hasNext()){
                    iterator.next();
                   // System.out.println(iterator.next() );
                }
            }
            System.out.println("useTime:"+ (System.currentTimeMillis() - stratTime ));


            long stratTime1 = System.currentTimeMillis();
            for (int i =0; i<100000; i++){
               List<String> list = new ArrayList<String>();
                list.add("1111");
                list.add("2222");
                list.add("3333");
                list.add("4444");
                list.add("5555");

                for (int j =0 ;j <list.size();j++){
                    list.get(j);
                }

            }
            System.out.println("useTime1:"+ (System.currentTimeMillis() - stratTime1 ));

           //  System.out.println( String.format("%s，恭喜你，你已完成今日登陆的任务，系统奖励你%s谢尔，按加键马上领取，也可以离车后当日内在手机上领取奖励。","屌炸天",10));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
