package com.daoke.mobileserver.mall.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.mall.WeMeMallCommonConfig;
import com.daoke.mobileserver.mall.entity.DaokeWallet;
import com.daoke.mobileserver.mall.entity.MallBanner;
import com.daoke.mobileserver.mall.service.IMallService;
import com.daoke.mobileserver.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2014/12/23.
 * weme商城支付接口
 */

@Controller
@RequestMapping("/mall")
public class WeMeMallController {


    private static final Logger log = LoggerFactory.getLogger(WeMeMallController.class);

    @Autowired
    private IMallService mallService;

    @Value("#{apiConfig[userFinanceConsume]}")
    private String userFinanceConsume;


    @Value("#{apiConfig[getAllRankInfo]}")
    private String getAllRankInfo;

    @Value("#{apiConfig[getInsuranceInfo]}")
    private String getInsuranceInfo;

    @Value("#{apiConfig[getRewardRank]}")
    private String getRewardRank;

    /**
     *商城支付接口
     * @param appKey  应用标识
     * @param accountID  账户编号
     * @param MEPoints   密点数
     * @param WEPoints   微点数
     * @param tradeNumber 交易流水号
     *  changedType 变化类型  10表示发放红包，、11表示接收红包、12表示道客商城交易
     *  remark   备注
     * @param businessID 企业编号
     * @param endTime   账户截至时间  changedType=11时有效，默认值为0
     * @param  signType  签名方式
     * @param  sign    签名串
     * @return
     */
    @RequestMapping("/v1.0/mallPay")
    public @ResponseBody CommonJsonResult payForMall(@RequestParam(required = true) String appKey,
                                                     @RequestParam(required = true) String accountID,
                                                     @RequestParam(required = true, defaultValue = "0") String MEPoints,
                                                     @RequestParam(required = true, defaultValue = "0") String WEPoints,
                                                     @RequestParam(required = true) String tradeNumber,
                                                     @RequestParam(required = true) String businessID,
                                                     @RequestParam(required = false) String endTime,
                                                     @RequestParam(required = true) String signType,
                                                     @RequestParam(required = true) String sign) {

        CommonJsonResult jsonResult = new CommonJsonResult();

        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey",appKey);
        params.put("accountID", accountID);
        params.put("MEPoints", MEPoints);
        params.put("WEPoints", WEPoints);
        params.put("tradeNumber", tradeNumber);
        params.put("businessID", businessID);
        params.put("endTime", endTime);
        params.put("sign_type", signType);
        params.put("sign", sign);


        if (!MD5Signature.verifyMall(params)){
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SIGN_FAIL);
            jsonResult.setRESULT("签名不匹配!");

            return  jsonResult;
        }

        //请求支付网关
        String _appKey = ConstantsUtil.getAppKey(appKey);
        String _secret = ConstantsUtil.getSecret(appKey);

        String[] keys = { "appKey", "secret", "accountID", "MEPoints", "WEPoints", "tradeNumber", "changedType", "remark", "businessID", "endTime"};
        Object[] values = { _appKey, _secret, accountID, MEPoints, WEPoints, tradeNumber, 12, "商城消费", businessID, endTime};

        try {
            String result = Sha1.httpPost(keys, values, userFinanceConsume);

            jsonResult = (CommonJsonResult)JsonMapper.fromJson(result, CommonJsonResult.class);

            //执行成功
            if (StringUtils.equals(ConstantsUtil.ERRORCODE_OK, jsonResult.getERRORCODE())){
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(jsonResult.getRESULT());
            } else {
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("支付失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("操作异常");
        }

        return jsonResult;
    }


    /**
     * 获取商城banner图
     * @param cityCode  城市号
     * @return
     */
    @RequestMapping("/v1.0/queryMallBanners")
    public @ResponseBody CommonJsonResult getBannerList(@RequestParam(required = false, defaultValue = "0") String cityCode){

        CommonJsonResult jsonResult = new CommonJsonResult();

        if (StringUtils.isEmpty(cityCode) || Integer.parseInt(cityCode) <= 0) {

            cityCode = "0";
        }

        List<MallBanner> banners = null;

        try {
            banners = mallService.queryMallBannerByCityCode(Integer.parseInt(cityCode));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        jsonResult.setRESULT(banners);

        return jsonResult;
    }


    @Value("#{apiConfig[donateDaoke]}")
    private String donateDaoke;

    /**
     * 捐赠接口
     * @param appKey  应用标识
     * @param accountID  发起捐赠者用户编号
     * @param userName   发起捐赠者用户名称
     * @param isAnonymous  是否匿名   0代表不匿名,1代表匿名
     * @param amount    捐赠数量  单位为元或米
     * @param donatedType  捐赠类型   1为密点,2为里程
     * @param regularDonation  0为否,1为每月自动捐赠,2每季度自动捐赠,3每年自动捐赠
     * @return
     */
    @RequestMapping("/v1.0/mallDonate")
    public @ResponseBody CommonJsonResult mallDonate(@RequestParam(required = true) String appKey,
                                                     @RequestParam(required = true) String accountID,
                                                     @RequestParam(required = true) String userName,
                                                     @RequestParam(required = true) String daokePassword,
                                                     @RequestParam(required = true) String isAnonymous,
                                                     @RequestParam(required = true) String amount,
                                                     @RequestParam(required = true) String donatedType,
                                                     @RequestParam(required = true) String regularDonation,
                                                     @RequestParam(required = true) String signType,
                                                     @RequestParam(required = true) String sign){
        CommonJsonResult jsonResult = new CommonJsonResult();

        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey", appKey);
        params.put("accountID", accountID);
        params.put("userName", userName);
        params.put("isAnonymous", isAnonymous);
        params.put("amount", amount);
        params.put("donatedType", donatedType);
        params.put("regularDonation",regularDonation);
        params.put("daokePassword", daokePassword);
        params.put("sign_type", signType);
        params.put("sign", sign);

        if (!MD5Signature.verifyMall(params)){

            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SIGN_FAIL);
            jsonResult.setRESULT("签名不匹配!");

            return  jsonResult;
        }

        //请求支付网关
        String _appKey = ConstantsUtil.getAppKey(appKey);
        String _secret = ConstantsUtil.getSecret(appKey);

        String[] keys = { "appKey", "secret", "donatorAccountID", "donatorName", "isAnonymous", "accepterAccountID"
                , "amount", "donatedType", "regularDonation","daokePassword"};

        Object[] values = { _appKey, _secret, accountID, userName, isAnonymous, WeMeMallCommonConfig.DAOKE_ACCOUNTID, amount, donatedType, regularDonation,daokePassword};

        try {
            String result = Sha1.httpPost(keys, values, donateDaoke);

            jsonResult = (CommonJsonResult)JsonMapper.fromJson(result, CommonJsonResult.class);

            //执行成功

            if (StringUtils.equals(ConstantsUtil.ERRORCODE_OK, jsonResult.getERRORCODE())){
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(jsonResult.getRESULT());
            } else {
                jsonResult.setERRORCODE(jsonResult.getERRORCODE());
                jsonResult.setRESULT(jsonResult.getRESULT());
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("操作异常");
        }

        return jsonResult;
    }

    /**
     * 查询所有道客的排名信息
     * @param appKey   应用标识
     * @param type     排名类型  （１代表日排名，２代表周排名，３代表月>排名，４代表总排名，若type为４则time可以不传）
     * @param startRank  起始排名
     * @param endRank    结束排名
     * @param startPage  当前页
     * @param pageCount  每页条数
     * @return
     */
    @RequestMapping("/v1.0/getAllRankInfo")
    @ResponseBody
    public CommonJsonResult getAllRankInfo(@RequestParam String appKey,@RequestParam String type,@RequestParam(required = false) String time,
                                           @RequestParam String startRank, @RequestParam String endRank,
                                           @RequestParam String startPage,@RequestParam String pageCount){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String _appKey = ConstantsUtil.getAppKey(appKey);
        String _secret = ConstantsUtil.getSecret(appKey);

        String[] keys = { "appKey", "secret", "type","time","startRank", "endRank", "startPage", "pageCount"};

        Object[] values = { _appKey, _secret, type,time, startRank, endRank,startPage,pageCount};
       try {
           String result = Sha1.httpPost(keys, values, getAllRankInfo);
           jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
       }catch (Exception e){
           log.error("查询所有道客的排名信息" + e.getMessage());
           e.printStackTrace();
           jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
           jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
       }

        return jsonResult;
    }

    /**
     * 获取用户保险金清单
     * @param appKey 应用标识
     * @param accountID    用户帐户信息
     * @return
     */
    @RequestMapping("/v1.0/getInsuranceInfo")
    @ResponseBody
    public CommonJsonResult getInsuranceInfo(@RequestParam String appKey,@RequestParam String accountID){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String _appKey = ConstantsUtil.getAppKey(appKey);
        String _secret = ConstantsUtil.getSecret(appKey);
        String[] keys = { "appKey", "secret", "accountID"};
        Object[] values = { _appKey, _secret, accountID};
        try {
            String result = Sha1.httpPost(keys, values, getInsuranceInfo);
            jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
        }catch (Exception e){
            log.error("获取用户保险金清单发生异常" + e.getMessage());
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        return  jsonResult;
    }

    /**
     * 查询道客的捐赠排名
     * @param appKey
     * @param accountID
     * @param time 排名时间  格式:20141231
     * @param type   排名类型  （１代表日排名，２代表周排名，３代表月>排名，４代表总排名，若type为４则time可以不传）
     * @param  moneyType  1 密点 2 金钱
     * @return
     */
    @RequestMapping("/v1.0/getRewardRank")
    @ResponseBody
    public CommonJsonResult  getRewardRank(String appKey,String accountID,String time,String type,@RequestParam(required =  false) Short moneyType ){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String _appKey = ConstantsUtil.getAppKey(appKey);
        String _secret = ConstantsUtil.getSecret(appKey);
        String[] keys = { "appKey", "secret", "accountID","time","type"};
        Object[] values = { _appKey, _secret, accountID,time,type};
        try {
            String result = Sha1.httpPost(keys, values, getRewardRank);
            jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
        }catch (Exception e){
            log.error("查询道客的捐赠排名发生异常" + e.getMessage());
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        return jsonResult;
    }

    /**
     * 查询道客钱包页面元素
     */
    @RequestMapping("/queryDaokeWallet")
    @ResponseBody
    public CommonJsonResult  queryDaokeWallet( ){
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<DaokeWallet> list =mallService.queryDaokeWallet();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        }catch (Exception e){
            log.error(" 查询道客钱包页面元素失败" , e);
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        return jsonResult;
    }



}
