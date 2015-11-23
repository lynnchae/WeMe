package com.daoke.mobileserver.custom.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.custom.service.CustomService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonMapper;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * 
 * @author wangliming
 * @date 2014-10-29 上午10:14:46
 * @version 1.0
 */
@Controller
public class CustomController {

	private final Logger logger = Logger.getLogger(CustomController.class);

	@Value("#{apiConfig[getCustomInfo]}")
	private String getCustomInfo;

	@Value("#{apiConfig[setCustomInfo]}")
	private String setCustomInfo;

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
	 * 用户获取已经设置的参数
	 * 
	 * @param appKey
	 * @param accountID
	 * @param actionType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCustomInfo")
	public String getCustomInfo(String appKey, String accountID, String actionType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getCustomInfo(appkey, secret, accountID, actionType,
					getCustomInfo);
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
	 * 用户设置
	 * 
	 * @param appKey
	 * @param accountID
	 * @param actionType
	 * @param customType
	 * @param customParameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setCustomInfo")
	public String setCustomInfo(String appKey, String accountID, String actionType,
			String customType, String customParameter) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.setCustomInfo(appkey, secret, accountID, actionType, customType,
					customParameter, setCustomInfo);
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
	 * 获取接受微博类型
	 * 
	 * @param appKey
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSubscribeMsg")
	public String getSubscribeMsg(String appKey, String accountID, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getSubscribeMsg(appkey, secret, accountID, getSubscribeMsg);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							ConstantsUtil.RESULT_OK));
			result = null;
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
	 * 设置接受微博类型
	 * 
	 * @param appKey
	 * @param accountID
	 * @param subParameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setSubscribeMsg")
	public String setSubscribeMsg(String appKey, String accountID, String subParameter) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.setSubscribeMsg(appkey, secret, accountID, subParameter,
					setSubscribeMsg);
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
	 * 关注微频道API
	 * 
	 * @param appKey
	 * @param accountID
	 * @param uniqueCode
	 * @param followType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/followMicroChannel")
	public String followMicroChannel(String appKey, String accountID, String uniqueCode,
			String followType, String channelNumber) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.followMicroChannel(appkey, secret, accountID, uniqueCode,
					followType, channelNumber, followMicroChannel);
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
	 * 取消关注微频道API
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @param followType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unFollowUserChannel")
	public String unFollowUserChannel(String appKey, String accountID, String channelNumber,
			String followType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.unFollowUserChannel(appkey, secret, accountID, channelNumber,
					followType, followMicroChannel);
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
	 * 获取微频道关注的用户列表信息
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @param startPage
	 * @param pageCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fetchFollowListUserChannel")
	public String fetchFollowListUserChannel(String appKey, String accountID, String channelNumber,
			String startPage, String pageCount) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.fetchFollowListUserChannel(appkey, secret, accountID,
					channelNumber, startPage, pageCount, fetchFollowListUserChannel);
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
	 * 根据频道编码获取频道详情信息
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMicroChannelInfo")
	public String getMicroChannelInfo(String appKey, String accountID, String channelNumber,
			HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getMicroChannelInfo(appkey, secret, accountID, channelNumber,
					getMicroChannelInfo);
			response.getOutputStream().write(result.getBytes("utf-8"));
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
		return result;
	}

	/**
	 * 用户查询自己关注的所有微频道
	 * 
	 * @param appKey
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOwnerFollowListUserChannel")
	public String getOwnerFollowListUserChannel(String appKey, String accountID, String startPage,
			String pageCount, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getOwnerFollowListUserChannel(appkey, secret, accountID,
					startPage, pageCount, getUserFollowListMicroChannel);
			String ERRORCODE = JSONObject.fromObject(result).getString("ERRORCODE");
			if ("0".equals(ERRORCODE)) {
				logger.info(appKey
						+ "===>"
						+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
								ConstantsUtil.RESULT_OK));
			} else {
				logger.warn(appKey + "===>" + result);
			}
			response.getOutputStream().write(result.getBytes("utf-8"));
			result = null;
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
	 * 主播查询本频道所有关注用户列表
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBossFollowListMicroChannel")
	public String getBossFollowListMicroChannel(String appKey, String accountID,
			String channelNumber, String startPage, String pageCount, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getOwnerUserChannelFollowList(appkey, secret, accountID,
					channelNumber, startPage, pageCount, getBossFollowListMicroChannel);
			String ERRORCODE = JSONObject.fromObject(result).getString("ERRORCODE");
			if ("0".equals(ERRORCODE)) {
				logger.info(appKey
						+ "===>"
						+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
								ConstantsUtil.RESULT_OK));
			} else {
				logger.warn(appKey + "===>" + result);
			}
			response.getOutputStream().write(result.getBytes("utf-8"));
			result = null;
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
	 * 重置频道当前邀请的惟一码
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @param channelType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetInviteUniqueCode")
	public String resetInviteUniqueCode(String appKey, String accountID, String channelNumber,
			String channelType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.resetInviteUniqueCode(appkey, secret, accountID, channelNumber,
					channelType, resetInviteUniqueCode);
			logger.info(result);
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
	 * 获取微频道所有类别的列表
	 * 
	 * @param appKey
	 * @param startPage
	 * @param pageCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCatalogInfo")
	public String getCatalogInfo(String appKey, String startPage, String pageCount, HttpServletResponse response){
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getCatalogInfo(appkey, secret, startPage, pageCount, getCatalogInfo);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(result);
			result = null;
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
	 * 获取待重审的微频道列表
	 * 
	 * @param appKey
	 * @param startPage
	 * @param pageCount
	 * @return
	 */
	@RequestMapping("/getRecheckMicroChannelList")
	public @ResponseBody String  getRecheckMicroChannelList( HttpServletResponse response,String appKey,String accountID,
			String startPage,String pageCount){
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = customService.getRecheckMicroChannelList(appkey, secret, accountID,startPage, pageCount, getRecheckMicroChannelList);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(result);
			result = null;
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
	 * 修改微频道/被驳回的频道
	 * 
	 * @param appKey
	 * @param accountID
	 * @param beforeChannelNumber
	 * @param channelNumber
	 * @param channelName
	 * @param channelIntroduction
	 * @param chiefAnnouncerIntr
	 * @param channelCityCode
	 * @param channelCatalogID
	 * @param infoType
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifyMicroChannel")
	public @ResponseBody String modifyMicroChannel(String appKey,String accountID,String beforeChannelNumber,
			String channelNumber,String channelName,String channelIntroduction,String chiefAnnouncerIntr,
			String channelCityCode,String channelCatalogID,String infoType,String applyIdx, HttpServletRequest request,
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
	
		
		try {
            //调用api
			result = customService.modifyMicroChannel(appkey, secret, accountID,beforeChannelNumber, channelNumber,
					channelName,channelIntroduction,chiefAnnouncerIntr,channelCityCode,channelCatalogID,channelCatalogUrl,infoType,applyIdx,
					modifyMicroChannel);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(result);
			result = null;
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
