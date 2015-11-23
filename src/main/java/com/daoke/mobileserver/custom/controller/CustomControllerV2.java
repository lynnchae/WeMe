package com.daoke.mobileserver.custom.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.custom.service.CustomService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 
 * @author chenmaomao
 * @date 2015-05-25 上午10:14:46
 * @version 1.0
 */
@Controller
public class CustomControllerV2 {

	private final Logger logger = Logger.getLogger(CustomControllerV2.class);

	@Value("#{apiConfig[getUserkeyInfo]}")
	private String getUserkeyInfo;

	@Value("#{apiConfig[setUserkeyInfo]}")
	private String setUserkeyInfo;

	@Value("#{apiConfig[getSubscribeMsg]}")
	private String getSubscribeMsg;

	@Value("#{apiConfig[setSubscribeMsg]}")
	private String setSubscribeMsg;

	@Value("#{apiConfig[followMicroChannel]}")
	private String followMicroChannel;

	@Value("#{apiConfig[fetchFollowListUserChannel]}")
	private String fetchFollowListUserChannel;

	@Value("#{apiConfig[getMicroChannelInfo]}")
	private String getMicroChannelInfo;

	@Value("#{apiConfig[getUserFollowListMicroChannel]}")
	private String getUserFollowListMicroChannel;

	@Value("#{apiConfig[getBossFollowListMicroChannel]}")
	private String getBossFollowListMicroChannel;

	@Value("#{apiConfig[resetInviteUniqueCode]}")
	private String resetInviteUniqueCode;

	@Value("#{apiConfig[getCatalogInfo]}")
	private String getCatalogInfo;
	
	@Value("#{apiConfig[getRecheckMicroChannelList]}")
	private String getRecheckMicroChannelList;
	
	@Value("#{apiConfig[modifyMicroChannel]}")
	private String modifyMicroChannel;

	@Value("#{apiConfig[saveFile]}")
	private String saveFile;
	
	@Autowired
	private CustomService customService;

	/**
	 * 用户获取按键设置
	 * 
	 * @param appKey
	 * @param accountID
	 * @param actionType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserkeyInfo")
	public CommonJsonResult getUserkeyInfo(@RequestParam String appKey, @RequestParam String accountID, Integer actionType) {
		CommonJsonResult jsonResult = new CommonJsonResult();
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			Map<String, String> param = new HashMap<String, String>(4);
			param.put("appKey", appkey);
			param.put("secret",secret);
			param.put("accountID", accountID);
			param.put("actionType", actionType==null?"":actionType+"");
			//签名
			String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
			param.put("sign", sign);
			HttpRequester requester = new HttpRequester();
			HttpRespons respons = requester.sendPost(getUserkeyInfo, param);
			if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
				String content = respons.getContent();
				jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
			}
		}catch (Exception e){
			logger.error("获取按键设置失败",e);
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
			jsonResult.setRESULT("获取按键设置失败");
		}
		return jsonResult;
	}

	/**
	 * 用户设置键
	 * 
	 * @param appKey
	 * @param accountID
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setUserkeyInfo")
	public CommonJsonResult setUserkeyInfo(@RequestParam String appKey,@RequestParam String accountID,@RequestParam String parameter) {
		CommonJsonResult jsonResult = new CommonJsonResult();
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			Map<String, String> param = new HashMap<String, String>(4);
			param.put("appKey", appkey);
			param.put("secret",secret);
			param.put("accountID", accountID);
			param.put("parameter", parameter);
			//签名
			String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
			param.put("sign", sign);
			HttpRequester requester = new HttpRequester();
			HttpRespons respons = requester.sendPost(setUserkeyInfo, param);
			if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
				String content = respons.getContent();
				jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
			}
		}catch (Exception e){
			logger.error("用户设置失败",e);
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
			jsonResult.setRESULT("用户设置失败");
		}
		return jsonResult;
	}

	/**
	 * 用户设置键
	 *
	 * @param appKey
	 * @param accountID
	 * @param actionType 按键类型
	 * @param customType 自定义类型
	 * @param customParameter 自定义参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setOnlyOneUserkeyInfo")
	public CommonJsonResult setOnlyOneUserkeyInfo(@RequestParam String appKey,@RequestParam String accountID,@RequestParam String actionType,
												  @RequestParam String customType,@RequestParam String customParameter) {
		CommonJsonResult jsonResult = new CommonJsonResult();
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			Map<String, String> param = new HashMap<String, String>(4);
			param.put("appKey", appkey);
			param.put("secret",secret);
			param.put("accountID", accountID);
			StringBuffer sb = new StringBuffer("{\"count\":\"1\",\n \"list\": [{\t\"actionType\":");
			sb.append("\"").append(actionType).append("\"").append(",\"customType\":").append("\"").append(customType).append("\"")
					.append(",\n \t\t\t\"customParameter\":").append("\"").append(customParameter).append("\"").append("\n \t\t}]}");
			param.put("parameter", sb.toString());
			//签名
			String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
			param.put("sign", sign);
			HttpRequester requester = new HttpRequester();
			HttpRespons respons = requester.sendPost(setUserkeyInfo, param);
			if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
				String content = respons.getContent();
				jsonResult = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
			}
		}catch (Exception e){
			logger.error("用户设置失败",e);
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
			jsonResult.setRESULT("用户设置失败");
		}
		return jsonResult;
	}


	
}
