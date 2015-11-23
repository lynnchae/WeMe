package com.daoke.mobileserver.rss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.report.dto.Channel;
import com.daoke.mobileserver.rss.service.RssService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:28:36
 * @version 1.0
 */
@Controller
public class RssController {

	private final Logger logger = Logger.getLogger(RssController.class);

	@Value("#{apiConfig[getChannels]}")
	private String getChannels;

	@Value("#{apiConfig[subChannels]}")
	private String subChannels;

	@Value("#{apiConfig[channelset]}")
	private String channelset;

	@Value("#{apiConfig[setChannel]}")
	private String setChannel;

	@Value("#{apiConfig[resetChannel]}")
	private String resetChannel;

	@Value("#{apiConfig[getUserChannel]}")
	private String getUserChannel;

	@Value("#{apiConfig[getChannelUserList]}")
	private String getChannelUserList;

	@Value("#{apiConfig[getVoiceChannels]}")
	private String getVoiceChannels;

	@Value("#{apiConfig[subVoiceChannels]}")
	private String subVoiceChannels;

	@Autowired
	private RssService rssService;

	/**
	 * 获取可用RSS源列表和用户已订阅的源列表
	 * 
	 * @param accountID
	 * @param getChannels
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getChannels")
	public String getChannels(String accountID, HttpServletResponse response) {
		String result = null;
		try {
			result = rssService.getChannels(accountID, getChannels);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
                    ConstantsUtil.RESULT_OK));
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}

		return result;
	}

	/**
	 * 获取可用RSS源列表和用户已订阅的源列表
	 * 
	 * @param accountID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getChannelList")
	public String getChannelList(String accountID, String version, HttpServletResponse response) {
		String result = null;
		try {
			String subChannels = rssService.getChannels(accountID, getChannels);
			String allChannels = rssService.getChannels(null, getChannels);
			List<Channel> allChanList = new ArrayList<Channel>();
			if (allChannels.startsWith("[")) {
				JSONArray allChanArray = JSONArray.fromObject(allChannels);
				allChanList = (List<Channel>) JSONArray.toCollection(allChanArray, Channel.class);
				if (subChannels.startsWith("[")) {
					JSONArray subChanArray = JSONArray.fromObject(subChannels);
					List<Channel> subChanList = (List<Channel>) JSONArray.toCollection(subChanArray, Channel.class);
					for (Channel allChannel : allChanList) {
						if (("1").equals(version)) {
							String from = allChannel.getFrom();
							if (("netEase").equals(from)) {
								allChannel.setFrom("网易新闻");
							} else if (("huanqiu").equals(from)) {
								allChannel.setFrom("环球新闻");
							} else if (("baidu").equals(from)) {
								allChannel.setFrom("百度新闻");
							} else if (("sina").equals(from)) {
								allChannel.setFrom("新浪新闻");
							} else {
								allChannel.setFrom("其他新闻");
							}
						}
						for (Channel subChannel : subChanList) {
							if ((subChannel.getTypeId()).equals(allChannel.getTypeId())) {
								allChannel.setIsSub(true);
								break;
							} else {
								allChannel.setIsSub(false);
							}
						}
					}
				}
			}
			Map<String, List<Channel>> map = new HashMap<String, List<Channel>>();
			for (Channel channel : allChanList) {
				String from = channel.getFrom();
				if (map.containsKey(from)) {
					map.get(from).add(channel);
				} else {
					List<Channel> list = new ArrayList<Channel>();
					list.add(channel);
					map.put(from, list);
				}
			}
			JSONArray jsonArray = new JSONArray();
			// 添加有声资讯
			if (("1").equals(version)) {
				List<Channel> voiceChannelList = getVoiceChannelList(accountID);
				jsonArray.add(JSONArray.fromObject(voiceChannelList));
			}
			// 添加新闻
			for (Map.Entry<String, List<Channel>> entry : map.entrySet()) {
				jsonArray.add(JSONArray.fromObject(entry.getValue()));
			}
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, jsonArray);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
					ConstantsUtil.RESULT_OK));
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}

		return result;
	}

	/**
	 * 获取可用RSS源列表和用户已订阅的源列表
	 * 
	 * @param accountID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Channel> getVoiceChannelList(String accountID) {
		List<Channel> allChanList = null;
		try {
			String subChannels = rssService.getVoiceChannels(accountID, getVoiceChannels);
			String allChannels = rssService.getVoiceChannels(null, getVoiceChannels);
			if (allChannels.startsWith("[")) {
				JSONArray allChanArray = JSONArray.fromObject(allChannels);
				allChanList = (List<Channel>) JSONArray.toCollection(allChanArray, Channel.class);
				for (Channel channel : allChanList) {
					channel.setFrom("有声资讯");
				}
				if (subChannels.startsWith("[")) {
					JSONArray subChanArray = JSONArray.fromObject(subChannels);
					List<Channel> subChanList = (List<Channel>) JSONArray.toCollection(subChanArray, Channel.class);
					for (Channel allChannel : allChanList) {
						for (Channel subChannel : subChanList) {
							if ((subChannel.getTypeId()).equals(allChannel.getTypeId())) {
								allChannel.setIsSub(true);
								break;
							} else {
								allChannel.setIsSub(false);
							}
						}
					}
				}
			}
			logger.info(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
					ConstantsUtil.RESULT_OK));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return allChanList;
	}

	/**
	 * 保存个人订阅的新闻源
	 * 
	 * @param accountID
	 * @param channels
	 * @param subChannels
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/subChannels")
	public String subChannels(String accountID, String channels) {
		String result = null;
		try {
			result = rssService.subChannels(accountID, channels, subChannels);
			int error = JSONObject.fromObject(result).getInt("error");
			if (error == 0) {
				result = rssService.subVoiceChannels(accountID, channels, subVoiceChannels);
			}
			logger.info(result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}

		return result;
	}

	/**
	 * 频道设置
	 * 
	 * @param appKey
	 * @param secret
	 * @param channelID
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/channelset")
	public String channelset(String appKey, String channelID, String accountID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rssService.channelset(appkey, secret, channelID, accountID, channelset);
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
	 * 设置频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelID
	 * @param actionType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setChannel")
	public String setChannel(String appKey, String accountID, String channelID, String actionType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rssService.setChannel(appkey, secret, accountID, channelID, actionType,
					setChannel);
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
	 * 重置频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetChannel")
	public String resetChannel(String appKey, String accountID, String actionType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rssService.resetChannel(appkey, secret, accountID, actionType, resetChannel);
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
	 * 查询用户当前频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserChannel")
	public String getUserChannel(String appKey, String accountID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rssService.getUserChannel(appkey, secret, accountID, getUserChannel);
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
	 * 查询频道用户列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getChannelUserList")
	public String getChannelUserList(String appKey, String accountID, String channelID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rssService.getChannelUserList(appkey, secret, accountID, channelID,
					getChannelUserList);
			logger.info(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							ConstantsUtil.RESULT_OK));
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
