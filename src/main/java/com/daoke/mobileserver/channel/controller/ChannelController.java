package com.daoke.mobileserver.channel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.channel.service.ChannelService;
import com.daoke.mobileserver.util.ConstantsUtil;
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

import java.util.Iterator;


/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午7:27:02
 * @version 1.0
 */
@Controller
public class ChannelController {

	private final Logger logger = Logger.getLogger(ChannelController.class);

	@Value("#{apiConfig[applyMicroChannel]}")
	private String applyMicroChannel;

	@Value("#{apiConfig[saveFile]}")
	private String saveFile;

	@Value("#{apiConfig[fetchMicroChannel]}")
	private String fetchMicroChannel;

	@Value("#{apiConfig[inviteLinksUserChannel]}")
	private String inviteLinksUserChannel;

	@Autowired
	private AudioService audioService;

	@Autowired
	private ChannelService channelService;

	/**
	 * 用户通过APP申请微频道/密频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param channelName
	 * @param channelIntroduction
	 * @param channelCityCode
	 * @param channelCatalog
	 * @param channelType
	 * @param openType
	 * @param chiefAnnouncerIntr
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyMicroChannel")
	public String applyMicroChannel(HttpServletRequest request, String appKey, String accountID, String channelNumber,
			String channelName, String channelIntroduction, String chiefAnnouncerIntr,
			String channelCityCode, String channelCatalogID) {
		String result = null;
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
                result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,   "图片保存失败");
                return result;
            }
			if (file != null) {
				result = audioService.saveFile(appkey, secret, file, saveFile);
				JSONObject jsonObject = JSONObject.fromObject(result);
				String ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					String channelLogoURL = jsonObject.getJSONObject("RESULT").getString("url");
					result = channelService.applyMicroChannel(appkey, secret, accountID,
							channelNumber, channelName, channelIntroduction, chiefAnnouncerIntr,
							channelCityCode, channelCatalogID, channelLogoURL, applyMicroChannel);
					logger.info(appKey + "===>" + result);
				} else {
                    result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                            ConstantsUtil.RESULT_SERVICE_ERROR);
					logger.warn(appkey + "===>" + result);
				}
			} else {
				result = channelService.applyMicroChannel(appkey, secret, accountID, channelNumber,
						channelName, channelIntroduction, chiefAnnouncerIntr, channelCityCode,
						channelCatalogID, "", channelName);
				logger.info(appKey + "===>" + result);
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
     * 获取微频道/密频道列表
     *
     * @param appKey
     * @param accountID
     * @param startPage
     * @param channelNumber
     * @param pageCount
     * @param infoType
     * @param channelStatus
     * @param cityCode
     * @param catalogid
     * @param response
     * @return
     */
	@ResponseBody
	@RequestMapping("/fetchMicroChannel")
	public String fetchMicroChannel(String appKey, String accountID, String startPage,
			String channelNumber, String pageCount, String infoType, String channelStatus,
			String cityCode, String channelName, String catalogID, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			if ("1".equals(infoType)) {
				// 提交申请未审核的记录
				result = channelService.fetchMicroChannel(appkey, secret, accountID, startPage,
						pageCount, infoType, null, null, null, null, fetchMicroChannel);
				/*
				 * JSONObject jsonObject =
				 * JSONObject.fromObject(result).getJSONObject("RESULT"); int
				 * firstCount = jsonObject.getInt("count"); JSONArray jsonArray
				 * = jsonObject.getJSONArray("list"); List<UserChannel>
				 * firstUserChannels = (List<UserChannel>)
				 * JSONArray.toCollection( jsonArray, UserChannel.class);
				 * 
				 * // 提交申请驳回的记录 result =
				 * channelService.fetchMicroChannel(appkey, secret, accountID,
				 * startPage, pageCount, infoType, "1", fetchMicroChannel);
				 * jsonObject =
				 * JSONObject.fromObject(result).getJSONObject("RESULT"); int
				 * secondCount = jsonObject.getInt("count"); jsonArray =
				 * jsonObject.getJSONArray("list"); List<UserChannel>
				 * secondUserChannels = (List<UserChannel>)
				 * JSONArray.toCollection( jsonArray, UserChannel.class);
				 * 
				 * // 提交申请审核通过的记录(包含邀请码) result =
				 * channelService.fetchMicroChannel(appkey, secret, accountID,
				 * startPage, pageCount, infoType, "2", fetchMicroChannel);
				 * jsonObject =
				 * JSONObject.fromObject(result).getJSONObject("RESULT"); int
				 * thirdCount = jsonObject.getInt("count"); jsonArray =
				 * jsonObject.getJSONArray("list"); List<UserChannel>
				 * thirdUserChannels = (List<UserChannel>)
				 * JSONArray.toCollection( jsonArray, UserChannel.class);
				 * 
				 * List<UserChannel> userChannels = new
				 * ArrayList<UserChannel>();
				 * userChannels.addAll(firstUserChannels);
				 * userChannels.addAll(secondUserChannels);
				 * userChannels.addAll(thirdUserChannels); int count =
				 * firstCount + secondCount + thirdCount;
				 * jsonObject.put("count", String.valueOf(count));
				 * jsonObject.put("list", userChannels); result =
				 * JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
				 * jsonObject);
				 */
			} else if ("2".equals(infoType)) {
				result = channelService.fetchMicroChannel(appkey, secret, accountID, startPage,
						pageCount, infoType, channelStatus, cityCode, channelName, catalogID,
						fetchMicroChannel);
			} else {
				result = channelService.fetchMicroChannel(appkey, secret, accountID, startPage,
						pageCount, infoType, channelStatus, null, null, null, fetchMicroChannel);
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
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
	 * 创建/获取/删除 微频道/密频道的的邀请唯一码
	 * 
	 * @param appKey
	 * @param accountID
	 * @param channelNumber
	 * @param customType
	 * @param startPage
	 * @param pageCount
	 * @param filterValidity
	 * @param inviteUniqueCode
	 * @param flag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/inviteLinksUserChannel")
	public String inviteLinksUserChannel(String appKey, String accountID, String channelNumber,
			String customType, String startPage, String pageCount, String filterValidity,
			String inviteUniqueCode, String flag) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			if ("1".equals(flag)) {
				result = channelService.createChannel(appkey, secret, accountID, channelNumber,
						customType, inviteLinksUserChannel);
				logger.info(appKey + "===>" + result);
			} else if ("2".equals(flag)) {
				result = channelService.getChannel(appkey, secret, accountID, channelNumber,
						customType, startPage, pageCount, filterValidity, inviteLinksUserChannel);
				logger.info(appKey + "===>" + result);
			} else if ("3".equals(flag)) {
				result = channelService.delChannel(appkey, secret, accountID, channelNumber,
						inviteUniqueCode, customType, inviteLinksUserChannel);
				logger.info(appKey + "===>" + result);
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
}
