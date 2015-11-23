package com.daoke.mobileserver.sina.controller;

import com.daoke.mobileserver.sina.service.SinaService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:15:24
 * @version 1.0
 */
@Controller
public class SinaController {

	private final Logger logger = Logger.getLogger(SinaController.class);

	@Value("#{apiConfig[sinaSaveOauth]}")
	private String sinaSaveOauth;

	@Value("#{apiConfig[sinaCheckOauth]}")
	private String sinaCheckOauth;

	@Autowired
	private SinaService sinaService;

	/**
	 * 保存用户新浪授权文档
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param sinaWeiboID
	 * @param accessToken
	 * @param accessTokenExpiration
	 * @param appInfo
	 * @param sinaAppKey
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sinaSaveOauth")
	public String sinaSaveOauth(String appKey, String accountID, String sinaWeiboID,
			String accessToken, String accessTokenExpiration, String appInfo, String sinaAppKey,
			String nickname) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sinaService.sinaSaveOauth(appkey, secret, accountID, sinaWeiboID, accessToken,
					accessTokenExpiration, appInfo, sinaAppKey, nickname, sinaSaveOauth);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

	/**
	 * 检验用户新浪授权文档
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param sinaAppKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sinaCheckOauth")
	public String sinaCheckOauth(String appKey, String accountID, String sinaAppKey) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sinaService.sinaCheckOauth(appkey, secret, accountID, sinaAppKey,
					sinaCheckOauth);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

}
