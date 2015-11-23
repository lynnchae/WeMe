package com.daoke.mobileserver.sharepoint.controller;

import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.sharepoint.service.SharePointService;
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
 * @date 2014-10-22 下午5:10:50
 * @version 1.0
 */
@Controller
public class SharePointController {

	private final Logger logger = Logger.getLogger(SharePointController.class);

	@Value("#{apiConfig[getGrowthInfo]}")
	private String getGrowthInfo;

	@Value("#{apiConfig[getGrowthDetail]}")
	private String getGrowthDetail;

	@Value("#{apiConfig[getShareValue]}")
	private String getShareValue;

	@Value("#{apiConfig[getTaskList]}")
	private String getTaskList;

	@Autowired
	private SharePointService sharePointService;

	/**
	 * 获取某用户的成长信息
	 * 
	 * @param appKey
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGrowthInfo")
	public String getGrowthInfo(String appKey, String accountID, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sharePointService.getGrowthInfo(appkey, secret, accountID, getGrowthInfo);
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
	 * 获取某用户的成长明细
	 * 
	 * @param appKey
	 * @param accountID
	 * @param startPage
	 * @param pageCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGrowthDetail")
	public String getGrowthDetail(String appKey, String accountID, String startPage,
			String pageCount, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sharePointService.getGrowthDetail(appkey, secret, accountID, startPage,
					pageCount, getGrowthDetail);
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
	 * 用户领取某任务的谢尔值
	 * 
	 * @param appKey
	 * @param uniqueCode
	 * @param taskID
	 * @param obatainType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShareValue")
	public String getShareValue(String appKey, String accountID, String uniqueCode, String taskID,
			String obtainType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sharePointService.getShareValue(appkey, secret, accountID, uniqueCode, taskID,
					obtainType, getShareValue);
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
	 * 获取某用户的任务列表以及完成情况
	 * 
	 * @param appKey
	 * @param accountID
	 * @param taskType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTaskList")
	public String getTaskList(String appKey, String accountID, String taskType,
			HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = sharePointService
					.getTaskList(appkey, secret, accountID, taskType, getTaskList);
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
}
