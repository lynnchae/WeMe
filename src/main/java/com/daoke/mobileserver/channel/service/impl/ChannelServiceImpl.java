package com.daoke.mobileserver.channel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.channel.service.ChannelService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import com.daoke.mobileserver.util.Sha1;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午7:16:02
 * @version 1.0
 */
@Service
public class ChannelServiceImpl implements ChannelService {

	@Override
	public String applyMicroChannel(String appKey, String secret, String accountID,
			String channelNumber, String channelName, String channelIntroduction,
			String chiefAnnouncerIntr, String channelCityCode, String channelCatalogID,
			String channelCatalogUrl, String applyMicroChannel) throws Exception {
		String result = null;
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("channelNumber");
		keys.add("channelName");
		keys.add("channelIntroduction");
		keys.add("chiefAnnouncerIntr");
		keys.add("channelCityCode");
		keys.add("channelCatalogID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(channelNumber);
		values.add(channelName);
		values.add(channelIntroduction);
		values.add(chiefAnnouncerIntr);
		values.add(channelCityCode);
		values.add(channelCatalogID);

		if (StringUtils.isNotEmpty(channelCatalogUrl)) {
			keys.add("channelCatalogUrl");
			values.add(channelCatalogUrl);
		}
		String sign = Sha1.calculationSign(keys.toArray(new String[0]), values.toArray());
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(applyMicroChannel);
		Part[] parts = new Part[keys.size()];
		for (int i = 0; i < keys.size(); i++) {
			String paramname = keys.get(i);
			String paramvalue = (String) values.get(i);
			if (("secret").equals(paramname)) {
				paramname = "sign";
				paramvalue = sign;
			}
			StringPart part = new StringPart(paramname, paramvalue, "utf-8");
			parts[i] = part;
		}
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));

		int statusCode = client.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			result = post.getResponseBodyAsString();
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
		}

		return result;
	}

	@Override
	public String fetchMicroChannel(String appKey, String secret, String accountID,
			String startPage, String pageCount, String infoType, String channelStatus,
			String citycode, String channelName, String catalogID, String fetchMicroChannel)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("infoType");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(infoType);

		String[] keyContent = { "channelStatus", "cityCode", "channelName", "catalogID", "startPage",
				"pageCount" };
		Object[] valueContent = { channelStatus, citycode, channelName, catalogID, startPage,
				pageCount };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchMicroChannel);
		return result;
	}

	@Override
	public String createChannel(String appKey, String secret, String accountID,
			String channelNumber, String customType, String createChannel) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelNumber", "customType" };
		Object[] values = { appKey, secret, accountID, channelNumber, customType };
		String result = Sha1.httpPost(keys, values, createChannel);
		return result;
	}

	@Override
	public String delChannel(String appKey, String secret, String accountID, String channelNumber,
			String inviteUniqueCode, String customType, String delChannel) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelNumber", "inviteUniqueCode",
				"customType" };
		Object[] values = { appKey, secret, accountID, channelNumber, inviteUniqueCode, customType };
		String result = Sha1.httpPost(keys, values, delChannel);
		return result;
	}

	@Override
	public String getChannel(String appKey, String secret, String accountID, String channelNumber,
			String customType, String startPage, String pageCount, String filterValidity,
			String getChannel) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("channelNumber");
		keys.add("customType");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(channelNumber);
		values.add(customType);
		String[] keyContent = { "startPage", "pageCount", "filterValidity" };
		Object[] valueContent = { startPage, pageCount, filterValidity };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getChannel);
		return result;
	}
}
