package com.daoke.mobileserver.test;

import com.daoke.mobileserver.util.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: chenlong
 * Date: 2014/12/24
 * Time: 16:48
 */
public class ChongzhiTest {

    @Test
    public void checkPhone()throws IOException {
        HttpRequester requester = new HttpRequester();
        Map<String,String> param = new HashMap<String,String>();
        param.put("card_worth","1");
        param.put("phone_number","18502120620");
        param.put("api_key", ChongzhiAPI.API_KEY);
        String sign = ParameterUtil.getHuaFeiDuoSignData(param);
        sign = MD5Signature.sign(sign, ChongzhiAPI.SECRET_KEY);
        param.put("sign" ,sign);
        param.put("mod",ChongzhiAPI.ORDER_PHONE_CHECK);
        //调用话费多api
        HttpRespons respons = requester.sendGet(ChongzhiAPI.ORDER_PHONE_CHECK_API, param);
        Map<String,Object> map = (Map)JsonMapper.fromJson(respons.getContent(),Map.class);
        if(map != null && map.get("status") != null &&  map.get("status").equals("success")){
            System.out.println(map.toString());
        }else{

            System.out.println(map.toString());
        }
    }

    @Test

    public void createChongzhiOrder()throws  IOException{
        HttpRequester requester = new HttpRequester();
        Map<String,String> param = new HashMap<String,String>();
        param.put("card_worth","1");
        param.put("phone_number","18502120620");
        // param.put("notify_url",ChongzhiAPI.API_KEY);
        param.put("sp_order_id","2014122416530001");
        param.put("remark","冲值");
        param.put("api_key",ChongzhiAPI.API_KEY);
        String sign = ParameterUtil.getHuaFeiDuoSignData(param);
        sign = MD5Signature.sign(sign,ChongzhiAPI.SECRET_KEY);
        param.put("sign" ,sign);
        param.put("mod",ChongzhiAPI.ORDER_PHONE_SUBMIT);
        //调用话费多api
        HttpRespons respons = requester.sendGet(ChongzhiAPI.ORDER_PHONE_SUBMIT_API, param);
        Map<String,Object> map = (Map)JsonMapper.fromJson(respons.getContent(),Map.class);
        if(map != null && map.get("status") != null &&  map.get("status").equals("success")){
            System.out.println(map.toString());
        }else{
            System.out.println(map.toString());
        }

    }



}

