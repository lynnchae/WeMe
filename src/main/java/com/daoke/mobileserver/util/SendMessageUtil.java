package com.daoke.mobileserver.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.user.dao.IMessageCentreDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: chenlong
 * Date: 2015/5/12
 * Time: 9:44
 */
public class SendMessageUtil {


    private static JPushClient jpush = new JPushClient(ConstantsUtil.JPush.MASTER_SECRET, ConstantsUtil.JPush.APP_KEY);
    private static Logger logger = Logger.getLogger(SendMessageUtil.class);
    @Autowired
    private static IMessageCentreDao pushMessageDao;
    /**
     *  推送消息到手机端
     * @param accountID
     * @param alert
     * @param title
     * @throws cn.jpush.api.common.APIConnectionException
     * @throws cn.jpush.api.common.APIRequestException
     */
    public static void sendToMobile(String accountID,String alert,String title) throws APIConnectionException, APIRequestException {
        PushPayload payload =     buildPushObject_android_and_ios(accountID,alert,title);
        PushResult pushResult =jpush.sendPush(payload);
    }



    public static PushPayload buildPushObject_android_and_ios(String accountID,String alert,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(accountID))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1).build())
                        .build())
                .build();
    }




    public  static String generateVoiceUrl(String terminalText) throws IOException {
       // StringBuilder buf = new StringBuilder();
      //  buf.append("屌炸天，恭喜你，你已完成今日单次行驶十公里的任务，系统奖励你十谢尔，按加键马上领取，也可以离车后当日内在手机上领取奖励。");
        //因使用tomcat服务出现签名错误 故修改请求方式
        String[] keys = { "appKey", "secret", "text" };
        Object[] values = { ConstantsUtil.appKey, ConstantsUtil.secret, terminalText};
        String sign1 = Sha1.calculationSign(keys, values);

        HttpClient client1 = new HttpClient();
        PostMethod post1 = new PostMethod("http://api.daoke.io/dfsapi/v3/txt2voice");
        post1.setRequestHeader(new Header("Content-Type",
                "application/x-www-form-urlencoded; charset=utf-8"));
        NameValuePair[] valuePairs = new NameValuePair[] { new NameValuePair("appKey",  ConstantsUtil.appKey),
                new NameValuePair("sign", sign1), new NameValuePair("text", terminalText), };
        post1.addParameters(valuePairs);

        String voiceUrl ="";

        for (int i =0; i<3;i++){
            //语音文件URL
            int statusCode1 = client1.executeMethod(post1);
            if (statusCode1 == 200) {
                String  result = post1.getResponseBodyAsString();
                CommonJsonResult jsonResult2 = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
                if (jsonResult2.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                    Map<String, String> resParam = (Map<String, String>) jsonResult2.getRESULT();
                    voiceUrl = resParam.get("url");
                    break;
                }
            }
        }

        return voiceUrl;
    }

    /**
     * 推送消息到硬件终端
     * @param sendPersonalWeibo
     * @param callbackUrl
     * @param rewardID
     * @param accountID
     * @return
     * @throws java.io.IOException
     */
    public static String sendToTerminal(String sendPersonalWeibo,String callbackUrl,Integer rewardID,String accountID,String terminalText) throws IOException {

        Map<String, String> paramWeibo = new HashMap<String, String>();
        paramWeibo.put("appKey", ConstantsUtil.appKey);
        paramWeibo.put("secret",  ConstantsUtil.secret);
        paramWeibo.put("multimediaURL",  generateVoiceUrl(terminalText));
        //临时测试使用
        paramWeibo.put("senderAccountID",accountID);
        paramWeibo.put("receiverAccountID",accountID);
        paramWeibo.put("level","18");
        paramWeibo.put("interval", "90");
        StringBuilder callbackBuf = new StringBuilder();
        callbackBuf.append(callbackUrl)
                .append("?rewardID=").append(rewardID).append("&accountID=").append(accountID);

        paramWeibo.put("callbackURL", callbackBuf.toString());
        String signWeibo = SHASignature.sign(ParameterUtil.getDaokeSignData(paramWeibo));
        paramWeibo.put("sign", signWeibo);

        logger.info("push 到终端参数:"+ paramWeibo);

        HttpRequester requesterWeibo = new HttpRequester();
        requesterWeibo.setContentType("multipart/form-data;boundary=--abc");
        HttpRespons responsWeibo = requesterWeibo.sendPost(sendPersonalWeibo, paramWeibo);
        String bizid = "";
        if (responsWeibo.getCode() == ConstantsUtil.HttpStatusCode.OK) {
            String content = responsWeibo.getContent();
            CommonJsonResult jsonResult2 = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            if (jsonResult2.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                Map<String, String> resParam = (Map<String, String>) jsonResult2.getRESULT();
                bizid = resParam.get("bizid");
            }else{
                logger.info(jsonResult2.toString());

            }
        }


        return  bizid;

    }
    /**
     * 推送消息到硬件终端
     * @param sendPersonalWeibo
     * @param callbackUrl
     * @param params
     * @param accountID
     * @return
     * @throws java.io.IOException
     */
    public static String sendToTerminalV2(String sendPersonalWeibo,String callbackUrl,Map<String,Object> params,String accountID,String terminalText) throws IOException {

        Map<String, String> paramWeibo = new HashMap<String, String>();
        paramWeibo.put("appKey", ConstantsUtil.appKey);
        paramWeibo.put("secret",  ConstantsUtil.secret);
        paramWeibo.put("multimediaURL",  generateVoiceUrl(terminalText));
        //临时测试使用
        paramWeibo.put("senderAccountID","3XhS4kgGVl");
        paramWeibo.put("receiverAccountID","3XhS4kgGVl");
        paramWeibo.put("level","18");
        paramWeibo.put("interval", "90");
        StringBuffer callbackBuf = new StringBuffer(callbackUrl);
        if(params!=null&&params.size()!=0){
            Set<Map.Entry<String,Object>> paramsEntry = params.entrySet();
            for(Map.Entry<String,Object> paramsMap : paramsEntry){
                String key = paramsMap.getKey();
                String value = paramsMap.getValue().toString();
                if(callbackBuf==null|| StringUtils.isBlank(callbackBuf.toString())){
                    callbackBuf.append("?").append(key).append("=").append(value);
                }else{
                    callbackBuf.append("&").append(key).append("=").append(value);
                }
            }
        }

        paramWeibo.put("callbackURL", callbackUrl);
        String signWeibo = SHASignature.sign(ParameterUtil.getDaokeSignData(paramWeibo));
        paramWeibo.put("sign", signWeibo);

        HttpRequester requesterWeibo = new HttpRequester();
        requesterWeibo.setContentType("multipart/form-data;boundary=--abc");
        HttpRespons responsWeibo = requesterWeibo.sendPost(sendPersonalWeibo, paramWeibo);
        String bizid = "";
        if (responsWeibo.getCode() == ConstantsUtil.HttpStatusCode.OK) {
            String content = responsWeibo.getContent();
            CommonJsonResult jsonResult2 = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            if (jsonResult2.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                Map<String, String> resParam = (Map<String, String>) jsonResult2.getRESULT();
                bizid = resParam.get("bizid");
            }else{
                System.out.print(jsonResult2.toString());

            }
        }


        return  bizid;

    }
    /**
     * 推送消息
     * @param msgTitle
     * @param accountID
     * @param alert
     * @param messageType
     * @return
     * @throws Exception
     */
    public static PushResult pushMessage(String msgTitle, String accountID,String alert,String messageType,Map<String,Object> params){
        PushResult result = null;
        try{
            if(params==null||params.size()==0){
                result = jpush.sendPush(PushPayload.newBuilder()
                        .setPlatform(Platform.android_ios())
                        .setAudience(Audience.alias(new String[]{accountID}))
                        .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(msgTitle).addExtra("messageType", messageType).build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1).setAlert(alert).addExtra("messageType", messageType).build())
                                .build())
                        .build());
            }else {
                result = jpush.sendPush(PushPayload.newBuilder()
                        .setPlatform(Platform.android_ios())
                        .setAudience(Audience.alias(new String[]{accountID}))
                        .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(msgTitle).addExtra("messageType", messageType).addExtra("params", params.toString()).build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1).setAlert(alert).addExtra("messageType", messageType).addExtra("params", params.toString()).build())
                                .build())
                        .build());
            }
            }catch (Exception e){
                if(result==null){
                    result = new  PushResult();
                }
            }
        return result;
    }
    /**
     * 推送消息
     * @param msgTitle
     * @param accountID
     * @param msgContent
     * @return
     * @throws Exception
     */
      public static void pushMessage(String msgTitle, String msgContent, String accountID) {
          try{
              PushResult pr = jpush.sendAndroidMessageWithAlias(msgTitle, msgContent, accountID);
            }catch (APIConnectionException e){
              logger.error("Android连接异常:", e);
          }catch (APIRequestException e){
              logger.error("Android jpush服务器错误响应", e);
              logger.error("Android status:" + e.getStatus());
              logger.error("Android errorCode:" + e.getErrorCode());
              logger.error("Android errorMessage:" + e.getErrorMessage());
              logger.error("Android msgId:" + e.getMsgId());
              try {
                  PushResult pr =  jpush.sendIosMessageWithAlias(msgTitle, msgContent, accountID);
              } catch (APIConnectionException e1) {
                  logger.error("Ios连接异常:", e);
              } catch (APIRequestException e1) {
                  logger.error("Ios jpush服务器错误响应", e);
                  logger.error("status:" + e.getStatus());
                  logger.error("errorCode:" + e.getErrorCode());
                  logger.error("errorMessage:" + e.getErrorMessage());
                  logger.error("msgId:" + e.getMsgId());
              }
          }
      }
}
