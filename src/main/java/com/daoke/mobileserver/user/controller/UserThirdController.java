package com.daoke.mobileserver.user.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.common.service.RedisService;
import com.daoke.mobileserver.reward.service.RewardService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;


/**
 * @author wangliming
 * @version 1.0
 * @date 2014-9-26 上午10:19:43
 */
@Controller
public class UserThirdController {

    private final Logger logger = Logger.getLogger(UserThirdController.class);

    @Value("#{apiConfig[userBindThirdAccountMirrtalk]}")
    private String userBindThirdAccountMirrtalk;

    @Value("#{apiConfig[addThirdCustomAccount]}")
    private String addThirdCustomAccount;

    @Value("#{apiConfig[createThirdAccountID]}")
    private String createThirdAccountID;

    @Value("#{apiConfig[associateDeviceIDWithImei]}")
    private String associateDeviceIDWithImei;

    @Autowired
    private UserService userService;

    /**
     * 取redis数据
     */
    @Autowired
    private RedisService redisService;


    @Autowired
    private RewardService rewardService;


    @Value("#{apiConfig[payThirdUserDeposit]}")
    private String payThirdUserDeposit;


    @Value("#{apiConfig[thirdAppKeylimit]}")
    private String thirdAppKeylimit;


    /**
     * 验证appkey
     * @param appKey
     * @return
     */
    private boolean verificationThirdAppKey(String appKey){
            if(StringUtils.isNotBlank(thirdAppKeylimit)){
               for(String thirdAppKey : thirdAppKeylimit.split(",")){
                    if(appKey.equals(thirdAppKey)){
                       return true;
                    }
               }
            }
        return false;
    }


	/**
	 * 第三方用户绑定语镜账号和IMEI
	 * 
	 * @param appKey
	 * @param accountID
	 * @param IMEI
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userBindThirdAccountMirrtalk")
	public String userBindThirdAccountMirrtalk(String appKey, String accountID, String IMEI) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = userService.userBindThirdAccountMirrtalk(appkey, secret, accountID, IMEI,
					userBindThirdAccountMirrtalk);
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
     * 添加用户(第三方)自定义账号
     *
     * @param appKey
     * @param username
     * @param mobile
     * @param userEmail
     * @param daokePassword
     * @param accountType
     * @param nickname
     * @param gender
     * @param name
     * @param plateNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addThirdCustomAccount")
    public String addThirdCustomAccount(String appKey, @RequestParam(required = false) String username, @RequestParam(required = false) String mobile, @RequestParam(required = false) String userEmail, String daokePassword, String accountType, String nickname,
                                        @RequestParam(required = false) String gender, @RequestParam(required = false) String name, @RequestParam(required = false) String plateNumber) {
        String result = null;
       // String appkey = ConstantsUtil.getAppKey(appKey);
        //String secret = ConstantsUtil.getSecret(appKey);
        //根据appkey 从redids取 secret
        if(!verificationThirdAppKey(appKey)){
            return    result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        String secret = redisService.getSecret(appKey);
        try {
            if (StringUtils.isNotBlank(nickname)) {
                nickname = URLEncoder.encode(nickname, "utf-8");
            }
            if (StringUtils.isNotBlank(name)) {
                name = URLEncoder.encode(name, "utf-8");
            }
            if (StringUtils.isNotBlank(plateNumber)) {
                plateNumber = URLEncoder.encode(plateNumber, "utf-8");
            }
            result = userService.addThirdCustomAccount(appKey, secret, username, mobile, userEmail, daokePassword, accountType, nickname, gender,
                    name, plateNumber, addThirdCustomAccount);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String ERRORCODE = jsonObject.getString("ERRORCODE");
            if (("0").equals(ERRORCODE)) {
                logger.info(appKey + "===>" + result);
            } else {
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
     * 根据第三方的账户（如微信、QQ等）创建语镜帐号
     *
     * @param appKey
     * @param account
     * @param loginType
     * @param nickname
     * @param token
     * @param accessToken
     * @param accessTokenExpiration
     * @param refreshToken
     * @return
     */
    @ResponseBody
    @RequestMapping("/createThirdAccountID")
    public String createThirdAccountID(String appKey, String account, String loginType, String nickname, @RequestParam(required = false) String token,
                                       @RequestParam(required = false) String accessToken, @RequestParam(required = false) String accessTokenExpiration, @RequestParam(required = false) String refreshToken,
                                       HttpServletResponse response) {
        String result = null;
       // String appkey = ConstantsUtil.getAppKey(appKey);
      //  String secret = ConstantsUtil.getSecret(appKey);



        if(!verificationThirdAppKey(appKey)){
            return    result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
        }
        //根据appkey 从redids取 secret
        String secret = redisService.getSecret(appKey);
        try {
            result = userService.createThirdAccountID(appKey, secret, account, loginType, nickname, token,
                    accessToken, accessTokenExpiration, refreshToken, createThirdAccountID);
            response.getOutputStream().write(result.getBytes("utf-8"));
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + e.getMessage());
        }
        return result;
    }

    /**
     * 将车机设备号与语境公司提供的imei进行关联
     *
     * @param appKey
     * @param deviceID
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/associateDeviceIDWithImei")
    public String associateDeviceIDWithImei(String appKey, String deviceID, String model, HttpServletResponse response) {
        String result = null;
        //根据appkey 从redids取 secret
        String secret = redisService.getSecret(appKey);
        try {
            result = userService.associateDeviceIDWithImei(appKey, secret, deviceID, model, associateDeviceIDWithImei);

            CommonJsonResult jsonResult = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
            String IMEI = "";
            String depositType = "";
            String depositAmount = "";
            if (jsonResult != null && jsonResult.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                Map<String, Object> map = (Map) jsonResult.getRESULT();
                IMEI = map.get("imei").toString();
                depositType = String.valueOf(map.get("depositType"));
                depositAmount = String.valueOf(map.get("depositAmount"));
                String depositResult = rewardService.payUserDeposit(appKey, secret, IMEI, depositType, depositAmount,
                        payThirdUserDeposit);
                if(logger.isInfoEnabled()){
                    logger.info("depositResult:" + depositResult);
                }
                CommonJsonResult jsonResult1 = (CommonJsonResult) JsonMapper.fromJson(result, CommonJsonResult.class);
                if (jsonResult1 != null && !jsonResult1.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
                    result = depositResult;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(appKey + "===>" + e.getMessage());
        }
        return result;
    }


    /**
     * 第三方用户缴纳押金
     *
     * @param appKey
     * @param IMEI
     * @param depositType
     * @param depositAmount
     * @return
     */

    @RequestMapping("/payThirdUserDeposit")
    @ResponseBody
    public String payThirdUserDeposit(String appKey, String IMEI, String depositType,
                                      String depositAmount) {
        String result = null;
        //根据appkey 从redids取 secret
        String secret = redisService.getSecret(appKey);
        try {
            result = rewardService.payUserDeposit(appKey, secret, IMEI, depositType, depositAmount,
                    payThirdUserDeposit);
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


}
