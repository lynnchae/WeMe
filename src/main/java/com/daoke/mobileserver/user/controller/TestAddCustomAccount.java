package com.daoke.mobileserver.user.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.map.service.impl.MapServiceImpl;
import com.daoke.mobileserver.user.entity.UserGrade;
import com.daoke.mobileserver.user.service.IUserGradeService;
import com.daoke.mobileserver.user.service.IUserResetPasswordService;
import com.daoke.mobileserver.user.service.IUserRochelleDetailService;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonMapper;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyaqin on 2015/5/26.
 */

@Controller
public class TestAddCustomAccount {
    private final Logger logger = Logger.getLogger(UserController.class);
    private Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    private UserService userService;
    @Autowired
    private IUserRochelleDetailService userRochelleDetailService;
    @Autowired
    private IUserGradeService userGradeService;
    @Autowired
    private IUserResetPasswordService userResetPasswordService;//用户重置密码

    //接口调用：添加用户自定义账号
    @Value("#{apiConfig[addCustomAccount]}")
    private String addCustomAccount;
    //接口调用：获取用户信息
    @Value("#{apiConfig[getUserInfo]}")
    private String getUserInfo;

    /**
     * 功能：添加用户自定义账号
     * @param appKey  应用标识
     * @param daokePassword 密码
     * @param nickname 用户的昵称
     * @param mobile 手机号码
     * @param verificationCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/testAddCustomAccount")
    public CommonJsonResult addCustomAccount(String appKey, String daokePassword, String nickname,
                                   String mobile, String verificationCode) {
        String result = null;
        CommonJsonResult jsonResult = new CommonJsonResult();;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            if (StringUtils.isNotEmpty(verificationCode)
                    && verificationCode.equals(map.get(mobile))) {
                result = userService.addCustomAccount(appkey, secret, daokePassword, "2", nickname,
                        mobile, addCustomAccount);
                JSONObject jsonObject = JSONObject.fromObject(result);
                String ERRORCODE = jsonObject.getString("ERRORCODE");
                if (("0").equals(ERRORCODE)) {
                    logger.info(appKey + "===>" + result);
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

        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/testGetUserInfo")
    public CommonJsonResult getUserInfo(String appKey, String accountID, HttpServletResponse response) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        CommonJsonResult jsonResult = null;
        try {
            result = userService.getUserInfo(appkey, secret, accountID, getUserInfo);

            jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
//                    response.getOutputStream().write(result.getBytes("utf-8"));
            if(jsonResult.getERRORCODE().equals("0"))  {
                Map<String,String> map = (Map<String,String>)jsonResult.getRESULT();
                UserGrade ug =  userGradeService.queryByAccountID(accountID);
                if (ug!=null){
                    map.put("userHeadName",ug.getUserHeadName());
                    map.put("userAreaCode", MapServiceImpl.CITY_CODE.get(ug.getUserAreaCode()));
                    map.put("gender",ug.getGender()+"");
                    jsonResult.setRESULT(map);
                    return  jsonResult;
                }
            } else {
                return  jsonResult;
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

}


