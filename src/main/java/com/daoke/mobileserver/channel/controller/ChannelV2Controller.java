package com.daoke.mobileserver.channel.controller;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerChannelMessage;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.channel.service.IServerChannelMessageService;
import com.daoke.mobileserver.channel.service.IServerChannelService;
import com.daoke.mobileserver.channel.service.IServerMenuService;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.map.service.impl.MapServiceImpl;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserFriend;
import com.daoke.mobileserver.user.service.IUserFriendService;
import com.daoke.mobileserver.user.service.IUserGradeService;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 2.0频道controller
 * Created by chenmaomao on 2015/3/30.
 */
@Controller
public class ChannelV2Controller {

    private final Logger logger = Logger.getLogger(ChannelV2Controller.class);

    @Value("#{apiConfig[fetchSecretChannel]}")
    private String fetchSecretChannel;

    @Value("#{apiConfig[getCatalogInfo]}")
    private String getCatalogInfo;

    @Value("#{apiConfig[getSecretChannelInfo]}")
    private String getSecretChannelInfo;

    @Value("#{apiConfig[getMicroChannelInfo]}")
    private String getMicroChannelInfo;

    @Value("#{apiConfig[joinSecretChannel]}")
    private String joinSecretChannel;

    @Value("#{apiConfig[followMicroChannel]}")
    private String followMicroChannel;

    @Value("#{apiConfig[applySecretChannel]}")
    private String applySecretChannel;

    @Value("#{apiConfig[quitSecretChannel]}")
    private String quitSecretChannel;

    @Value("#{apiConfig[veritySecretChannelMessage]}")
    private String veritySecretChannelMessage;

    @Value("#{apiConfig[secretChannelMessage]}")
    private String secretChannelMessage;

    @Value("#{apiConfig[getUserJoinListSecretChannel]}")
    private String getUserJoinListSecretChannel;

    @Value("#{apiConfig[applyMicroChannel ]}")
    private String applyMicroChannel ;

    @Value("#{apiConfig[modifySecretChannelInfo ]}")
    private String modifySecretChannelInfo ;

    @Value("#{apiConfig[fetchMicroChannel ]}")
    private String fetchMicroChannel ;

    @Value("#{apiConfig[getUserFollowListMicroChannel ]}")
    private String getUserFollowListMicroChannel ;

    @Value("#{apiConfig[ dissolveSecretChannel ]}")
    private String  dissolveSecretChannel ;

    @Value("#{apiConfig[ transferSecretChannel ]}")
    private String  transferSecretChannel ;

    @Value("#{apiConfig[getBossFollowListMicroChannel ]}")
    private String getBossFollowListMicroChannel ;

    @Value("#{apiConfig[sendPersonalWeibo]}")
    private String sendPersonalWeibo;

    @Value("#{apiConfig[manageSecretChannelUsers]}")
    private String manageSecretChannelUsers;

    @Value("#{apiConfig[callBackJoinSecretChannel]}")
    private String callBackJoinSecretChannel;

    @Value("#{apiConfig[getCustomDefineInfo]}")
    private String getCustomDefineInfo;

    @Value("#{apiConfig[getServiceContent]}")
    private String getServiceContent;

    @Value("#{apiConfig[saveFile]}")
    private String saveFile;

    @Autowired
    private AudioService audioService;
    @Autowired
    private UserService userService;
    @Autowired
    private IServerChannelService serverChannelService;
    @Autowired
    private IUserGradeService userGradeService;
    @Autowired
    private IServerMenuService serverMenuService;
    @Autowired
    private IUserFriendService userFriendService;
    @Autowired
    private IServerChannelMessageService serverChannelMessageService;

    /**
     *  根据频道名称,号码,管理员,所在地区查询群聊频道
     * @param appKey 应用标识
     * @param accountID 语镜用户帐户编号
     * @param infoType 函数接口
     * @param startPage 起始索引
     * @param pageCount 每页显示条数
     * @param cityCode 频道区域编码
     * @param channelNumber 频道编号
     * @param catalogID 频道类别编码
     * @param channelName 频道名称
     * @return
     */
    @RequestMapping("/fetchSecretChannel")
    @ResponseBody
    public CommonJsonResult fetchSecretChannel(@RequestParam String appKey,@RequestParam String accountID,@RequestParam Integer infoType,
                                               Integer startPage,Integer pageCount , Integer cityCode ,String channelNumber ,Integer catalogID,
                                               String channelName,String channelKeyWords) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try{
            Map<String, String> param = new HashMap<String, String>(11);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID",  accountID==null?"":accountID);
            param.put("infoType", infoType==null?"":infoType.toString());
            param.put("startPage", startPage==null?"":startPage.toString());
            param.put("pageCount",pageCount==null?"":pageCount.toString());
            param.put("cityCode",cityCode==null?"":cityCode.toString());
            param.put("channelNumber", channelNumber==null?"":channelNumber);
            param.put("catalogID", catalogID==null?"":catalogID.toString());
            param.put("channelName", channelName==null?"":channelName);
            param.put("channelKeyWords", channelKeyWords==null?"":channelKeyWords);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(fetchSecretChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        }catch (Exception e){
            logger.error("查询群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("查询群聊频道失败");
        }
        return jsonResult;
    }

    /**
     * 获取群聊频道详情
     * @param appKey 应用标识
     * @param accountID 语镜用户帐户编号
     * @param channelNumber 频道编号
     * @return
     */
    @RequestMapping("/getSecretChannelInfo")
    @ResponseBody
    public  CommonJsonResult getSecretChannelInfo(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String channelNumber){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getSecretChannelInfo, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        }catch (Exception e){
            logger.error("获取群聊频道详情失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取群聊频道详情失败");
        }
        return jsonResult;
    }


    /**
     * 用户申请加入群聊频道
     * @param appKey 应用标识
     * @param accountID 申请账户
     * @param uniqueCode 频道邀请码
     * @return
     */
    @RequestMapping("/joinSecretChannel")
    @ResponseBody
    public  CommonJsonResult joinSecretChannel(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String uniqueCode){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("uniqueCode", uniqueCode);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(joinSecretChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        }catch (Exception e){
            logger.error("申请加入群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("申请加入群聊频道失败");
        }
        return jsonResult;
    }
    /**
     * 用户退出群聊频道
     * @param appKey 应用标识
     * @param accountID 管理员申请账户
     * @param channelNumber 频道编号
     * @return
     */
    @RequestMapping("/quitSecretChannel")
    @ResponseBody
    public  CommonJsonResult quitSecretChannel(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String channelNumber,
                                               @RequestParam String accountNickName,@RequestParam String channelName){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(quitSecretChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
            requester = new HttpRequester();
            respons = requester.sendPost(getSecretChannelInfo, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                Map<String,List<Map<String,Object>>> result = (Map<String,List<Map<String,Object>>>) JsonMapper.fromJson(content, Map.class);
                String adminAccontID = result.get("RESULT").get(0).get("accountID").toString();
                 userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, adminAccontID,accountNickName+" 退出了你创建的群聊频道 "+channelName+"。",ConstantsUtil.Push.QUIT_SECRET_CHANNEL_TYPE,null,accountID);
            }
        }catch (Exception e){
            logger.error("用户退出群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("用户退出群聊频道失败");
        }
        return jsonResult;
    }


    /**
     * 用户申请群聊频道
     * @param appKey 应用标识
     * @param accountID 语镜用户帐户编号
     * @param channelName 频道名称
     * @param channelIntroduction 频道简介
     * @param channelCityCode 频道区域编号
     * @param channelCatalogID 频道类别编号
     * @param openType 频道开放
     * @param isVerity 是否校验
     * @param request
     * @return
     */
    @RequestMapping("/applySecretChannel")
    @ResponseBody
    public CommonJsonResult applySecretChannel(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String channelName,@RequestParam String channelIntroduction,
                                              @RequestParam Integer channelCityCode,@RequestParam Integer channelCatalogID,@RequestParam Integer openType,
                                               Integer isVerity,String channelKeyWords ,HttpServletRequest request) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            MultipartFile file = null;
            try {
                //上传图片并返回url
                CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
                if(multipartResolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                    Iterator<String> iter = multiRequest.getFileNames();
                    while (iter.hasNext()) {
                        file = multiRequest.getFile(iter.next());
                        break;
                    }
                }
            } catch (Exception e1) {
                logger.error("图片保存失败",e1);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("图片保存失败");
            }
            Map<String, String> param = new HashMap<String, String>(11);
            if(file!=null) {
                String result = audioService.saveFile(appkey, secret, file, saveFile);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String ERRORCODE = jsonObject.getString("ERRORCODE");
                if (("0").equals(ERRORCODE)) {
                    String channelLogoURL = jsonObject.getJSONObject("RESULT").getString("url");
                    param.put("appKey", appkey);
                    param.put("secret", secret);
                    param.put("accountID", accountID==null?"":accountID);
                    param.put("channelName", channelName==null?"":channelName);
                    param.put("channelIntroduction", channelIntroduction==null?"":channelIntroduction);
                    param.put("channelCityCode", channelCityCode==null?"":channelCityCode.toString());
                    param.put("channelCatalogID", channelCatalogID==null?"":channelCatalogID.toString());
                    param.put("channelCatalogUrl",channelLogoURL==null?"":channelLogoURL);
                    param.put("openType", openType==null?"":openType.toString());
                    param.put("isVerify", isVerity==null?"":isVerity.toString());
                    param.put("channelKeyWords", channelKeyWords==null?"":channelKeyWords);
                }
            }
                //签名
                String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
                param.put("sign", sign);
                HttpRequester requester = new HttpRequester();
                HttpRespons respons = requester.sendPost(applySecretChannel, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                    String content = respons.getContent();
                    jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
                }
        }catch (Exception e){
            logger.error("用户申请群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("用户申请群聊频道失败");
        }
        return jsonResult;
    }

    /**
     * 管理员接收/拒绝用户加入群聊频道
     * @param appKey 应用标识
     * @param accountID 管理员的帐户编号
     * @param applyAccountID 用户帐户编号
     * @param checkRemark 接收/拒绝理由
     * @param checkStatus 加入状态
     * @param applyIdx 频道id
     * @return
     */
    @RequestMapping("/veritySecretChannelMessage")
    @ResponseBody
    public  CommonJsonResult veritySecretChannelMessage(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String applyAccountID,
                                                         String checkRemark, @RequestParam String checkStatus,@RequestParam String applyIdx,
                                                         @RequestParam String accountNickName,@RequestParam String channelName,@RequestParam Integer messageID){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(7);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("applyAccountID", applyAccountID);
            param.put("checkRemark", checkRemark==null?"":checkRemark);
            param.put("checkStatus", checkStatus);
            param.put("applyIdx", applyIdx);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(veritySecretChannelMessage, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
            if("1".equals(checkStatus)){
                //推送消息
               userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, applyAccountID,accountNickName+" 同意你加入群聊频道 "+channelName+" 要不要去关联这个频道到WEME呢？",ConstantsUtil.Push.AGREE_SECRET_CHANNEL_MESSAGE_TYPE,null,accountID);
                userService.updateMessageIsAgree(messageID,'1');
            }else if("2".equals(checkStatus)){
                userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, applyAccountID,accountNickName+" 拒绝你加入群聊频道 "+channelName+"，再去看看其他频道吧！",ConstantsUtil.Push.DISAGREE_SECRET_CHANNEL_MESSAGE_TYPE,null,accountID);
                userService.updateMessageIsAgree(messageID,'0');
            }
        }catch (Exception e){
            logger.error("管理员接收/拒绝用户加入群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("管理员接收/拒绝用户加入群聊频道失败");
        }
        return jsonResult;
    }

    /**
     * 群聊频道消息提醒
     * @param appKey 应用标识
     * @param accountID 管理员的帐户编号
     * @param startPage 起始索引
     * @param pageCount 每页显示条数
     * @return
     */
    @RequestMapping("/secretChannelMessage")
    @ResponseBody
    public  CommonJsonResult secretChannelMessage(@RequestParam  String appKey,@RequestParam String accountID,Integer startPage,Integer pageCount){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(7);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("startPage", startPage==null?"":startPage.toString());
            param.put("pageCount", pageCount==null?"":pageCount.toString());
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(secretChannelMessage, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        }catch (Exception e){
            logger.error("群聊频道消息提醒失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("群聊频道消息提醒失败");
        }
        return jsonResult;
    }

    /**
     * 获取群聊频道的用户列表
     * @param appKey 应用标识
     * @param accountID 管理员的帐户编号
     * @param channelNumber 频道编号
     * @param startPage 起始索引
     * @param pageCount 每页显示条数
     * @param infoType 函数接口
     * @return
     */
    @RequestMapping("/getUserJoinListSecretChannel")
    @ResponseBody
    public  CommonJsonResult getUserJoinListSecretChannel(@RequestParam  String appKey,@RequestParam String accountID,@RequestParam String channelNumber ,
                                                         Integer startPage, Integer pageCount, Integer infoType){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(7);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            param.put("infoType", infoType==null?"":infoType.toString());
            param.put("startPage", startPage==null?"":startPage.toString());
            param.put("pageCount", pageCount==null?"":pageCount.toString());
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getUserJoinListSecretChannel, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                    String content = respons.getContent();
                    Map<String, Map<String, Object>> result = (Map) JsonMapper.fromJson(content, Map.class);
                    jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
                    Map<String,Object> map = new HashMap<String, Object>(2);
                    String count =(String)result.get("RESULT").get("count");
                    List<Map<String, Object>> list = (List) result.get("RESULT").get("list");
                    List<String> friendAccountIDList = new ArrayList<String>(list.size());
                    for (Map<String, Object> friendAccount : list) {
                        friendAccountIDList.add(friendAccount.get("accountID").toString());
                    }
                    if (friendAccountIDList.size() != 0) {
                        //查询用户等级中的头像,性别,和地区
                        List<UserDetailVo> userGradeList = userGradeService.queryUserFriendDetailList(friendAccountIDList);
                        //查询好友
                        List<UserFriend> userFriendList = userFriendService.getAllFriends(accountID);

                        for (Map<String, Object> friendAccount : list) {
                            //添加用户头像
                            for (UserDetailVo userDetailVo : userGradeList) {
                                if (friendAccount.get("accountID").equals(userDetailVo.getAccountID())) {
                                    friendAccount.put("userHeadName", userDetailVo.getUserHeadName() == null ? "" : userDetailVo.getUserHeadName());
                                    friendAccount.put("gender",userDetailVo.getGender()==null?"":userDetailVo.getGender());
                                    friendAccount.put("userArea",userDetailVo.getUserAreaCode() == null ? "" : MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode())==null?"":MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode()));
                                    friendAccount.put("isAllowedOpinion", userDetailVo.getIsAllowedOpinion());
                                    friendAccount.put("isVerifyOpinion", userDetailVo.getIsVerifyOpinion());
                                }
                            }
                            for (UserFriend userFriend : userFriendList) {
                                //添加用户是否为好友
                                if (userFriend.getIsAgree()==1 &&
                                        ((accountID.equals(userFriend.getAccountID()) && friendAccount.get("accountID").equals(userFriend.getFriendAccountID())) ||
                                        (accountID.equals(userFriend.getFriendAccountID()) && friendAccount.get("accountID").equals(userFriend.getAccountID())))) {
                                    friendAccount.put("isFriend", 1);
                                    }
                                }
                            if (friendAccount.get("isFriend") == null) {
                                friendAccount.put("isFriend", 0);
                            }
                        }
                        map.put("count",count);
                        map.put("list",list);
                        jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
                        jsonResult.setRESULT(map);
                    }
                }
        }catch (Exception e){
            logger.error("获取群聊频道的用户列表失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取群聊频道的用户列表失败");
        }
        return jsonResult;
    }
    /**
     * 获取我关注的主播频道列表
     * @param appKey 应用标识
     * @param accountID 管理员的帐户编号
     * @param startPage 起始索引
     * @param pageCount 每页显示条数
     * @return
     */
    @RequestMapping("/getUserFollowListMicroChannel")
    @ResponseBody
    public  CommonJsonResult getUserFollowListMicroChannel(@RequestParam  String appKey,@RequestParam String accountID,
                                                           @RequestParam  Integer startPage,@RequestParam  Integer pageCount){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(5);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("startPage", startPage==null?"":startPage.toString());
            param.put("pageCount", pageCount==null?"":pageCount.toString());
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getUserFollowListMicroChannel, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                    jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        }catch (Exception e){
            logger.error("获取我关注的主播频道列表失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取我关注的主播频道列表失败");
        }
        return jsonResult;
    }
    /**
     * 获取频道所有类别的列表V2
     * @param appKey
     * @param startPage
     * @param pageCount
     * @param channelType 1:主播频道类别 2:群聊频道类别
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCatalogInfoV2")
    public CommonJsonResult getCatalogInfoV2(@RequestParam String appKey,@RequestParam Integer startPage,@RequestParam Integer pageCount,
                                                Integer channelType){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(5);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("channelType", channelType==null?"":channelType+"");
            param.put("startPage", startPage==null?"":startPage.toString());
            param.put("pageCount", pageCount==null?"":pageCount.toString());
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getCatalogInfo, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("获取频道所有类别的列表V2失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取频道所有类别的列表V2失败");
        }
        return jsonResult;
    }

    /**
     * 修改群聊频道
     * @param appKey
     * @param accountID 语境用户id
     * @param channelNumber 频道号
     * @param channelName 频道名称
     * @param channelIntro 简介
     * @param channelCitycode 城市码
     * @param channelCatalogID 类别id
     * @param channelOpenType 开放标识 1:开放 0:非开放
     * @param channelKeyWords 关键字
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifySecretChannelInfo")
    public CommonJsonResult modifySecretChannelInfo(@RequestParam String appKey,@RequestParam String accountID,@RequestParam String channelNumber,
                                             String channelName, String channelIntro , Integer channelCitycode, Integer channelCatalogID ,Integer channelIsVerify,
                                             Integer channelOpenType, String channelKeyWords,HttpServletRequest request){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            MultipartFile file = null;
            try {
                //上传图片并返回url
                CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
                if(multipartResolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                    Iterator<String> iter = multiRequest.getFileNames();
                    while (iter.hasNext()) {
                        file = multiRequest.getFile(iter.next());
                        break;
                    }
                }
            } catch (Exception e1) {
                logger.error("图片保存失败",e1);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("图片保存失败");
            }
            String channelCatalogUrl ="";
            if(file!=null) {
                String result = audioService.saveFile(appkey, secret, file, saveFile);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String ERRORCODE = jsonObject.getString("ERRORCODE");
                if (("0").equals(ERRORCODE)) {
                    channelCatalogUrl = jsonObject.getJSONObject("RESULT").getString("url");
                }
            }
            Map<String, String> param = new HashMap<String, String>(12);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            param.put("channelName", channelName==null?"":channelName);
            param.put("channelIsVerify", channelIsVerify==null?"":channelIsVerify+"");
            param.put("channelIntro", channelIntro==null?"":channelIntro);
            param.put("channelCatalogUrl", channelCatalogUrl);
            param.put("channelCitycode", channelCitycode==null?"":channelCitycode+"");
            param.put("channelCatalogID", channelCatalogID==null?"":channelCatalogID+"");
            param.put("channelOpenType", channelOpenType==null?"":channelOpenType+"");
            param.put("channelKeyWords", channelKeyWords==null?"":channelKeyWords+"");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(modifySecretChannelInfo , param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("修改群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("修改群聊频道失败");
        }
        return jsonResult;
    }

    /**
     * 用户通过APP申请主播频道
     * @param appKey
     * @param accountID 语境用户ID
     * @param channelNumber 频道号
     * @param channelName 频道名
     * @param channelIntroduction 频道介绍
     * @param chiefAnnouncerIntr 主播简介
     * @param channelCityCode 频道城市码
     * @param channelCatalogID 类别ID
     * @param channelKeyWords 关键字
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyMicroChannelV2")
    public CommonJsonResult applyMicroChannelV2(HttpServletRequest request, @RequestParam String appKey, @RequestParam String accountID, @RequestParam String channelNumber,
                                                @RequestParam String channelName, @RequestParam String channelIntroduction, @RequestParam String chiefAnnouncerIntr,
                                                @RequestParam String channelCityCode, @RequestParam String channelCatalogID,String channelKeyWords) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            MultipartFile file = null;
            try {
                //上传图片并返回url
                CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
                if(multipartResolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                    Iterator<String> iter = multiRequest.getFileNames();
                    while (iter.hasNext()) {
                        file = multiRequest.getFile(iter.next());
                        break;
                    }
                }
            } catch (Exception e1) {
                logger.error("图片保存失败",e1);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("图片保存失败");
            }
            String channelCatalogUrl = "";
            if(file!=null) {
                String result = audioService.saveFile(appkey, secret, file, saveFile);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String ERRORCODE = jsonObject.getString("ERRORCODE");
                if (("0").equals(ERRORCODE)) {
                    channelCatalogUrl = jsonObject.getJSONObject("RESULT").getString("url");
                }
            }
            Map<String, String> param = new HashMap<String, String>(5);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            param.put("channelName", channelName==null?"":channelName);
            param.put("channelIntroduction", channelIntroduction==null?"":channelIntroduction);
            param.put("channelCatalogUrl", channelCatalogUrl);
            param.put("chiefAnnouncerIntr", chiefAnnouncerIntr==null?"":chiefAnnouncerIntr+"");
            param.put("channelCityCode", channelCityCode==null?"":channelCityCode+"");
            param.put("channelCatalogID", channelCatalogID==null?"":channelCatalogID+"");
            param.put("channelKeyWords", channelKeyWords==null?"":channelKeyWords+"");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(applyMicroChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("用户通过APP申请主播频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("用户通过APP申请主播频道失败");
        }
        return jsonResult;
    }

    /**
     * 获取主播频道列表V2
     * @param appKey
     * @param accountID
     * @param channelStatus 频道状态
     * @param channelNumber 频道号
     * @param infoType 函数接口
     * @param startPage 开始页
     * @param pageCount 页面大小
     * @param cityCode 城市码
     * @param channelName 频道名
     * @param catalogID 类别ID
     * @param channelKeyWords 关键字
     * @return
     */
    @ResponseBody
    @RequestMapping("/fetchMicroChannelV2")
    public CommonJsonResult fetchMicroChannelV2(@RequestParam String appKey, @RequestParam String accountID,@RequestParam Integer channelStatus,
                                     String channelNumber,@RequestParam Integer infoType, Integer pageCount,Integer startPage,
                                    Integer cityCode, String channelName, Integer catalogID,String channelKeyWords) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(11);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelStatus", channelStatus+"");
            param.put("channelNumber", channelNumber==null?"":channelNumber);
            param.put("infoType", infoType+"");
            param.put("channelName", channelName==null?"":channelName);
            param.put("startPage", startPage==null?"":startPage+"");
            param.put("pageCount", pageCount==null?"":pageCount+"");
            param.put("cityCode", cityCode==null?"":cityCode+"");
            param.put("catalogID", catalogID==null?"":catalogID+"");
            param.put("channelKeyWords", channelKeyWords==null?"":channelKeyWords);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(fetchMicroChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("获取主播频道列表失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取主播频道列表失败");
        }
        return jsonResult;
    }

    /**
     * 获取主播频道用户列表V2
     * @param appKey
     * @param accountID
     * @param channelNumber 频道号
     * @param startPage 开始页
     * @param pageCount 页面大小
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBossFollowListMicroChannelV2")
    public CommonJsonResult getBossFollowListMicroChannelV2(@RequestParam String appKey, @RequestParam String accountID,@RequestParam String channelNumber,
                                                 Integer pageCount,Integer startPage) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(5);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber==null?"":channelNumber);
            param.put("startPage", startPage==null?"":startPage+"");
            param.put("pageCount", pageCount==null?"":pageCount+"");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getBossFollowListMicroChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                Map<String, Map<String, Object>> result = (Map) JsonMapper.fromJson(content, Map.class);
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
                Map<String,Object> map = new HashMap<String, Object>(2);
                String count =(String)result.get("RESULT").get("count");
                List<Map<String, Object>> list = (List) result.get("RESULT").get("list");
                List<String> friendAccountIDList = new ArrayList<String>(list.size());
                for (Map<String, Object> friendAccount : list) {
                    friendAccountIDList.add(friendAccount.get("accountID").toString());
                }
                if (friendAccountIDList.size() != 0) {
                    //查询用户等级中的头像,性别,和地区
                    List<UserDetailVo> userGradeList = userGradeService.queryUserFriendDetailList(friendAccountIDList);
                    //查询好友
                    List<UserFriend> userFriendList = userFriendService.getAllFriends(accountID);

                    for (Map<String, Object> friendAccount : list) {
                        //添加用户头像
                        for (UserDetailVo userDetailVo : userGradeList) {
                            if (friendAccount.get("accountID").equals(userDetailVo.getAccountID())) {
                                friendAccount.put("userHeadName", userDetailVo.getUserHeadName() == null ? "" : userDetailVo.getUserHeadName());
                                friendAccount.put("gender",userDetailVo.getGender()==null?"":userDetailVo.getGender());
                                friendAccount.put("userArea",userDetailVo.getUserAreaCode() == null ? "" : MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode())==null?"":MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode()));
                                friendAccount.put("isAllowedOpinion", userDetailVo.getIsAllowedOpinion());
                                friendAccount.put("isVerifyOpinion", userDetailVo.getIsVerifyOpinion());
                            }
                        }
                        for (UserFriend userFriend : userFriendList) {
                            //添加用户是否为好友
                            if (userFriend.getIsAgree()==1
                                    && ((accountID.equals(userFriend.getAccountID()) && friendAccount.get("accountID").equals(userFriend.getFriendAccountID())) ||
                                    (accountID.equals(userFriend.getFriendAccountID()) && friendAccount.get("accountID").equals(userFriend.getAccountID())))) {
                                friendAccount.put("isFriend", 1);
                            }
                        }
                        if (friendAccount.get("isFriend") == null) {
                            friendAccount.put("isFriend", 0);
                        }
                    }
                    map.put("count",count);
                    map.put("list",list);
                    jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
                    jsonResult.setRESULT(map);
                }
            }
        } catch (Exception e) {
            logger.error("获取主播频道用户列表失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取主播频道用户列表失败");
        }
        return jsonResult;
    }


    /**
     * 推送申请加入频道消息
     * @param msgContent 推送内容(验证消息)`
     * @param adminAccountID
     * @param channelName
     * @param accountNickName
     * @return
     */
    @RequestMapping("/pushJoinSecretChannelMessage")
    @ResponseBody
    public CommonJsonResult pushJoinSecretChannelMessage(@RequestParam String msgContent, @RequestParam String adminAccountID,
                                                         @RequestParam String channelName , @RequestParam String accountNickName,
                                                         @RequestParam String applyAccountID, @RequestParam String applyIdx,
                                                         @RequestParam String adminAccountNickName,@RequestParam Integer gender,
                                                         @RequestParam String userArea) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            Map<String,Object> params = new HashMap<String,Object>(1);
            params.put("verifyMessage",msgContent);
            params.put("gender",gender);
            params.put("userArea",userArea);
            params.put("accountNickName",accountNickName);
            params.put("applyIdx",applyIdx);
            params.put("channelName",channelName);
            //推送消息
           Integer messageID = userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, adminAccountID,accountNickName+" 请求加入你创建的群聊频道 "+channelName+"，快去看看吧！",ConstantsUtil.Push.JOIN_SECRET_CHANNEL_TYPE,params,applyAccountID);
            params = new HashMap<String, Object>(5);
            params.put("accountID",adminAccountID);
            params.put("accountNickName",accountNickName);
            params.put("applyAccountID",applyAccountID);
            params.put("applyIdx",applyIdx);
            params.put("channelName",channelName);
            params.put("messageID",messageID);
            SendMessageUtil.sendToTerminalV2(sendPersonalWeibo, callBackJoinSecretChannel, params, adminAccountID, "频道申请，亲爱的"+adminAccountNickName+"，"+accountNickName+"请求加入你创建的群聊频道"+channelName+"，验证消息为"+msgContent+"，马上按加键接受或者按加加键拒绝，也可以在离车后在手机上操作。");
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        }catch (Exception e){
            logger.error("申请加入频道推送消息失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("申请加入频道推送消息失败");
        }
        return jsonResult;
    }
    /**
     * weme 终端 加入频道 回调
     *
     * @return
     */
    @RequestMapping("/user/v1.0/callBackJoinSecretChannel")
    @ResponseBody
    public CommonJsonResult callBackJoinSecretChannel(HttpServletRequest request) throws Exception {
        CommonJsonResult jsonResult = new CommonJsonResult();

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        String appkey = ConstantsUtil.getAppKey("Android");
        String secret = ConstantsUtil.getSecret("Android");
        String actionType = params.get("actionType");
        String accountID = params.get("accountID");
        String accountNickName = params.get("accountNickName");
        String applyAccountID = params.get("applyAccountID");
        Integer applyIdx = new Integer(params.get("applyIdx"));
        Integer messageID = new Integer(params.get("messageID"));
        String channelName = params.get("channelName");
        Map<String, String> param = new HashMap<String, String>(7);
        param.put("appKey", appkey);
        param.put("secret",secret);
        param.put("accountID", accountID);
        param.put("applyAccountID", applyAccountID);
        param.put("applyIdx", applyIdx==null?"":applyIdx+"");
        /**
         * actionType 操作类型
         动作类型:1-拨打电话1;2-拨打电话2;3-发送语音,4-语音命令,5-发送群组语音,6-yes回复,7-no回复,8-关机,9-路过签到,10-语音回复,需返回bizid
         目前回调只会用到 6 YES, 7 NO, 10回复
         */
        if ("6".equals(actionType)) {
            param.put("checkStatus", "1");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(veritySecretChannelMessage, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
                //推送消息
            userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, applyAccountID,accountNickName+" 同意你加入群聊频道 "+channelName+" 要不要去关联这个频道到WEME呢？",ConstantsUtil.Push.AGREE_SECRET_CHANNEL_MESSAGE_TYPE,null,accountID);
            userService.updateMessageIsAgree(messageID,'1');
        }else if("7".equals(actionType)){
            param.put("checkStatus", "2");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(veritySecretChannelMessage, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
            userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, applyAccountID,accountNickName+" 拒绝你加入群聊频道 "+channelName+"，再去看看其他频道吧！",ConstantsUtil.Push.DISAGREE_SECRET_CHANNEL_MESSAGE_TYPE,null,accountID);
            userService.updateMessageIsAgree(messageID,'0');
        }
        return jsonResult;
    }

    /**
     * 获取服务频道列表
     * @param appKey
     * @param startPage
     * @param pageCount
     * @param isDetail 如需返回菜单的内容，必填写1
     * @param accountID
     * @param longitude
     * @param latitude
     * @param defineName
     * @param actionType
     * @return
     */

    @ResponseBody
    @RequestMapping("/getCustomDefineInfo")
    public CommonJsonResult getCustomDefineInfo(@RequestParam String appKey , @RequestParam Integer startPage,@RequestParam Integer pageCount,String isDetail,
                                             String accountID,String longitude,String latitude,String defineName,String actionType  ){
        //后三个参数留作备用
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(6);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("isDetail", isDetail==null?"":isDetail);
            param.put("startPage", startPage==null?"":startPage+"");
            param.put("pageCount", pageCount==null?"":pageCount+"");
            param.put("defineName", defineName==null?"":defineName);
            param.put("actionType", actionType==null?"":actionType+"");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getCustomDefineInfo, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("获取服务频道列表失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取服务频道列表失败");
        }
        return jsonResult;
    }

    /**
     *获取服务频道内容
     * @param appKey
     * @param startPage
     * @param pageCount
     * @param serviceID
     * @return
     */
    @ResponseBody
    @RequestMapping("/getServiceContent")
    public CommonJsonResult getServiceContent(@RequestParam String appKey , @RequestParam Integer startPage,@RequestParam Integer pageCount,
                                              @RequestParam String serviceID,  @RequestParam String accountID,String receiveAccountID){
        //后三个参数留作备用
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(6);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("serviceID", serviceID);
            param.put("accountID", accountID);
            param.put("receiveAccountID", receiveAccountID==null?"":receiveAccountID);
            param.put("startPage", startPage==null?"":startPage+"");
            param.put("pageCount", pageCount==null?"":pageCount+"");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getServiceContent, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("获取服务频道内容失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取服务频道内容失败");
        }
        return jsonResult;
    }

    /**
     * 判断是否加入车队
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/judgeIsJoinMcade")
    public CommonJsonResult judgeIsJoinMcade(@RequestParam String accountID){
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            Map<String,Object> map = new HashMap<String, Object>(1);
            int flag = serverChannelService.judgeIsJoinMcade(accountID);
            map.put("isJoinMcade",flag);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(map);
        } catch (Exception e) {
            logger.error("查询用户是否加入车队失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("查询用户是否加入车队失败");
        }
        return jsonResult;
    }
//     /**
//     * 根据频道查询菜单
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/getServerMenu")
//    public CommonJsonResult getServerMenu(@RequestParam Integer serverChannelID){
//        CommonJsonResult jsonResult = new CommonJsonResult();
//        try {
//            List<ServerMenu> serverMenuList = serverMenuService.queryServerMenu(serverChannelID);
//            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//            jsonResult.setRESULT(serverMenuList);
//        } catch (Exception e) {
//            logger.error("获取服务频道列表失败",e);
//            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
//            jsonResult.setRESULT("获取服务频道列表失败");
//        }
//        return jsonResult;
//    }
//    /**
//     * 查询服务频道消息
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/queryServerChannelMessage")
//    public CommonJsonResult queryServerChannelMessage(@RequestParam Integer serverChannelID ,@RequestParam Integer startPage,@RequestParam Integer pageCount){
//        CommonJsonResult jsonResult = new CommonJsonResult();
//        try {
//            PageList<ServerChannelMessage> serverChannelMessageList = serverChannelMessageService.pageQueryServerChannelMessage(serverChannelID,startPage,pageCount);
//            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//            jsonResult.setRESULT(serverChannelMessageList);
//        } catch (Exception e) {
//            logger.error("获取服务频道消息列表失败",e);
//            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
//            jsonResult.setRESULT("获取服务频道消息列表失败");
//        }
//        return jsonResult;
//    }
    /**
     * 转移频道
     * @param appKey 应用标识
     * @param accountID 用户ID
     * @param password 密码
     * @param channelNumber 频道号
     * @param receiverAccountID 接受者id
     * @return
     */
    @RequestMapping("/transferSecretChannel")
    @ResponseBody
    public CommonJsonResult transferSecretChannel(@RequestParam String appKey,@RequestParam  String accountID,
                                                @RequestParam String password, @RequestParam String channelNumber,@RequestParam String receiverAccountID,
                                                @RequestParam String accountNickName,@RequestParam String receiverAccountNickName,@RequestParam String channelName) {

        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getUserJoinListSecretChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                Map<String, Map<String, Object>> result = (Map) JsonMapper.fromJson(content, Map.class);
                List<Map<String, String>> list = (List) result.get("RESULT").get("list");
                param = new HashMap<String, String>(6);
                param.put("appKey", appkey);
                param.put("secret", secret);
                param.put("accountID", accountID);
                param.put("password", password);
                param.put("channelNumber", channelNumber);
                param.put("receiverAccountID", receiverAccountID);
                //签名
                sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
                param.put("sign", sign);
                requester = new HttpRequester();
                respons = requester.sendPost(transferSecretChannel, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                    content = respons.getContent();
                    jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
                    Map<String, String> resultMap = (Map<String, String>) JsonMapper.fromJson(content, Map.class);
                    String errorcode = resultMap.get("ERRORCODE").toString();
                    if ("0".equals(errorcode)) {
                            for(Map<String,String> map : list){
                                String role = map.get("role");
                                String nickname = map.get("nickname");
                                String memberAccountID = map.get("accountID");
                                Map<String,Object> params = new HashMap<String, Object>(1);
                                params.put("channelNumber", channelNumber==null?"":channelNumber);
                                if("0".equals(role)){
                                    userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, memberAccountID,nickname+"，你加入的群聊频道 "+channelName+" 管理员已变更，快去看看吧！",ConstantsUtil.Push.TRANSFER_SECRET_CHANNEL_TO_MEMBER,params);
                                }else if("1".equals(role)){
                                    userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, accountID,accountNickName+"，你成功将你创建的群聊频道 "+channelName+" 转移给 "+receiverAccountNickName+"，快去看看吧！",ConstantsUtil.Push.TRANSFER_SECRET_CHANNEL_TO_ADMIN,params);
                                }
                            }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("转移频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("转移频道失败");
        }
        return jsonResult;
    }
    /**
     * 解散频道
     *
     * @param appKey 应用标识
     * @param accountID 用户ID
     * @param password 密码
     * @param channelNumber 频道号
     * @return
     */
    @RequestMapping("/dissolveSecretChannel")
    @ResponseBody
    public CommonJsonResult  dissolveSecretChannel(@RequestParam String appKey,@RequestParam  String accountID,
                                                  @RequestParam String password, @RequestParam String channelNumber,
                                                  @RequestParam String channelName) {

        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("channelNumber", channelNumber);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(getUserJoinListSecretChannel, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                Map<String, Map<String, Object>> result = (Map) JsonMapper.fromJson(content, Map.class);
                List<Map<String, String>> list = (List) result.get("RESULT").get("list");
                param = new HashMap<String, String>(5);
                param.put("appKey", appkey);
                param.put("secret", secret);
                param.put("accountID", accountID);
                param.put("password", password);
                param.put("channelNumber", channelNumber);
                //签名
                sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
                param.put("sign", sign);
                requester = new HttpRequester();
                respons = requester.sendPost(dissolveSecretChannel, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                     content = respons.getContent();
                    jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
                    Map<String, String> resultMap = (Map<String, String>) JsonMapper.fromJson(content, Map.class);
                    String errorcode = resultMap.get("ERRORCODE").toString();
                    if ("0".equals(errorcode)) {
                        for (Map<String, String> map : list) {
                            String role = map.get("role");
                            String nickname = map.get("nickname");
                            String actionType = map.get("actionType");
                            String memberAccountID = map.get("accountID");
                            if ("0".equals(role)) {
                                userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, memberAccountID, nickname + "，你加入的群聊频道 " + channelName + " 已解散，快去看看吧！", ConstantsUtil.Push.DISSOLVE_SECRET_CHANNEL_TO_MEMBER, null);
                            }
                            if ("4".equals(actionType)) {
                                userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, memberAccountID, nickname + " 你加键关联的群聊频道 "+channelName+" 已解散，快去看看吧！", ConstantsUtil.Push.DISSOLVE_SECRET_CHANNEL_TO_MEMBER, null);
                            }
                            if ("5".equals(actionType)) {
                                userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, memberAccountID, nickname + " 你加加键关联的群聊频道 "+channelName+" 已解散，快去看看吧！", ConstantsUtil.Push.DISSOLVE_SECRET_CHANNEL_TO_MEMBER, null);
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
                logger.error("解散频道失败",e);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("解散频道失败");
        }
            return jsonResult;
        }

    /**
     *管理群聊频道
     * @param appKey
     * @param accountID
     * @param channelNumber
     * @param infoType
     * @param curStatus
     * @param userAccountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/manageSecretChannelUsers")
    public CommonJsonResult manageSecretChannelUsers(@RequestParam String appKey, @RequestParam String accountID,
                                                @RequestParam String channelNumber,@RequestParam Integer infoType,
                                                @RequestParam Integer curStatus,@RequestParam String userAccountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(6);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("accountID", accountID);
            param.put("curStatus", curStatus+"");
            param.put("channelNumber", channelNumber==null?"":channelNumber);
            param.put("infoType", infoType+"");
            param.put("userAccountID", userAccountID);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(manageSecretChannelUsers, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            logger.error("管理群聊频道失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("管理群聊频道失败");
        }
        return jsonResult;
    }
}
