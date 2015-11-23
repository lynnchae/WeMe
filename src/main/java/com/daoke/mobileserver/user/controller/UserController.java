package com.daoke.mobileserver.user.controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.daoke.mobileserver.common.config.RedisVariable;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.common.service.DrivingRedisService;
import com.daoke.mobileserver.custom.service.CustomService;
import com.daoke.mobileserver.map.service.impl.MapServiceImpl;
import com.daoke.mobileserver.report.dto.AssociateAccount;
import com.daoke.mobileserver.user.entity.*;
import com.daoke.mobileserver.user.service.*;
import com.daoke.mobileserver.user.service.impl.UserRochelleRuleServiceImpl;
import com.daoke.mobileserver.util.*;
import com.daoke.mobileserver.weibo.service.WeiBoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author wangliming
 * @version 1.0
 * @date 2014-9-26 上午10:19:43
 */
@Controller
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);
    private Map<String, Object> map = new HashMap<String, Object>();

    @Value("#{apiConfig[getImeiPhone]}")
    private String getImeiPhone;

    @Value("#{apiConfig[getMirrtalkInfoByImei]}")
    private String getMirrtalkInfoByImei;

    @Value("#{apiConfig[userBindAccountMirrtalk]}")
    private String userBindAccountMirrtalk;

    @Value("#{apiConfig[fixUserInfo]}")
    private String fixUserInfo;

    @Value("#{apiConfig[getUserInfo]}")
    private String getUserInfo;

    @Value("#{apiConfig[checkLogin]}")
    private String checkLogin;

    @Value("#{apiConfig[checkRegistration]}")
    private String checkRegistration;

    @Value("#{apiConfig[sendSms]}")
    private String sendSms;

    @Value("#{apiConfig[addCustomAccount]}")
    private String addCustomAccount;

    @Value("#{apiConfig[resetUserPassword]}")
    private String resetUserPassword;

    @Value("#{apiConfig[updateUserPassword]}")
    private String updateUserPassword;

    @Value("#{apiConfig[verifyEmailOrMobile]}")
    private String verifyEmailOrMobile;

    @Value("#{apiConfig[getAccountIDFromMobile]}")
    private String getAccountIDFromMobile;

    @Value("#{apiConfig[associateAccountWithAccountID]}")
    private String associateAccountWithAccountID;

    @Value("#{apiConfig[createAccountID]}")
    private String createAccountID;

    @Value("#{apiConfig[disconnectAccount]}")
    private String disconnectAccount;

    @Value("#{apiConfig[checkImei]}")
    private String checkImei;

    @Value("#{contentConfig[message]}")
    private String content;

    @Value("#{apiConfig[checkMobileRegister]}")
    private String checkMobileRegister;

    @Value("#{apiConfig[sendPersonalWeibo]}")
    private String sendPersonalWeibo;

    @Value("#{apiConfig[userBindMobile]}")
    private String userBindMobile;


    @Value("#{apiConfig[callBackReceiveRochelle]}")
    private String callBackReceiveRochelle;

    @Value("#{apiConfig[callBackAddFriend]}")
    private String callBackAddFriend;

    @Autowired
    private UserService userService;

    @Autowired
    private IFriendSettingService friendSettingService;

    @Autowired
    private IUserFriendService userFriendService;

    @Autowired
    private IUserTalkRecordService userTalkRecordService;

    @Autowired
    private WeiBoService weiBoService;

    @Autowired
    private IUserRochelleDetailService userRochelleDetailService;
    @Autowired
    private IUserGradeService userGradeService;
    @Autowired
    private CustomService customService;
    @Autowired
    private DrivingRedisService drivingRedisService;


    @Value("#{apiConfig[saveFile]}")
    private String saveFile;

    @Autowired
    private IUserRankDetailService userRankDetailService;

    @Autowired
    private IUserRochelleRuleService userRochelleRuleService;

    @Autowired
    private IIndexRankingDetailService indexRankingDetailService;
    @Autowired
    private IUserRankInfoService UserRankInfoService;
    @Autowired
    private IReportInfoService reportInfoService;

    @Value("#{apiConfig[getSecretchannelRelationByUserkey]}")
    private String getSecretchannelRelationByUserkey;



    //排名默认起始
    private final int DEFAULT_BEGAN = 0;
    //排名条数
    private final int DEFAULT_FINISH = 100;

    /**
     * 通过用户账号编号获得imei和手机号码
     *
     * @param appKey
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/getImeiPhone")
    public String getImeiPhone(String appKey, String accountID) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.getImeiPhone(appkey, secret, accountID, getImeiPhone);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }


    /**
     * 通过imei获取语境用户信息
     *
     * @param appKey
     * @param IMEI
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMirrtalkInfoByImei")
    public String getMirrtalkInfoByImei(String appKey, String IMEI) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.getMirrtalkInfoByImei(appkey, secret, IMEI, getMirrtalkInfoByImei);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }

    /**
     * 绑定用户账号
     *
     * @param appKey
     * @param accountID
     * @param IMEI
     * @return
     */
    @ResponseBody
    @RequestMapping("/userBindAccountMirrtalk")
    public String userBindAccountMirrtalk(String appKey, String accountID, String IMEI) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            //String ruleCode = "bindWEME";
            List<UserRochelleDetail> userRochelleDetailList = userRochelleDetailService.queryByAccountIDAndRuleCode(accountID, ConstantsUtil.Grade.BINDING_WEME);
            if (userRochelleDetailList.size() == 0) {

                //预奖励
                Object[] obj = userGradeService.toReward(accountID, IMEI, ConstantsUtil.Grade.BINDING_WEME);
                logger.info("预奖励:"+ obj.toString());
                UserGrade userGrade = this.userGradeService.queryByAccountID(accountID);
                String terminalText = "";
                if (userGrade != null && StringUtils.isNotBlank(userGrade.getNickName()) && obj != null && obj[3] != null) {
                    terminalText = obj[3].toString().replace("昵称",userGrade.getNickName());
                } else {
                    // 取IMEI后四位
                    if(StringUtils.isNotBlank(IMEI)){
                        terminalText = obj[3].toString().replace("昵称","道客"+IMEI.substring(IMEI.length() - 4, IMEI.length()));
                    } else{
                        terminalText = obj[3].toString().replace("昵称","");
                    }
                }
                //记录到消息表中
                Map<String,Object> param = new HashMap<String,Object>();
                //推送到手机
                param.put("rewardID", (Integer) obj[2]);
                param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get(ConstantsUtil.Grade.BINDING_WEME));
                userService.pushMessage("任务奖励", accountID, obj[4].toString(), "rochelleReward", param);

                //推送消息
                SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], accountID, terminalText);
            }
            result = userService.userBindAccountMirrtalk(appkey, secret, accountID, IMEI,
                    userBindAccountMirrtalk);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            e.printStackTrace();
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }

    /**
     * 修改用户信息
     *
     * @param appKey
     * @param accountID
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/fixUserInfo")
    public String fixUserInfo(String appKey, String accountID, String name, String nickname,
                              String gender, String plateNumber, String drivingLicense, String homeAddress, String areaCode) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            String userInfo = userService.getUserInfo(appkey, secret, accountID, getUserInfo);
            org.json.JSONObject userInfoJson = new org.json.JSONObject(userInfo);
            Object resultObj = userInfoJson.get("RESULT");
            org.json.JSONObject resultJson = new org.json.JSONObject(resultObj.toString());
            String oldNickname = resultJson.getString("nickname");
            if (StringUtils.isNotBlank(nickname)) {
                if (!nickname.equals(oldNickname)) {
                 //   String ruleCode = "settingNickName";
                    List<UserRochelleDetail> userRochelleDetailList = userRochelleDetailService.queryByAccountIDAndRuleCode(accountID, ConstantsUtil.Grade.SET_NICKNAME);
                    if (userRochelleDetailList.size() == 0) {
//
                        //预奖励
                        Object[] obj = userGradeService.toReward(accountID, null, ConstantsUtil.Grade.SET_NICKNAME);
                        logger.info("预奖励:"+ obj.toString());
                        UserGrade userGrade = this.userGradeService.queryByAccountID(accountID);
                        String terminalText = "";
                        if (userGrade != null && StringUtils.isNotBlank(userGrade.getNickName()) && obj != null && obj[3] != null) {
                            terminalText = obj[3].toString().replace("昵称",userGrade.getNickName());
                        } else {
                                terminalText = obj[3].toString().replace("昵称","");
                        }
                        //记录到消息表中
                        Map<String,Object> param = new HashMap<String,Object>();
                        //推送到手机
                        param.put("rewardID", (Integer) obj[2]);
                        param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get(ConstantsUtil.Grade.SET_NICKNAME));
                        userService.pushMessage("任务奖励", accountID, obj[4].toString(), "rochelleReward", param);

                        //推送消息
                        SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], accountID, terminalText);
                    }
                }
            }
            result = userService.fixUserInfo(appkey, secret, accountID, name, nickname, gender,
                    plateNumber, drivingLicense, homeAddress, fixUserInfo);
            Map<String, String> map = new HashMap<String, String>();
            if(StringUtils.isNotBlank(nickname)){
                map.put("nickName", nickname);
            }else if(StringUtils.isNotBlank(gender)){
                map.put("gender", gender);
            }else if(StringUtils.isNotBlank(areaCode)){
                map.put("userAreaCode", areaCode);
            }
            map.put("accountID", accountID);
            if(map.size()>1){
                userGradeService.updateUserInfo(map);
            }
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, "修改失败");
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }


    /**
     * 获取用户信息
     *
     * @param appKey
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public CommonJsonResult getUserInfo(String appKey, String accountID, HttpServletResponse response) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        CommonJsonResult jsonResult = null;
        try {
            result = userService.getUserInfo(appkey, secret, accountID, getUserInfo);

            jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
//                    response.getOutputStream().write(result.getBytes("utf-8"));
            if (jsonResult.getERRORCODE().equals("0")) {
                Map<String, String> map = (Map<String, String>) jsonResult.getRESULT();
                UserGrade ug = userGradeService.queryByAccountID(accountID);
                if (ug != null) {
                    map.put("userHeadName", ug.getUserHeadName());
                    map.put("userAreaCode", MapServiceImpl.CITY_CODE.get(ug.getUserAreaCode()));
                    map.put("gender", ug.getGender() + "");
                    jsonResult.setRESULT(map);
                    return jsonResult;
                }
            } else {
                return jsonResult;
            }
            logger.info(appKey + "===>" + result);
            result = null;

        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return jsonResult;
    }

    /**
     * 用户登录
     *
     * @param appKey
     * @param username
     * @param daokePassword
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestHeader(value = "X-Real-IP", required = false,defaultValue = "172.16") String realIP,
                             String appKey, String username, String daokePassword, HttpServletRequest request,
                             HttpServletResponse response) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        String clientIP = request.getRemoteAddr();
        logger.info("X-Real-IP=======>" + realIP);
        if (clientIP.startsWith("172.16")) {
            clientIP = realIP;
        }
        try {
            result = userService.checkLogin(appkey, secret, username, daokePassword, clientIP,
                    checkLogin);
            //检查登录成功后再检查用户等级表是否有数据 没数据就插入
            CommonJsonResult jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
            if (jsonResult != null && jsonResult.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                Map map = (Map) jsonResult.getRESULT();
                userGradeService.updateUserInfo(map.get("accountID").toString(),null,null, map.get("nickname").toString());
            }

            logger.info(appKey + "===>" + result);
            response.getOutputStream().write(result.getBytes("utf-8"));
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(e.getMessage());
        }   

        return result;
    }

    /**
     * 用户注册
     *
     * @param appKey
     * @param mobile
     * @param request
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/toReg")
//    public String toReg(String appKey, String mobile, HttpServletRequest request) {
//        String result = null;
//        String appkey = ConstantsUtil.getAppKey(appKey);
//        String secret = ConstantsUtil.getSecret(appKey);
//        try {
//            result = userService.checkRegistration(appkey, secret, mobile, checkRegistration);
//            JSONObject jsonObject = JSONObject.fromObject(result);
//            String ERRORCODE = jsonObject.getString("ERRORCODE");
//            if (("0").equals(ERRORCODE)) {
//                String message = new String(content.getBytes("iso-8859-1"), "utf-8");
//                result = weiBoService.sendSms(appkey, secret, mobile, message, sendSms);
//                jsonObject = JSONObject.fromObject(result);
//                ERRORCODE = jsonObject.getString("ERRORCODE");
//                if (("0").equals(ERRORCODE)) {
////                    JSONObject RESULT = jsonObject.getJSONObject("RESULT");
////                    String verificationCode = RESULT.getString("verificationCode");
////                    map.put(mobile, verificationCode);
//                    result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
//                            ConstantsUtil.RESULT_OK);
//                    logger.info(appKey + "===>" + result);
////                    userGradeService.updateUserGrade(RESULT.getString("accountID"), 0,0);
//                } else {
//                    logger.warn(appKey + "===>" + result);
//                }
//            } else {
//                logger.warn(appKey + "===>" + result);
//            }
//        } catch (Exception e) {
//            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
//                    ConstantsUtil.RESULT_SERVICE_ERROR);
//            logger.error(appKey
//                    + "===>"
//                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
//                    e.toString()));
//        }
//
//        return result;
//    }
    @ResponseBody
    @RequestMapping(value = "/toReg")
    public CommonJsonResult toReg(String appKey, String mobile, HttpServletRequest request) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String verifyCode = String.valueOf(RandomNumberUtil.genRandomNum(6));
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {

            request.getHeader("");

            String result = userService.checkRegistration(appkey, secret, mobile, checkRegistration);
             jsonResult =  (CommonJsonResult)JsonMapper.fromJson(result,CommonJsonResult.class);
          //  JSONObject jsonObject = JSONObject.fromObject(result);
           // String ERRORCODE = jsonObject.getString("ERRORCODE");
            if (("0").equals(jsonResult.getERRORCODE())) {
                //有效时间1分钟
               boolean isSuc= SendTemplateSMSUtil.sendTemplateSMS(mobile, "21109", verifyCode, "1");
                if(isSuc){
                    //验证码插入表中
                    userService.insertIdentifyingCode(mobile, verifyCode, "1","1");
                    //为了兼容1.0的版本
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT("发送注册验证码成功");
                } else{
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT("发送注册验证码失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送注册验证码失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("发送注册验证码失败");
        }
        return jsonResult;
    }

    /**
     * 获取验证码
     * @param appKey
     * @param mobile
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/v2/toReg")
    public CommonJsonResult toRegV2(String appKey, String mobile, HttpServletRequest request) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String verifyCode = String.valueOf(RandomNumberUtil.genRandomNum(6));
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            String result = userService.checkRegistration(appkey, secret, mobile, checkRegistration);
            jsonResult =  (CommonJsonResult)JsonMapper.fromJson(result,CommonJsonResult.class);
            if (("0").equals(jsonResult.getERRORCODE())) {
                //有效时间1分钟
                boolean isSuc= SendTemplateSMSUtil.sendTemplateSMS(mobile, "21109", verifyCode, "1");
                if(isSuc){
                    //验证码插入表中
                    userService.insertIdentifyingCode(mobile, verifyCode, "1","1");
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT("发送注册验证码成功");
                } else{
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT("发送注册验证码失败");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送注册验证码失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("发送注册验证码失败");
        }
        return jsonResult;
    }
    /**
     * 注册
     *
     * @param appKey
     * @param daokePassword
     * @param nickname
     * @param mobile
     * @param verificationCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustomAccount")
    public String addCustomAccount(String appKey, String daokePassword, String nickname,
                                   String mobile, String verificationCode) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);

        try {

            if (StringUtils.isNotEmpty(verificationCode)
                    && userService.findValidCode(verificationCode,mobile) > 0) {
                result = userService.addCustomAccount(appkey, secret, daokePassword, "2", nickname,
                        mobile, addCustomAccount);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String ERRORCODE = jsonObject.getString("ERRORCODE");
                JSONObject resultJsonObject = JSONObject.fromObject(jsonObject.getString("RESULT"));
                if (("0").equals(ERRORCODE)) {
                    logger.info(appKey + "===>" + result);
                    userGradeService.updateUserGrade(resultJsonObject.getString("accountID"),nickname, 0,0);
                } else {
                    logger.warn(appKey + "===>" + result);
                }
            } else {
                result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_VERICODE,
                        ConstantsUtil.RESULT_VERICODE);
                logger.warn(appKey + "===>" + result);
            }
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }

    /**
     * 重置用户密码
     *
     * @param appKey
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetUserPassword")
    public String resetUserPassword(String appKey, String accountID) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.resetUserPassword(appkey, secret, accountID, resetUserPassword);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + result);
        }

        return result;
    }

    /**
     * 为用户更新密码
     *
     * @param appKey
     * @param accountID
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(String appKey, String accountID, String oldPassword,
                                     String newPassword) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.updateUserPassword(appkey, secret, accountID, oldPassword,
                    newPassword, updateUserPassword);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + appKey + "===>" + result);
        }

        return result;
    }

    /**
     * 验证手机号并修改密码
     *
     * @param appKey
     * @param mobile
     * @param accountID
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyAndreset")
    public String verifyAndreset(String appKey, String mobile, String verificationCode,
                                 String accountID, String oldPassword, String newPassword) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        if (mobile != null && (mobile.replaceAll(" ", "").length() >= 6)) {
            try {
                result = userService.getImeiPhone(appkey, secret, accountID, getImeiPhone);
                String phone = JSONObject.fromObject(result).getJSONObject("RESULT")
                        .getString("phone");
                if (StringUtils.isEmpty(phone)) {
                    if (StringUtils.isNotEmpty(verificationCode)
                            && verificationCode.equals(map.get(mobile))) {
                        result = userService.verifyAndreset(appkey, secret, mobile, accountID,
                                oldPassword, newPassword, verifyEmailOrMobile, updateUserPassword);
                        logger.info(appKey + "===>" + result);
                    } else {
                        result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_VERICODE,
                                ConstantsUtil.RESULT_VERICODE);
                        logger.warn(appKey + "===>" + result);
                    }
                } else {
                    result = userService.updateUserPassword(appkey, secret, accountID, "",
                            newPassword, updateUserPassword);
                    logger.info(appKey + "===>" + result);
                }
            } catch (Exception e) {
                result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PHONE,
                        ConstantsUtil.RESULT_PHONE);
                logger.error(appKey + "===>" + e.getMessage());
            }
        } else {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PHONE,
                    ConstantsUtil.RESULT_PHONE);
            logger.error(appKey + "===>" + result);
        }
        return result;
    }

    /**
     * 根据注册手机号获取账号编号
     *
     * @param appKey
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAccountIDFromMobile")
    public String getAccountIDFromMobile(String appKey, String mobile, HttpServletResponse response) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.getAccountIDFromMobile(appkey, secret, mobile,
                    getAccountIDFromMobile);
            response.getOutputStream().write(result.getBytes("utf-8"));
            logger.info(appKey + "===>" + result);
            result = null;
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + appKey + "===>" + result);
        }

        return result;
    }

    /**
     * 绑定第三方帐户与语镜帐号
     *
     * @param associateAccount
     * @return
     */
    @ResponseBody
    @RequestMapping("/associateAccountWithAccountID")
    public String associateAccountWithAccountID(AssociateAccount associateAccount) {
        String result = null;
        try {
            result = userService.associateAccountWithAccountID(associateAccount,
                    associateAccountWithAccountID);
            logger.info(result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 根据第三方账户创建语镜帐号
     *
     * @param appKey
     * @param account
     * @param loginType
     * @param token
     * @param accessToken
     * @param accessTokenExpiration
     * @param nickname
     * @return
     */
    @ResponseBody
    @RequestMapping("/createAccountID")
    public String createAccountID(String appKey, String account, String loginType, String token,
                                  String accessToken, String accessTokenExpiration, String nickname, String username, String daokePassword,
                                  HttpServletResponse response) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.createAccountID(appkey, secret, account, loginType, token,
                    accessToken, accessTokenExpiration, nickname, username, daokePassword, createAccountID);
            response.getOutputStream().write(result.getBytes("utf-8"));
            logger.info("account===>" + account);
            logger.info(appKey + "===>" + result);
            result = null;
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + e.getMessage());
        }
        return result;
    }

    /**
     * 解绑
     *
     * @param appKey
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/disconnectAccount")
    public String disconnectAccount(String appKey, String accountID) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.disconnectAccount(appkey, secret, accountID, disconnectAccount);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }

    /**
     * 判断给定imei是否允许用户绑定
     *
     * @param appKey
     * @param IMEI
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkImei")
    public String checkImei(String appKey, String IMEI) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = userService.checkImei(appkey, secret, IMEI, checkImei);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e.toString()));
        }

        return result;
    }

  /*  *//**
     * 上传文件通用接口
     *
     * @param appKey
     * @param response
     * @return
     *//*
    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(String appKey,String accountID,HttpServletRequest request,
                                                   HttpServletResponse response){
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        String channelCatalogUrl ="";

        try {
            //上传图片并返回url
            CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile(iter.next());
                    String fileResultUrl = customService.saveFile(appkey, secret, file, saveFile);
                    CommonJsonResult fileResult = (CommonJsonResult) JsonMapper.fromJson(fileResultUrl, CommonJsonResult.class);
                    Map<String,String> param = (Map<String, String>) fileResult.getRESULT();
                    channelCatalogUrl =  param.get("url");
                    break;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey
                    + "===>"
                    + JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    e1.toString()));
            return result;
        }

        return result;
    }*/


    /**
     * 上传用户头像
     *
     * @param appKey
     * @param accountID
     * @param request
     * @return
     */
    @RequestMapping("/uploadHeadImage")
    public
    @ResponseBody
    CommonJsonResult uploadImage(@RequestParam String appKey, @RequestParam String accountID, HttpServletRequest request) {
        CommonJsonResult res = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        String headImageUrl = "";

        try {
            //上传图片并返回url
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile(iter.next());
                    String fileResultUrl = customService.saveFile(appkey, secret, file, saveFile);
                    CommonJsonResult fileResult = (CommonJsonResult) JsonMapper.fromJson(fileResultUrl, CommonJsonResult.class);
                    Map<String, String> param = (Map<String, String>) fileResult.getRESULT();
                    headImageUrl = param.get("url");
                    break;
                }
            }

            if (headImageUrl != null) {
                if (userGradeService.uploadHeadImage(accountID, headImageUrl) > 0) {
                    res.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("headImageUrl", headImageUrl);
                    res.setRESULT(map);
                } else {
                    res.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                    res.setRESULT("头像上传失败");
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("头像上传失败:" + e1.getMessage());
            res.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            res.setRESULT("头像上传失败");
        }

        return res;
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public CommonJsonResult updateUserInfo(@RequestParam String appKey, @RequestParam String accountID, HttpServletRequest request,
                                           String userArea, int gender, String nickName) {
        CommonJsonResult result = new CommonJsonResult();
        try {
            int is = userGradeService.updateUserInfo(accountID, userArea, gender, nickName);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            result.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            result.setRESULT("修改失败");
        }

        return result;
    }

    /**
     * 获取榜单数据
     *
     * @param appKey    平台类型 ios  android
     * @param accountID 用户的accountID
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public CommonJsonResult getList(@RequestParam String appKey, @RequestParam String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> result = userRochelleDetailService.getDetailUserList(accountID,appKey,getSecretchannelRelationByUserkey);
            if (result != null) {
                map.put("accountID", result.get("accountID"));//用户accountID
                //等级
                map.put("grade", result.get("grade"));//用户等级
                map.put("gradeTitle", result.get("gradeTitle"));  //等级名称
                map.put("rochelle", result.get("rochelle")); //谢尔值
                map.put("nextGradeRochelle", result.get("nextGradeRochelle") == null ? "0" : result.get("nextGradeRochelle").toString()); //距离下一等级谢尔值
                //排名
                map.put("allRanking", result.get("allRanking")); //全部道客用户谢尔值排名
                map.put("monthRanking", result.get("monthRanking")); //全部道客用户本月排名
                //指数
                map.put("influenceIndex", result.get("influenceIndex")); //影响指数
                map.put("closeIndex", result.get("closeIndex")); //亲密值数
                map.put("interactIndex", result.get("interactIndex")); //参与指数据
                map.put("meetIndex", result.get("meetIndex")); //相遇指数
                //任务星级
                map.put("addMileageSum", result.get("addMileageSum")); //新增里程
                map.put("mileageCostTime", result.get("mileageCostTime")); //达标用时
                map.put("mePoint", result.get("mePoint")); //捐献密点
                map.put("driverDaysMonth", result.get("driverDays")); //当月驾驶天数
                map.put("driverGrade", result.get("driverGrade")); //驾驶评分
                map.put("tweetCount", result.get("tweetCount")); //吐槽数
                map.put("taskIndex", result.get("taskIndex")); //任务指数
                map.put("environmentalIndex", result.get("environmentalIndex")); //环保指数
                //星级显示
                map.put("addMileageSumStar", RankingStarsTool.getRuleTextWithRankType(1, result.get("addMileageSum") == null ? -1 : (Integer) result.get("addMileageSum")));//新增里程
                map.put("mileageCostTimeStar", RankingStarsTool.getRuleTextWithRankType(2, Integer.parseInt(result.get("mileageCostTime") == null ? "-1" : result.get("mileageCostTime").toString()))); //达标用时
                map.put("mePointStar", RankingStarsTool.getRuleTextWithRankType(3, (int) Double.parseDouble(result.get("mePoint") == null ? "-1" : result.get("mePoint").toString()))); //捐献密点
                map.put("driverDaysMonthStar", RankingStarsTool.getRuleTextWithRankType(4, Integer.parseInt(result.get("driverDays") == null ? "-1" : result.get("driverDays").toString()))); //当月驾驶天数
                map.put("driverGradeStar", RankingStarsTool.getRuleTextWithRankType(5, Integer.parseInt(result.get("driverGrade") == null ? "-1" : result.get("driverGrade").toString()))); //驾驶评分
                map.put("tweetCountStar", result.get("tweetCount")); //吐槽数
                map.put("taskIndexStar", RankingStarsTool.getRuleTextWithRankType(7, Integer.parseInt(result.get("taskIndex") == null ? "-1" : result.get("taskIndex").toString()))); //任务指数
                map.put("environmentalIndexStar", RankingStarsTool.getRuleTextWithRankType(8, Integer.parseInt(result.get("environmentalIndex") == null ? "-1" : result.get("environmentalIndex").toString()))); //环保指数

                //总驾驶信息
                map.put("driverCityNum", result.get("cityNum")); //驾驶城市数
                map.put("driverHotNum", result.get("hotNum")); //驾驶热点数
                map.put("driverLocusNum", result.get("locusNum")); //轨迹数
                map.put("driverDays", result.get("days")); //驾驶天数
                map.put("driverMileageSum", result.get("mileageSum")); //驾驶总里程数据


                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(map);
            } else {
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("暂无数据");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return jsonResult;
    }

    /**
     * 获取榜单数据
     *
     * @param appKey    平台类型 ios  android
     * @param accountID 用户的accountID
     * @return customType 1,取出好友排序  2,取出频道排序  3，好友+频道
     */
    //@RequestMapping("/getRankInfo")
    @RequestMapping("/getUserRankInfo")
    @ResponseBody
    public CommonJsonResult getList(@RequestParam String appKey,
                                    @RequestParam String accountID,
                                    @RequestParam int rankType) {
        CommonJsonResult result = new CommonJsonResult();
        int customType = 3;
        List<Map<String, Object>> lis = new ArrayList<Map<String, Object>>();
        try{
            lis = UserRankInfoService.getRankMember(accountID, customType, rankType,appKey,getSecretchannelRelationByUserkey);

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("rank", lis == null ? new ArrayList<Map<String, Object>>() : lis);
            Map<String, Object> resultMap1 = UserRankInfoService.getRankMyselfInfo(accountID, rankType);
            //itemValue
    //        resultMap1.put("star",RankingStarsTool.getRuleTextWithRankType(rankType,Integer.parseInt(resultMap1.get("itemValue").toString())));
            resultMap.put("myRankInfo", resultMap1 == null ? "" : resultMap1);
            resultMap.put("rankRuleText",
                    RankingStarsTool.getRuleTextWithRankTypeTitle(rankType) + "|" + RankingStarsTool.getRuleTextWithRankType(rankType));
            result.setRESULT(resultMap);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        }catch (Exception e){
            logger.error("获取好友列表失败",e);
            result.setRESULT("获取用户好友列表失败");
            result.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            e.printStackTrace();
        }
        return result;

    }
//    @RequestMapping("/getMileageRank")
//    @ResponseBody
//    public CommonJsonResult getMileageRank(@RequestParam String appKey,
//                                    @RequestParam String accountID,
//                                    @RequestParam int customType) {
//        CommonJsonResult result = new CommonJsonResult();
//        List<Map<String,Object>> lis =   UserRankInfoService.getMileageSumRankList(accountID,customType);
//        result.setRESULT(lis);
//        result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//        return result;
//
//    }

    @RequestMapping("/getRankListInfoByShellAll")
    @ResponseBody
    public CommonJsonResult getRankListInfoByShellAll(@RequestParam String appKey,
                                                      @RequestParam String accountID) {
        CommonJsonResult result = new CommonJsonResult();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("rank", UserRankInfoService.getRankListInfoByShellAll(accountID, appKey, getSecretchannelRelationByUserkey));
            resultMap.put("myRankInfo", UserRankInfoService.getRankMyselfInfoByShellAll(accountID));
            result.setRESULT(resultMap);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        }catch (Exception e) {
            logger.error("获取全部排行失败",e);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            result.setRESULT(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
        }

        return result;

    }

    @RequestMapping("/getRankListInfoByShell")
    @ResponseBody
    public CommonJsonResult getRankListInfoByShell(@RequestParam String appKey,
                                                   @RequestParam String accountID) {
        CommonJsonResult result = new CommonJsonResult();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("rank", UserRankInfoService.getRankListInfoByShell(accountID,appKey,getSecretchannelRelationByUserkey));
            resultMap.put("myRankInfo", UserRankInfoService.getRankMyselfInfoByShell(accountID));
            result.setRESULT(resultMap);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        } catch (Exception e) {
            logger.error("获取个人本月排行失败",e);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            result.setRESULT(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
        }

        return result;

    }

    /**
     * @param appKey
     * @param accountID
     * @param taskInfoType 1.当日任务 2 当周任务 3当月任务 4 成就任务
     * @return
     */
    @RequestMapping("/getUserTaskInfo")
    @ResponseBody
    public CommonJsonResult getUserTaskInfo(@RequestParam String appKey,
                                            @RequestParam String accountID,
                                            @RequestParam int taskInfoType) {
        CommonJsonResult result = new CommonJsonResult();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> map = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> taskInfo = null;
        long resultInfo = 0;
        String viewInfo = "";
         try{

        if (taskInfoType == 1) {
            Long dis = drivingRedisService.getHRuleValue(RedisVariable.powerDay.POWEROFF_SEVEN_DAY, accountID);
            resultInfo = dis %  1000;
            Calendar cd = Calendar.getInstance();
            //1970-01-01到今天的总天数
            long allDays = AbDateUtil.getAllDays();
            long restDays = dis / 1000;
            if( ((allDays - restDays)+1) != resultInfo ){
                resultInfo = 0 ;
            }
            viewInfo = "您已连续驾驶" + resultInfo + "天";
            map = userRochelleRuleService.queryRochellReceiveStatus(accountID, taskInfoType, format.format(cd.getTime()));
        }
        if (taskInfoType == 2) {
            Long dis = drivingRedisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, accountID);
            resultInfo = dis % 100000000;
            viewInfo = "您本周已累计驾驶" + (resultInfo / 1000) + "公里";
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            map = userRochelleRuleService.queryRochellReceiveStatus(accountID, taskInfoType, format.format(cal.getTime()));

        }
        if (taskInfoType == 3) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH, 1);
            Long dis = drivingRedisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, accountID);
            resultInfo = dis % 100000000;
            viewInfo = "您本月已累计驾驶" + (resultInfo / 1000) + "公里";
            map = userRochelleRuleService.queryRochellReceiveStatus(accountID, taskInfoType, format.format(c.getTime()));
        }
        if (taskInfoType == 4) {
            UserGrade ug =  userGradeService.queryByAccountID(accountID);
            viewInfo = "您已达到" + (ug==null?0:ug.getGrade()) + "等级";
            map = userRochelleRuleService.queryRochellReceiveStatus(accountID, taskInfoType, null);
        }
         }catch (Exception e){
             result.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
             result.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
             return result;
         }
        result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        resultMap.put("taskInfo", map);
        resultMap.put("resultInfo", viewInfo);
        result.setRESULT(resultMap);
        return result;
    }

    /**
     * 获取用户当前排名
     */
    @RequestMapping("/currentRanking")
    @ResponseBody
    public CommonJsonResult currentRanking(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            UserRankDetail userRankDetail = userRankDetailService.getCurrentRank(accountID);
            jsonResult.setRESULT(userRankDetail);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        } catch (Exception e) {
            logger.error("获取当前排名时发生异常" + e.toString());
            jsonResult.setRESULT(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取当前排名时异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 全国排名
     *
     * @param appKey
     * @param accountID
     * @return
     */
    @RequestMapping("/rankingAll")
    @ResponseBody
    public CommonJsonResult rankingAll(@RequestParam String accountID, String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<UserGrade> userGradeList = userGradeService.getAllRochelleRanking(DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setRESULT(userGradeList);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        } catch (Exception e) {
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取全国排名时出错");
            logger.error("获取全国排名时出错" + e.toString());
        }

        return jsonResult;
    }

    /**
     * 本月排名
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/rankingMonth")
    @ResponseBody
    public CommonJsonResult rankingMonth(@RequestParam String accountID, String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            String pattern = "yyyyMMdd";
            String firstDay = AbDateUtil.getFirstDayOfMonth(pattern);
            String lastDay = AbDateUtil.getLastDayOfMonth(pattern);
            DateFormat df = new SimpleDateFormat(pattern);
            long firstUnixTime = (df.parse(firstDay).getTime()) / 1000;
            long lastUnixTime = (df.parse(lastDay).getTime()) / 1000;
            List<UserGrade> userGradeList = userGradeService.getMonthRochelleRanking(firstUnixTime, lastUnixTime, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setRESULT(userGradeList);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        } catch (Exception e) {
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取本月排名时出错");
            logger.error("获取本月排名时出错" + e.toString());
        }
        return jsonResult;
    }


    /**
     * 达标用时排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/mileageCostTimeRankingDetail")
    @ResponseBody
    public CommonJsonResult mileageCostTimeRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getMileageCostTimeList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取当达标用时发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取当达标用时发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 新增里程排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/mileageSumRankingDetail")
    @ResponseBody
    public CommonJsonResult mileageSumRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getMileageSumList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取新增里程发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取新增里程发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 捐献密点排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/mePointRankingDetail")
    @ResponseBody
    public CommonJsonResult mePointRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getMePointList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取捐赠密点发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取捐赠密点发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 驾驶天数排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/driverDaysRankingDetail")
    @ResponseBody
    public CommonJsonResult driverDaysRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getDriverDaysList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取驾驶天数发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取驾驶天数发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 吐槽数排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/tweetCountRankingDetail")
    @ResponseBody
    public CommonJsonResult tweetCountRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getTweetCountList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取吐槽数发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取吐槽数发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 驾驶评分排名详情
     *
     * @param accountID
     * @param appKey
     * @return
     */
    @RequestMapping("/driverGradeRankingDetail")
    @ResponseBody
    public CommonJsonResult driverGradeRankingDetail(@RequestParam String accountID, @RequestParam String appKey) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            List<IndexRankingDetail> list = indexRankingDetailService.getDriverGradeList(accountID, DEFAULT_BEGAN, DEFAULT_FINISH);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(list);
        } catch (Exception e) {
            logger.error("获取驾驶评分发生异常" + e.toString());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("获取驾驶评分发生异常");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 添加好友
     *
     * @param accountID
     * @param friendAccountID
     * @return
     */
    @RequestMapping("/addFriend")
    @ResponseBody
    public CommonJsonResult addFriend(String msgContent, @RequestParam String accountID, String accountNickName,
                                      @RequestParam String friendAccountID, String friendNickName, Integer gender, String userArea) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            //添加好友
            if (StringUtils.isNotBlank(msgContent)) {
                Boolean flag = userFriendService.addFriend(accountID, friendAccountID, ConstantsUtil.NEED_VERIFY_OPINION);
                if (flag) {
                    Map<String, Object> params = new HashMap<String, Object>(4);
                    params.put("verifyMessage", msgContent==null?"":msgContent);
                    params.put("accountNickName", accountNickName==null?"":accountNickName);
                    params.put("gender",gender==null?"":gender);
                    params.put("userArea",userArea==null?"":userArea);
                    //推送消息
                    Integer messageID = userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, friendAccountID,  accountNickName + " 请求添加你为好友，快去看看吧！", ConstantsUtil.Push.ADD_FRIEND_TYPE, params, accountID);
                    params = new HashMap<String, Object>(6);
                    params.put("accountID",accountID==null?"":accountID);
                    params.put("accountNickName",accountNickName==null?"":accountNickName);
                    params.put("friendAccountID",friendAccountID==null?"":friendAccountID);
                    params.put("friendNickName",friendNickName==null?"":friendNickName);
                    params.put("gender",gender==null?"":gender);
                    params.put("messageID",messageID==null?"":messageID);
                    SendMessageUtil.sendToTerminalV2(sendPersonalWeibo, callBackAddFriend, params, friendAccountID, "好友申请，亲爱的" + friendNickName + "，" + accountNickName + "请求添加你为好友，验证消息为" + msgContent + "，马上按加键接受或者按加加键拒绝，也可以在离车后在手机上操作。");
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
                } else {
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                    jsonResult.setRESULT("好友已存在");
                }
            } else {
                boolean flag = userFriendService.addFriend(accountID, friendAccountID, ConstantsUtil.NO_NEED_VERIFY_OPINION);
                if (!flag) {
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                    jsonResult.setRESULT("好友已存在");
                } else {
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
                }
            }
        } catch (Exception e) {
            logger.error("添加好友失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("添加好友失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * weme 终端 添加好友 回调
     *
     * @return
     */
    @RequestMapping("/user/v1.0/callBackAddFriend")
    @ResponseBody
    public CommonJsonResult callBackAgreeAddFriend(HttpServletRequest request) throws Exception {
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
        String actionType = params.get("actionType");
        String accountID = params.get("accountID");
        String friendAccountID = params.get("friendAccountID");
        Integer gender = new Integer(params.get("gender"));
        String accountNickName = params.get("accountNickName");
        String friendNickName = params.get("friendNickName");
        String messageID = params.get("messageID");
        /**
         * actionType 操作类型
         动作类型:1-拨打电话1;2-拨打电话2;3-发送语音,4-语音命令,5-发送群组语音,6-yes回复,7-no回复,8-关机,9-路过签到,10-语音回复,需返回bizid
         目前回调只会用到 6 YES, 7 NO, 10回复
         */
        if ("6".equals(actionType)) {
            //同意添加好友
            userFriendService.agreeAddFriend(accountID, friendAccountID,new Integer(messageID));
            //推送消息
            String ta = "";
            if (1 == gender) {
                ta = "他";
            } else if (0 == gender) {
                ta = "她";
            } else {
                ta = "TA";
            }
            userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, friendAccountID,  accountNickName + " 同意了你的好友申请，快去和" + ta + "聊天吧！", ConstantsUtil.Push.AGREE_ADD_FRIEND_TYPE, null);

        } else if ("7".equals(actionType)) {
            userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, accountID,  friendNickName + " 拒绝了你的好友申请，去通讯录再看看其他好友吧！", ConstantsUtil.Push.DIS_AGREE_ADD_FRIEND_TYPE, null);
        }
        jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        return jsonResult;
    }

    /**
     * 同意添加好友
     *
     * @param accountID
     * @param friendAccountID
     * @param accountNickName
     * @return
     */
    @RequestMapping("/agreeAddFriend")
    @ResponseBody
    public CommonJsonResult agreeAddFriend(@RequestParam String accountNickName, @RequestParam String accountID,
                                           @RequestParam String friendAccountID, @RequestParam Integer gender,Integer messageID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            //同意添加好友
            Boolean flag = userFriendService.agreeAddFriend(accountID, friendAccountID, messageID);
            if(flag){
                //推送消息
                String ta = "";
                if (1 == gender) {
                    ta = "他";
                } else if (0 == gender) {
                    ta = "她";
                } else {
                    ta = "TA";
                }
                userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, friendAccountID,  accountNickName + " 同意了你的好友申请，快去和" + ta + "聊天吧！", ConstantsUtil.Push.AGREE_ADD_FRIEND_TYPE, null, accountID);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
            }else{
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT("好友已存在");
            }
        } catch (Exception e) {
            logger.error("同意添加好友失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("同意添加好友失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 不同意添加好友
     *
     * @param accountNickName
     * @param friendAccountID
     * @return
     */
    @RequestMapping("/disAgreeAddFriend")
    @ResponseBody
    public CommonJsonResult disAgreeAddFriend(@RequestParam String accountNickName, @RequestParam String friendAccountID, @RequestParam String accountID,Integer messageID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            //推送消息
            userService.pushMessage(ConstantsUtil.Push.PUSH_TITLE, friendAccountID,  accountNickName + " 拒绝了你的好友申请，去通讯录再看看其他好友吧！", ConstantsUtil.Push.DIS_AGREE_ADD_FRIEND_TYPE, null, accountID);
            userFriendService.disAgreeAddFriend(messageID);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("拒绝添加好友失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("拒绝添加好友失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询好友
     *
     * @param accountID
     * @return
     */
    @RequestMapping("/queryUserFriend")
    @ResponseBody
    public CommonJsonResult queryUserFriend(@RequestParam String accountID,Integer startPage,Integer pageCount) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
//            List<UserFriend> userFriendList = userFriendService.getAllFriends(accountID);
            if(startPage == null || "".equals(startPage)){
                startPage = 1;
            }
            if(pageCount == null || "".equals(pageCount)){
                pageCount = 15;
            }
            PageList userFriendPageList = userFriendService.pageQueryFriends(accountID,startPage,pageCount);
            List<UserFriend> userFriendList = userFriendPageList.getRecords();
            if (userFriendList != null && userFriendList.size() != 0) {
                List<String> friendAccountIDList = new ArrayList<String>();
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(friendAccountIDList.size());
                for (UserFriend userFriend : userFriendList) {
                    Map<String, Object> friendAccount = new HashMap<String, Object>();
                    if (accountID.equals(userFriend.getAccountID())) {
                        friendAccountIDList.add(userFriend.getFriendAccountID());
                        friendAccount.put("accountID", userFriend.getFriendAccountID());
                        list.add(friendAccount);
                    } else if (accountID.equals(userFriend.getFriendAccountID())) {
                        friendAccountIDList.add(userFriend.getAccountID());
                        friendAccount.put("accountID", userFriend.getAccountID());
                        list.add(friendAccount);
                    }
                }
                //查询用户等级中的头像
                List<UserDetailVo> userGradeList = userGradeService.queryUserFriendDetailList(friendAccountIDList);
                for (Map<String, Object> friendAccount : list) {
                    //添加用户头像
                    for (UserDetailVo userDetailVo : userGradeList) {
                        if (friendAccount.get("accountID").equals(userDetailVo.getAccountID())) {
                            friendAccount.put("userHeadName", userDetailVo.getUserHeadName() == null ? "" : userDetailVo.getUserHeadName());
                            friendAccount.put("nickName", userDetailVo.getNickName() == null ? "" : userDetailVo.getNickName());
                            friendAccount.put("gender", userDetailVo.getGender() == null ? "" : userDetailVo.getGender());
                            friendAccount.put("userArea", userDetailVo.getUserAreaCode() == null ? "" : MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode()) == null ? "" : MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode()));
                        }
                    }
                }
                userFriendPageList.setRecords(list);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(userFriendPageList);
            } else {
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT("无微密好友");
            }
        } catch (Exception e) {
            logger.error("查询好友失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("查询好友失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 删除好友
     *
     * @param accountID
     * @param friendAccountID
     * @return
     */
    @RequestMapping("/removeUserFriend")
    @ResponseBody
    public CommonJsonResult removeUserFriend(@RequestParam String accountID, @RequestParam String friendAccountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            userFriendService.removeUserFriend(accountID, friendAccountID);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("好友设置失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("好友设置失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 好友设置
     *
     * @param accountID
     * @param isAllowedOpinion       是否允许被添加好友 1:允许 0:不允许
     * @param isVerifyOpinion        被添加好友是否需要验证 1:是 0:否
     * @param isReceiveNotifyOpinion 是否接收好友上线通知 1:是 0:否
     * @return
     */
    @RequestMapping("/friendSetting")
    @ResponseBody
    public CommonJsonResult friendSetting(@RequestParam String accountID, Integer isAllowedOpinion, Integer isVerifyOpinion,
                                          Integer isReceiveNotifyOpinion) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            friendSettingService.updateFriendSetting(accountID, isAllowedOpinion, isVerifyOpinion, isReceiveNotifyOpinion);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("好友设置失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("好友设置失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询好友设置
     *
     * @param accountID
     * @return
     */
    @RequestMapping("/queryFriendSetting")
    @ResponseBody
    public CommonJsonResult queryFriendSetting(@RequestParam String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            FriendSetting friendSetting = friendSettingService.queryFriendSetting(accountID);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(friendSetting);
        } catch (Exception e) {
            logger.error("查询好友设置失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("查询好友设置失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 根据通讯录判断是否是微密用户
     *
     * @param mobiles
     * @return
     */
    @RequestMapping("/judgeIsWeMeAccount")
    @ResponseBody
    public CommonJsonResult judgeIsWeMeAccount(@RequestParam String appKey, @RequestParam String[] mobiles, @RequestParam String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(mobiles.length);
            List<String> friendAccountIDList = new ArrayList<String>();
            String mobileString = "";
            if( mobiles != null &&  mobiles.length > 0 ){
                for (String mobile : mobiles) {
                    mobile = mobile.trim();
                    if(mobile.length() != 11 && mobile.length() != 13){
                        continue;
                    }else if(mobile.length() == 13 && mobile.substring(2).startsWith("1") ){
                        mobile = mobile.substring(2);
                    }else if(mobile.length() == 11 && !mobile.startsWith("1")){
                        continue;
                    }
                    mobileString += "," + mobile;
                }
                mobileString = mobileString.substring(1);
                Map<String, String> param = new HashMap<String, String>(3);
                param.put("appKey", appkey);
                param.put("secret", secret);
                param.put("mobile", mobileString);
                //签名
                String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
                param.put("sign", sign);
                HttpRequester requester = new HttpRequester();
                HttpRespons respons = requester.sendPost(getAccountIDFromMobile, param);
                if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                    String content = respons.getContent();
                    CommonJsonResult j = (CommonJsonResult)JsonMapper.fromJson(content,CommonJsonResult.class);
                    Map resultMap = (Map) JsonMapper.fromJson(content, Map.class);

                    if ("0".equals(j.getERRORCODE())) {
                        List<Map> accounts = (List<Map>)j.getRESULT();
                        if(accounts != null && accounts.size() > 0){
                            for(Map a : accounts){
                                Map<String, Object> map = new HashMap<String, Object>(3);
                                map.put("mobile", a.get("mobile").toString());
                                map.put("isWeMeAccount", ConstantsUtil.IS_WEME_ACCOUNT);
                                String accountIDTemp = a.get("accountID").toString();
                                String nickNameTemp = a.get("nickname").toString();
                                if(accountIDTemp != null && !"".equals(accountIDTemp)){
                                    friendAccountIDList.add(accountIDTemp);
                                    map.put("accountID",accountIDTemp);
                                    map.put("nickName",nickNameTemp);
                                    list.add(map);
                                }
                            }
                        }

                    }
                }

                if (friendAccountIDList.size() != 0) {
                    //查询用户等级中的头像
                    List<UserDetailVo> userGradeList = userGradeService.queryUserFriendDetailList(friendAccountIDList);
                    //查询好友
                    List<UserFriend> userFriendList = userFriendService.getAllFriends(accountID);

                    for (Map<String, Object> friendAccount : list) {
                        //添加用户头像
                        for (UserDetailVo userDetailVo : userGradeList) {
                            if (friendAccount.get("accountID").equals(userDetailVo.getAccountID())) {
                                friendAccount.put("userHeadName", userDetailVo.getUserHeadName() == null ? "" : userDetailVo.getUserHeadName());
                                friendAccount.put("gender", userDetailVo.getGender() == null ? "" : userDetailVo.getGender());
                                friendAccount.put("isAllowedOpinion", userDetailVo.getIsAllowedOpinion());
                                friendAccount.put("isVerifyOpinion", userDetailVo.getIsVerifyOpinion());
                                friendAccount.put("userArea",userDetailVo.getUserAreaCode() == null ? "" : MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode())==null?"":MapServiceImpl.CITY_CODE.get(userDetailVo.getUserAreaCode()));
                            }
                        }
                        for (UserFriend userFriend : userFriendList) {
                            //用户是否为好友
                            if ( userFriend.getIsAgree() == 1 &&
                                    ((accountID.equals(userFriend.getAccountID()) && friendAccount.get("accountID").equals(userFriend.getFriendAccountID())) ||
                                            (accountID.equals(userFriend.getFriendAccountID()) && friendAccount.get("accountID").equals(userFriend.getAccountID())))) {
                                friendAccount.put("isFriend", 1);
                            }
                        }
                        if (friendAccount.get("isFriend") == null) {
                            friendAccount.put("isFriend", 0);
                        }
                    }

                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT(list);
                } else {
                    jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    jsonResult.setRESULT("无微密用户");
                }
            }else{
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT("通讯录无手机号");
            }
        } catch (Exception e) {
            logger.error("根据通讯录判断是否是微密用户失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("根据通讯录判断是否是微密用户失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 聊天推送
     *
     * @param accountID
     * @param friendAccountID
     * @param msgContent
     * @return
     */
    @RequestMapping("/talkPushMessage")
    @ResponseBody
    public CommonJsonResult talkPushMessage(@RequestParam String accountID, @RequestParam String friendAccountID, @RequestParam String accountNickName,
                                            @RequestParam String senderUserHeadName,
                                            @RequestParam String friendNickName, @RequestParam Integer gender,
                                            @RequestParam String msgContent) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            Map<String, Object> params = new HashMap<String, Object>(7);
            params.put("talkContent", msgContent);
            //推送消息
            String ta = "";
            if (1 == gender) {
                ta = "他";
            } else if (0 == gender) {
                ta = "她";
            } else {
                ta = "TA";
            }
            Long createTime = System.currentTimeMillis() / 1000;
            params.put("friendNickName", friendNickName);
            params.put("accountNickName", accountNickName);
            params.put("senderUserHeadName", senderUserHeadName);
            params.put("pushTime", createTime);
            params.put("accountID", accountID);
            params.put("ta", ta);
            String paramsJson = JsonMapper.toJson(params, false);
            //保存消息中心
            MessageCentre messageContre = new MessageCentre();
            messageContre.setIsValid(1);
            messageContre.setCreateTime(createTime);
            messageContre.setAccountID(friendAccountID);
            messageContre.setContent( friendNickName + "，" + accountNickName + " 给你留言了，快去回复" + ta + "吧！");
            messageContre.setMsgTitle("微密");
            messageContre.setParam(paramsJson);
            messageContre.setSenderAccountID(accountID);
            messageContre.setMessageType("talk");
            messageContre.setIsRead(0);
            userService.insert(messageContre);
            String messageContreJson = JsonMapper.toJson(messageContre, false);
            SendMessageUtil.pushMessage("talk", messageContreJson, friendAccountID);

            UserTalkRecord userTalkRecord = new UserTalkRecord();
            userTalkRecord.setIsValid(1);
            userTalkRecord.setAccountID(accountID);
            userTalkRecord.setFriendAccountID(friendAccountID);
            userTalkRecord.setTalkContent(msgContent);
            userTalkRecord.setCreateTime(createTime);
            userTalkRecordService.insert(userTalkRecord);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("聊天推送失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("聊天推送失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询消息中心
     *
     * @param accountID
     * @return
     */
    @RequestMapping("/queryMessageCentre")
    @ResponseBody
    public CommonJsonResult queryMessageCentre(@RequestParam String accountID, String messageType,
                                               @RequestParam Integer pageNo, @RequestParam Integer pageCount) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            PageList<MessageCentre> pushMessagePageList = userService.queryMessageCentre(accountID, messageType, pageNo, pageCount);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(pushMessagePageList);
        } catch (Exception e) {
            logger.error("查询消息中心失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("查询消息中心失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 查询最新消息条数
     *
     * @param accountID
     * @return
     */
    @RequestMapping("/countNewMessageCentre")
    @ResponseBody
    public CommonJsonResult countNewMessageCentre(@RequestParam String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            Map<String,Integer> map = new HashMap<String, Integer>(1);
            int count = userService.countNewMessageCentre(accountID);
            map.put("newMessageCount",count);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(map);
        } catch (Exception e) {
            logger.error("查询最新消息中心条数失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("查询最新消息中心条数失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 清空消息中心
     *
     * @param messageCentreIDs
     * @return
     */
    @RequestMapping("/removeMessageCentre")
    @ResponseBody
    public CommonJsonResult removeMessageCentre(  Integer[] messageCentreIDs,String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            userService.removeMessageCentre(messageCentreIDs,accountID);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("清空消息中心失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("清空消息中心失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 修改消息中心为已读
     *
     * @param messageID
     * @return
     */
    @RequestMapping("/updateMessageIsRead")
    @ResponseBody
    public CommonJsonResult updateMessageIsRead(@RequestParam Integer messageID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            userService.updateMessageIsRead(messageID);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("修改消息中心为已读失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("修改消息中心为已读失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 举报
     *
     * @param accountID
     * @param reportType
     * @param reportObject
     * @return
     */
    @RequestMapping("/insertReportInfo")
    @ResponseBody
    public CommonJsonResult insertReportInfo(@RequestParam String accountID, @RequestParam Integer reportType, @RequestParam String reportObject, @RequestParam String content) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        try {
            ReportInfo reportInfo = new ReportInfo();
            reportInfo.setIsValid(1);
            reportInfo.setContent(content);
            reportInfo.setAccountID(accountID);
            reportInfo.setReportObject(reportObject);
            reportInfo.setReportType(reportType);
            reportInfo.setCreateTime(System.currentTimeMillis() / 1000);
            reportInfoService.insertReportInfo(reportInfo);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(ConstantsUtil.RESULT_OK);
        } catch (Exception e) {
            logger.error("举报失败", e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setFailure("举报失败");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * weme 终端 领取 谢尔值 回调
     *
     * @return
     */
    @RequestMapping("/user/v1.0/callBackReceiveRochelle")
    @ResponseBody
    public CommonJsonResult callBackRochelleTerminal(HttpServletRequest request) throws Exception {
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

        String actionType = params.get("actionType");
        //String accountID = params.get("accountID");
        String recordID = params.get("rewardID");
        /**
         * actionType 操作类型
         动作类型:1-拨打电话1;2-拨打电话2;3-发送语音,4-语音命令,5-发送群组语音,6-yes回复,7-no回复,8-关机,9-路过签到,10-语音回复,需返回bizid
         目前回调只会用到 6 YES, 7 NO, 10回复
         */
        if (actionType.equals("6")) {
            UserRochelleDetail userRochelleDetail = userRochelleDetailService.getUserRochelleDetailByRecordID(Integer.valueOf(recordID));
            if (userRochelleDetail != null && userRochelleDetail.getReceiveStatus() == 0) {
                Calendar cal = Calendar.getInstance();

                if (AbDateUtil.getOffectHour(cal.getTimeInMillis(), userRochelleDetail.getCreateDate().getTime()) <= 24) {

                    Object[] objects = userGradeService.updateUserGrade(userRochelleDetail.getAccountID(), "", userRochelleDetail.getRochelle(), Integer.valueOf(recordID));
                    //达到等级再奖励
                    if (objects != null) {
                        //推送消息
                        // SendMessageUtil.sendToMobile(userRochelleDetail.getAccountID(),"领取红包通知","");
                        SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) objects[2], userRochelleDetail.getAccountID(), objects[3] == null ? "" : objects[3].toString());
                    }
                }

            }
        }
        jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        jsonResult.setRESULT("处理成功");
        return jsonResult;
    }

    /**
     * 配置榜单页
     *
     * @return
     */
    @RequestMapping("/rankCofig")
    @ResponseBody
    public CommonJsonResult rankCofig(HttpServletRequest request) {
        CommonJsonResult result = new CommonJsonResult();
        result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        result.setRESULT(ConstantsUtil.rankCofig);
        return result;

    }
//    /**
//     * 测试推送任务
//     *
//     * @return
//     */
//    @RequestMapping("/testPushReward")
//    @ResponseBody
//    public CommonJsonResult testPushReward(HttpServletRequest request) {
//        CommonJsonResult result = new CommonJsonResult();
//        Map<String,Object> param = new HashMap<String,Object>();
//        //推送到手机
//        param.put("rewardID", 27849802);
//        param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get("drive300KmMonth"));
//        try {
//            userService.pushMessage("任务奖励", "kxl1QuHKCD", "你已完成今日行驶三千公里的任务，快去领取谢尔奖励吧，么么哒！", "rochelleReward", param);
//        }catch (Exception e){
//
//        }
//
//        result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//        result.setRESULT(ConstantsUtil.RESULT_OK);
//        return result;
//
//    }
    /**
     * 手机端领取谢尔值
     *
     * @param accountID 帐户ID
     * @param rewardID
     * @return
     */
    @RequestMapping("/user/v1.0/mobileRewardRochelle")
    @ResponseBody
    public CommonJsonResult mobileRewardRochelle(@RequestParam String accountID, @RequestParam String rewardID) throws Exception {
        CommonJsonResult commonJsonResult = new CommonJsonResult();

        UserRochelleDetail userRochelleDetail = userRochelleDetailService.getUserRochelleDetailByRecordID(Integer.valueOf(rewardID));
        try {
                if (userRochelleDetail != null && userRochelleDetail.getReceiveStatus() == 0) {
                    Calendar cal = Calendar.getInstance();

                    Object[] objects = userGradeService.updateUserGrade(accountID, "", userRochelleDetail.getRochelle(), Integer.valueOf(rewardID));
                    //达到等级再奖励
                    if (objects != null) {
                        //推送消息
                        // SendMessageUtil.sendToMobile(userRochelleDetail.getAccountID(),"领取红包通知","");
                        SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) objects[2], userRochelleDetail.getAccountID(), objects[3] == null ? "" : objects[3].toString());
                    }
                    commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    commonJsonResult.setRESULT("领取成功");
                } else {
                    commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                    commonJsonResult.setRESULT("领取失败");
                }
        }catch(Exception e){
            logger.error("领取谢尔值异常");
            commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            commonJsonResult.setRESULT("领取谢尔值异常");
        }
        return commonJsonResult;
    }

    /**
     *绑定手机号
     */
    @ResponseBody
    @RequestMapping("/userBindMobile")
    public CommonJsonResult userBindMobile(@RequestParam String appKey,  String mobile,
                                                      String accountID, @RequestParam String newmobile,String validateCode) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            int codeInDBCount = userService.findValidCode(validateCode,newmobile);
            if(codeInDBCount < 1){
                throw new Exception("1");
            }
            Map<String, String> param = new HashMap<String, String>(5);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("mobile", mobile==null?"":mobile);
            param.put("accountID", accountID==null?"":accountID);
            param.put("newmobile", newmobile);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(userBindMobile, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            if("1".equals(e.getMessage())){
                logger.error("验证码错误",e);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_VERICODE);
                jsonResult.setRESULT(ConstantsUtil.RESULT_VERICODE);
            }else{
                logger.error("绑定手机号失败",e);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("绑定手机号失败");
            }
        }
        return jsonResult;
    }
    /**
     *发送绑定验证信息
     */
    @ResponseBody
    @RequestMapping("/sendBindVerifyMessage")
    public CommonJsonResult sendBindVerifyMessage(@RequestParam String mobile) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String verifyCode = String.valueOf(RandomNumberUtil.genRandomNum(6));
        try {
            SendTemplateSMSUtil.sendTemplateSMS(mobile,"21109",verifyCode,"5");
            //验证码插入表中
            userService.insertIdentifyingCode(mobile,verifyCode,"3","5");
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT("发送绑定验证信息成功");
        } catch (Exception e) {
            logger.error("发送绑定验证信息失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("发送绑定验证信息失败");
        }
        return jsonResult;
    }

    /**
     *判断手机号
     * @param appKey
     * @param accountID
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkMobileRegister")
    public CommonJsonResult checkMobileRegister (@RequestParam String appKey,@RequestParam String accountID, String mobile) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            Map<String, String> param = new HashMap<String, String>(4);
            param.put("appKey", appkey);
            param.put("secret", secret);
            param.put("mobile", mobile);
            param.put("accountID", accountID);
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            HttpRespons respons = requester.sendPost(checkMobileRegister, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("判断手机号失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("判断手机号失败");
        }
        return jsonResult;
    }
    /**
     * 执行推送开关
     *
     * @return
     */
//    @RequestMapping("/user/exceutePush")
//    @ResponseBody
//    public CommonJsonResult exceutePush() {
//        CommonJsonResult commonJsonResult = new CommonJsonResult();
//        try {
//            //创建新线程运行接收消息
//            final ExecutorService threadPool_thread = Executors.newFixedThreadPool(4);
//            for (int i = 0; i < 4; i++) {
//                threadPool_thread.execute(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                while (true) {
//                                    try {
//                                        //获取消息队列信息（订单ID）
//                                        final String message = receiveRochelleMessage.receive();
//                                        try {
//                                            RuleRewardMq ruleRewardMq = (RuleRewardMq) me.daoke.common.mq.util.JsonMapper.fromJson(message, RuleRewardMq.class);
//                                            logger.info("ruleRewardMq:"+ ruleRewardMq);
//                                            // System.out.println("ruleRewardMq: " + ruleRewardMq);
//                                            //预奖励
//                                            Object[] obj = userGradeService.toReward(ruleRewardMq.getAccountID(), ruleRewardMq.getIMEI(), ruleRewardMq.getRuleCode());
//                                            logger.info("预奖励:"+ obj.toString());
//                                            UserGrade userGrade = userGradeService.queryByAccountID(ruleRewardMq.getAccountID());
//                                            String terminalText = "";
//                                            if (userGrade != null && StringUtils.isNotBlank(userGrade.getNickName()) && obj != null && obj[3] != null) {
//                                                terminalText = obj[3].toString().replace("昵称",userGrade.getNickName());
//                                                //terminalText = "任务奖励," + String.format(obj[3].toString(), userGrade.getNickName(), obj[1]);
//                                            } else {
//                                                // 取IMEI后四位
//                                                if(StringUtils.isNotBlank(ruleRewardMq.getIMEI())){
//                                                    terminalText = obj[3].toString().replace("昵称","道客"+ruleRewardMq.getIMEI().substring(ruleRewardMq.getIMEI().length() -4 ,ruleRewardMq.getIMEI().length()));
//                                                } else{
//                                                    terminalText = obj[3].toString().replace("昵称","");
//                                                }
//
//                                            }
//
//                                            //记录到消息表中
//
//                                            Map<String,Object> param = new HashMap<String,Object>();
//                                            //推送到手机
//
//                                            param.put("rewardID", (Integer) obj[2]);
//                                            param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get(ruleRewardMq.getRuleCode()));
//                                            userService.pushMessage("任务奖励", ruleRewardMq.getAccountID(), obj[4].toString(), "rochelleReward", param);
//
//
//                                            //推送消息
//                                            SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], ruleRewardMq.getAccountID(), terminalText);
//
//
//                                        } catch (Exception e) {
//                                            logger.error("接收消息 处理异常", e);
//                                            e.printStackTrace();
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                        logger.error("ExceptionMessage: {" + e.getMessage() + "}");
//                                        logger.error("StackTrace: ", e);
//                                    }
//                                }
//                            }
//                        });
//            }
//            threadPool_thread.shutdown();
//            commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//            commonJsonResult.setRESULT("执行成功");
//        } catch (Exception e) {
//            commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
//            commonJsonResult.setRESULT("执行失败");
//            e.printStackTrace();
//        }
//
//        return commonJsonResult;
//    }
}
