package com.daoke.mobileserver.test;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.user.service.impl.UserRochelleRuleServiceImpl;
import com.daoke.mobileserver.util.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chenmaomao on 5/13 0013.
 */
public class TestPushMsg extends BaseTest {
    @Autowired
    private UserService userService;
    @Test
    public void test() throws Exception {
        JPushClient jPushClient = new JPushClient(RandomSendNo.MASTER_SECRET, RandomSendNo.APP_KEY,
                864000);
        PushResult result = null;
        //867051024430949
        if (StringUtils.isNotEmpty("kxl1QuHKCD")) {
            try{
                result = jPushClient.sendPush(PushPayload.newBuilder()
                        .setPlatform(Platform.android_ios())
                        .setAudience(Audience.alias("kynBvul0nQ"))
                        .setNotification(Notification.newBuilder()
                                .setAlert("PushPayload Notification alert")
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle("AndroidNotification title").addExtra("info", "msgContent").addExtra("pushType", "pushType").build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1).setAlert("title").addExtra("info", "msgContent").addExtra("pushType", "pushType").build())
                                .build())
                        .build());
            }catch (Exception e){
                if(result==null){
                    result = new  PushResult();
                }

            }
        }else {
            //jpush.sendNotificationAll(msgTitle);
        }
        System.out.println(result);
        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_android_tag_alertWithTitle();
//
//        try {
//            PushResult result = jPushClient.sendPush(payload);
//           System.out.println("Got result - " + result);
//
//        } catch (APIConnectionException e) {
//            // Connection error, should retry later
//            System.out.println("Connection error, should retry later"+e);
//
//        } catch (APIRequestException e) {
//            // Should review the error, and fix the request
//            System.out.println("Should review the error, and fix the request"+ e);
//            System.out.println("HTTP Status: " + e.getStatus());
//            System.out.println("Error Code: " + e.getErrorCode());
//            System.out.println("Error Message: " + e.getErrorMessage());
//        }
    }
    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("aa");
    }
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias("mTlDlmLsya"))
                .setNotification(Notification.android("haha", "nihaoa", null))
                .build();
    }
    private static JPushClient jpush = new JPushClient(ConstantsUtil.JPush.MASTER_SECRET, ConstantsUtil.JPush.APP_KEY);
    @Test
    public  void pushMessage() {
        Integer gender =1;
        String msgContent ="我是聊天内容";
        String friendNickName ="好友昵称";
        String accountNickName ="发送者的昵称";
        String senderUserHeadName ="http://www.baidu.com/1.jpg";
        String accountID ="cllk0KkCCA";
        Map<String, Object> params = new HashMap<String, Object>(7);
        params.put("talkContent", msgContent);
        //推送消息
        String ta = "";
        if(1==gender){
            ta="他";
        }else if(0==gender){
            ta="她";
        }else{
            ta="TA";
        }
        Long createTime =System.currentTimeMillis()/1000;
        params.put("friendNickName",friendNickName);
        params.put("accountNickName",accountNickName);
        params.put("senderUserHeadName",senderUserHeadName);
        params.put("pushTime",createTime);
        params.put("accountID",accountID);
        params.put("ta",ta);
//        try {
//            SendMessageUtil.pushMessage("talk", params.toString(), "cllk0KkCCA");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String msgTitle="微密";
//        String msgContent="啊啊啊啊啊啊啊啊啊";
//        String accountID="kxl1QuHKCD";
//        PushResult result = null;
//        Map map = new HashMap();
//        map.put("msgContent",msgContent);
////            PushPayload payloadAndroid = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.alias(accountID)).
////                    setMessage(Message.newBuilder().setTitle(msgTitle).setMsgContent(msgContent).build()).build();
////            result = jpush.sendPush(payloadAndroid);
////            PushPayload payloadIOS = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.alias(accountID)).
////                    setMessage(Message.newBuilder().setTitle(msgTitle).setMsgContent(msgContent).build()).build();
////            result = jpush.sendPush(payloadIOS);
//            String paramsJson =  JsonMapper.toJson(params,false);
//            jpush.sendIosMessageWithAlias("talk", paramsJson, accountID);
//            jpush.sendAndroidMessageWithAlias("talk", paramsJson, accountID);
//            System.out.println("IOS:"+result);
    }
    @Test
    public void  testJson() throws IOException {
        String path ="";
//        String path = System.getProperty("user.dir")+"/webroot/cityJson/city.json";
        String cityJson = readJson(path);
//        System.out.println(cityJson);
        JsonMapper json = (JsonMapper) JsonMapper.fromJson(cityJson,Map.class);
//        ObjectMapper om = new ObjectMapper();
//        JsonParser jsonParser = om.getJsonFactory().createJsonParser(new File(path));
//        String text = jsonParser.getCurrentName();
////        for()
//        System.out.println(text);
    }
        //从给定位置读取Json文件
        public static String readJson(String path){
            //从给定位置获取文件
            File file = new File(path);
            BufferedReader reader = null;
            //返回值,使用StringBuffer
            StringBuffer data = new StringBuffer();
            //
            try {
                reader = new BufferedReader(new FileReader(file));
                //每次读取文件的缓存
                String temp = null;
                while((temp = reader.readLine()) != null){
                    data.append(temp);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //关闭文件流
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }
    @Test
    public void test1() throws Exception {
        Map<String,Object> param = new HashMap<String,Object>();
        //推送到手机

        param.put("rewardID", 27849802);
        param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get("powerSevenDay"));
        userService.pushMessage("任务奖励", "kxl1QuHKCD", "你已完成本月行驶三千公里的任务，快去领取谢尔奖励吧，么么哒！", "rochelleReward", param);

    }
}
