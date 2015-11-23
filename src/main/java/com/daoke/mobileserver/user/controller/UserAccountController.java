package com.daoke.mobileserver.user.controller;
//
//import com.daoke.mobileserver.common.model.CommonJsonResult;
//import com.daoke.mobileserver.util.*;
//import org.apache.log4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by huanghongyang on 2015/3/19.
// */
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.user.service.IUserResetPasswordService;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.GenerateCodeUtil;
import com.daoke.mobileserver.util.JsonMapper;
import com.daoke.mobileserver.util.SendTemplateSMSUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserAccountController {
    @Autowired
    UserService userService ;
    @Autowired
    private IUserResetPasswordService userResetPasswordService;//用户重置密码



    @Value("#{apiConfig[getImeiPhone]}")
    private String getImeiPhone;
    @Value("#{apiConfig[getAccountIDFromMobile]}")
    private String getAccountIDFromMobile;
    @Value("#{apiConfig[resetPasswordInitVerifyCode]}")//接口：用户获取密码重置验证码
    private String resetPasswordInitVerifyCode;
    @Value("#{apiConfig[resetPasswordCheckVerifyCode]}")//接口：手机用户根据验证码重置新密码
    private String resetPasswordCheckVerifyCode;



    /**
     * 用户获取密码重置验证码
     * @param appKey //应用标识
     * @param mobilePhone //手机号码
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendIdentifyingCode")
    public CommonJsonResult resetPasswordInitVerifyCode(String appKey, String mobilePhone) {
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);//安全签名
        try {
            String result =  userResetPasswordService.resetPasswordInitVerifyCode(appkey, secret, mobilePhone, resetPasswordInitVerifyCode);
            System.out.println(result);
            commonJsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
            if (commonJsonResult.getERRORCODE()!=null&&commonJsonResult.getERRORCODE().equals("0")){
                commonJsonResult.setERRORCODE(commonJsonResult.getERRORCODE());
                commonJsonResult.setRESULT(commonJsonResult.getRESULT());
                return  commonJsonResult;
            }else{
                commonJsonResult.setERRORCODE(commonJsonResult.getERRORCODE());
                commonJsonResult.setRESULT(commonJsonResult.getRESULT());
                return  commonJsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            commonJsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
            return  commonJsonResult;
        }
    }

    /**
     * 手机用户根据验证码重置新密码
     * @param appKey 应用标识
     * @param mobile 手机号码
     * @param verifyCode 验证码
     * @param newPassword 新密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPasswordCheckVerifyCode")
    public CommonJsonResult resetPasswordCheckVerifyCode(String appKey, String mobile,String verifyCode,String newPassword) {
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);//安全签名
        try {
            String result =  userResetPasswordService.resetPasswordCheckVerifyCode(appkey,secret,mobile,verifyCode,newPassword,resetPasswordCheckVerifyCode);
            System.out.println(result);
            commonJsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
            if (commonJsonResult.getERRORCODE()!=null&&commonJsonResult.getERRORCODE().equals("0")){
                commonJsonResult.setERRORCODE(commonJsonResult.getERRORCODE());
                commonJsonResult.setRESULT(commonJsonResult.getRESULT());
                return  commonJsonResult;
            }else{
                commonJsonResult.setERRORCODE(commonJsonResult.getERRORCODE());
                commonJsonResult.setRESULT(commonJsonResult.getRESULT());
                return  commonJsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonJsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            commonJsonResult.setRESULT(ConstantsUtil.RESULT_SERVICE_ERROR);
            return  commonJsonResult;
            }
    }
}