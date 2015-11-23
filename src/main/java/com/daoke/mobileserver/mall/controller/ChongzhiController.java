package com.daoke.mobileserver.mall.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrder;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrderNotify;
import com.daoke.mobileserver.mall.service.IMallService;
import com.daoke.mobileserver.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *话费总值
 *
 * User: chenlong
 * Date: 2014/12/24
 * Time: 14:04
 */
@Controller
@RequestMapping("/mall/")
public class ChongzhiController {

    private static final Logger log = LoggerFactory.getLogger(ChongzhiController.class);


    @Autowired
    private IMallService mallService;

    /**
     * 检查当前是否可以下单，以及下单价格。
     *
     * @param card_worth    充值卡面值
     * @param phone_number      手机号码
     * @return
     */
    @RequestMapping("/v1.0/checkPhone")
    @ResponseBody
    public CommonJsonResult checkPhone(String appKey, String accountId, String card_worth,String phone_number)throws IOException{
        CommonJsonResult jsonResult = new CommonJsonResult();
        HttpRequester requester = new HttpRequester();
        Map<String,String> param = new HashMap<String,String>();
        param.put("card_worth",card_worth);
        param.put("phone_number",phone_number);
        param.put("api_key",ChongzhiAPI.API_KEY);
        String sign = ParameterUtil.getHuaFeiDuoSignData(param);
        sign = MD5Signature.sign(sign,ChongzhiAPI.SECRET_KEY);
        param.put("sign" ,sign);
        param.put("mod" ,ChongzhiAPI.ORDER_PHONE_CHECK);
        //调用话费多api
        HttpRespons respons = requester.sendGet(ChongzhiAPI.ORDER_PHONE_CHECK_API, param);
        Map<String,Object> map = (Map)JsonMapper.fromJson(respons.getContent(),Map.class);
        if(map != null && map.get("status") != null &&  map.get("status").equals("success")){
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(map.get("data"));
            /**
             *   price = 49.5,
                 card_worth = 50,
                 phone_number = 15623720747,
                 area = 湖北武汉,
                 platform = 联通
             */
        }else{
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
        }

        return jsonResult;
    }


    /**
     * 创建冲值订单
     * @param accountId
     * @param card_worth
     * @param phone_number
     * @return
     */
    @RequestMapping("v1.0/createChongzhiOrder")
    @ResponseBody
    public CommonJsonResult createChongzhiOrder(@RequestParam String appKey, @RequestParam String accountId, @RequestParam String card_worth,
                                                @RequestParam String phone_number,@RequestParam String sign,String signType)throws  IOException{
        CommonJsonResult jsonResult = new CommonJsonResult();

        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey",appKey);
        params.put("accountId", accountId);
        params.put("card_worth", card_worth);
        params.put("phone_number", phone_number);
        params.put("sign_type", signType);
        params.put("sign", sign);

        if (!MD5Signature.verifyMall(params)){
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SIGN_FAIL);
            jsonResult.setRESULT("签名不匹配!");
            return  jsonResult;
        }


         //调用getUserFinanceInfo API 得到用户的资金信息
        String app_Key = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        String[] keys = { "appKey", "secret", "accountID", "moneyType"};
        Object[] values = { app_Key, secret, accountId, "1"};
        String signFinance = Sha1.calculationSign(keys, values);
        PropertiesUtil props = PropertiesUtil.getInstance();
        String getUserFinanceInfo = props.getProperty("getUserFinanceInfo");
        HttpRequester requesterFinance = new HttpRequester();
        try {
            Map<String,String> param = new HashMap<String,String>();
            param.put("appKey",app_Key);
            param.put("sign",signFinance);
            param.put("accountID",accountId);
            param.put("moneyType","1");
            HttpRespons respons =  requesterFinance.sendPost(getUserFinanceInfo,param);
            if(respons.getCode() == 200) {
                CommonJsonResult result = (CommonJsonResult) JsonMapper.fromJson(respons.getContent(), CommonJsonResult.class);
                List<Map<String,String>> list = (List<Map<String,String>>)result.getRESULT();
                Map<String,String> rResult =list.get(0);
                double moneyAmount = Double.valueOf(rResult.get("moneyAmount"));
                double cardWorth = Double.valueOf(card_worth);
                if(cardWorth > moneyAmount ){
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                    jsonResult.setRESULT("密点数不足");
                    return jsonResult;
                }
            }else{
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
                return jsonResult;
            }
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
            return jsonResult;
        }
        //--------------------------end----------------------------

        //double cardWorth, double price, short status, String phoneNumber, String remark, short isValid
        HuafeiduoOrder huafeiduoOrder = new HuafeiduoOrder(Double.valueOf(card_worth),0,(short)0,phone_number,"冲值话费",(short)1,accountId);

        String orderId = mallService.insertHuaFeiDuoOrder(huafeiduoOrder);

        HttpRequester requester = new HttpRequester();
        Map<String,String> param = new HashMap<String,String>();
        param.put("card_worth",card_worth);
        param.put("phone_number",phone_number);

        //PropertiesUtil props =  PropertiesUtil.getInstance();

        param.put("notify_url",props.getProperty("huafeiduoNotify"));

        param.put("sp_order_id",orderId);
        param.put("remark","冲值话费");
        param.put("api_key",ChongzhiAPI.API_KEY);
        String signHafeiduo = ParameterUtil.getHuaFeiDuoSignData(param);
        signHafeiduo = MD5Signature.sign(signHafeiduo,ChongzhiAPI.SECRET_KEY);
        param.put("sign" ,signHafeiduo);
        param.put("mod" ,ChongzhiAPI.ORDER_PHONE_SUBMIT);
        //调用话费多api
        HttpRespons respons = requester.sendGet(ChongzhiAPI.ORDER_PHONE_SUBMIT_API, param);
        Map<String,Object> map = (Map)JsonMapper.fromJson(respons.getContent(),Map.class);
        if(map != null && map.get("status") != null &&  map.get("status").equals("success")){
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            this.mallService.updateHuafeiduoOrderStatus(orderId,(short)2,0);
            //map.get("order_id") 话费多订单ID
            //获取订单
            jsonResult.setRESULT(orderId);
        }else{
            log.error("生成话费多订单失败：" + map.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        return jsonResult;
    }

    /**
     * 话费多回调
     * @param order_id    话费多订单号
     * @param status      订单处理结果
     * @param worth        面值
     * @param price        成本价
     * @param phone        手机号码
     * @param sp_order_id  商户订单号
     * @param sign         签名
     */
    @RequestMapping("/v1.0/huafeihuoNotify")
    @ResponseBody
    public void huafeiduoNotify(HttpServletRequest request,HttpServletResponse response, String order_id,String status,String worth,String price,String phone,String sp_order_id,String sign) throws IOException {
        Map<String,String> param = new HashMap<String,String>();
        param.put("order_id",order_id);
        param.put("status",status);
        param.put("worth",worth);
        param.put("price",price);
        param.put("phone",phone);
        param.put("sp_order_id",sp_order_id);
        String signWeme = ParameterUtil.getHuaFeiDuoSignData(param);
        signWeme = MD5Signature.sign(signWeme,ChongzhiAPI.SECRET_KEY);
        PrintWriter out = response.getWriter();
        try {
            //记录回调信息       String orderId, String huafeiduoOrderId, double cardWorth, double price, String status, String phoneNumber, String remark
            HuafeiduoOrderNotify huafeiduoOrderNotify = new HuafeiduoOrderNotify(sp_order_id, order_id, Double.valueOf(worth), Double.valueOf(price), status, phone, "记录回调数据");
            this.mallService.insertHuaFeiDuoOrderNotify(huafeiduoOrderNotify);
            if (signWeme.equals(sign)) {
                if (status.equals("success")) {
                    this.mallService.updateHuafeiduoOrderStatus(sp_order_id, (short)1, Double.valueOf(price));

                    PropertiesUtil props = PropertiesUtil.getInstance();

                    HuafeiduoOrder huafeiduoOrder =  this.mallService.getHuafeiduoOrder(sp_order_id);

                    String[] keysCrash = { "appKey", "secret", "accountID", "changedAmount"};
                    Object[] valuesCrash = { ConstantsUtil.appKey, ConstantsUtil.secret, huafeiduoOrder.getAccountId(), worth,"话费冲值"};
                    String signCrasg = Sha1.calculationSign(keysCrash, valuesCrash);
                    String crashRecharge = props.getProperty("crashRecharge");

                    HttpRequester requesterCrash = new HttpRequester();
                    Map<String,String> paramCrash = new HashMap<String, String>();
                    paramCrash.put("appKey",ConstantsUtil.appKey);
                    paramCrash.put("sign",signCrasg);
                    paramCrash.put("accountID", huafeiduoOrder.getAccountId());
                    paramCrash.put("changedAmount",worth);
                    paramCrash.put("remark","话费冲值");

                    HttpRespons responsCrash =  requesterCrash.sendGet(crashRecharge,paramCrash);
                    if(responsCrash.getCode() == 200){
                        CommonJsonResult mapCrash = (CommonJsonResult)JsonMapper.fromJson(responsCrash.getContent(),CommonJsonResult.class);
                        if(mapCrash.getERRORCODE().equals("0")){
                            out.print("success");
                        } else{
                            out.print("failure");
                        }
                    }

                } else {
                    this.mallService.updateHuafeiduoOrderStatus(sp_order_id, (short) 3, Double.valueOf(price));
                    out.print("success");
                }

            } else {
                this.mallService.updateHuafeiduoOrderStatus(sp_order_id, (short) 3, Double.valueOf(price));
                out.print("failure");
            }
        }catch(Exception e){
            log.error("接收话费多回调信息处理失败:",e.getMessage());
            e.printStackTrace();
            out.print("failure");
        }
        out.close();


    }



}
