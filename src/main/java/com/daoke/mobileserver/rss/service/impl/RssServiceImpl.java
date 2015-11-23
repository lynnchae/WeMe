package com.daoke.mobileserver.rss.service.impl;

import com.daoke.mobileserver.rss.service.RssService;
import com.daoke.mobileserver.util.Sha1;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:27:23
 * @version 1.0
 */
@Service
public class RssServiceImpl implements RssService {

	@Override
	public String getChannels(String accountID, String getChannels) throws Exception {
		String result = null;
		if (StringUtils.isNotEmpty(accountID)) {
			String[] keys = { "accountID" };
			Object[] values = { accountID };
			result = Sha1.httpGet(keys, values, getChannels);
		} else {
			result = Sha1.httpGet(null, null, getChannels);
		}

		return result;
	}

	@Override
	public String subChannels(String accountID, String channels, String subChannels)
			throws Exception {
		String[] keys = { "accountID", "channels" };
		Object[] values = { accountID, channels };
		String result = Sha1.httpGet(keys, values, subChannels);
		return result;
	}

	@Override
	public String channelset(String appKey, String secret, String channelID, String accountID,
			String channelset) throws Exception {
		String[] keys = { "appKey", "secret", "channelID", "accountID" };
		Object[] values = { appKey, secret, channelID, accountID };
		String result = Sha1.httpPost(keys, values, channelset);
		return result;
	}

	@Override
	public String setChannel(String appKey, String secret, String accountID, String channelID,
			String actionType, String setChannel) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelID", "actionType" };
		Object[] values = { appKey, secret, accountID, channelID, actionType };
		String result = Sha1.httpPost(keys, values, setChannel);
		return result;
	}

	@Override
	public String resetChannel(String appKey, String secret, String accountID, String actionType,
			String resetChannel) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "actionType" };
		Object[] values = { appKey, secret, accountID, actionType };
		String result = Sha1.httpPost(keys, values, resetChannel);
		return result;
	}

	@Override
	public String getUserChannel(String appKey, String secret, String accountID,
			String getUserChannel) throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getUserChannel);
		return result;
	}

	@Override
	public String getChannelUserList(String appKey, String secret, String accountID,
			String channelID, String getChannelUserList) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelID" };
		Object[] values = { appKey, secret, accountID, channelID };
		String result = Sha1.httpPost(keys, values, getChannelUserList);
		return result;
	}

	@Override
	public String getVoiceChannels(String accountID, String getVoiceChannels) throws Exception {
		String result = null;
		if (StringUtils.isNotEmpty(accountID)) {
			String[] keys = { "accountID" };
			Object[] values = { accountID };
			result = Sha1.httpGet(keys, values, getVoiceChannels);
		} else {
			result = Sha1.httpGet(null, null, getVoiceChannels);
		}

		return result;
	}

	@Override
	public String subVoiceChannels(String accountID, String channels, String subVoiceChannels)
			throws Exception {
		String[] keys = { "accountID", "channels" };
		Object[] values = { accountID, channels };
		String result = Sha1.httpGet(keys, values, subVoiceChannels);
		return result;
	}

}
