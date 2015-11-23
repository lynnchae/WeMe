package com.daoke.mobileserver.custom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.daoke.mobileserver.custom.service.CustomService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import com.daoke.mobileserver.util.Sha1;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * @author wangliming
 * @date 2014-10-29 上午10:11:58
 * @version 1.0
 */
@Service
public class CustomServiceImpl implements CustomService {

	@Override
	public String getCustomInfo(String appKey, String secret, String accountID, String actionType,
			String getCustomInfo) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "actionType" };
		Object[] valueContent = { actionType };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getCustomInfo);
		return result;
	}

	@Override
	public String setCustomInfo(String appKey, String secret, String accountID, String actionType,
			String customType, String customParameter, String setCustomInfo) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "actionType", "customType",
				"customParameter" };
		Object[] values = { appKey, secret, accountID, actionType, customType, customParameter };
		String result = Sha1.httpPost(keys, values, setCustomInfo);
		return result;
	}

	@Override
	public String getSubscribeMsg(String appKey, String secret, String accountID,
			String getSubscribeMsg) throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getSubscribeMsg);
		return result;
	}

	@Override
	public String setSubscribeMsg(String appKey, String secret, String accountID,
			String subParameter, String setSubscribeMsg) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "subParameter" };
		Object[] valueContent = { subParameter };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				setSubscribeMsg);
		return result;
	}

	@Override
	public String appConnectSendWeibo(String appKey, String secret, String multimediaURL,
			String accountID, String parameterType, String callbackURL, String appConnectSendWeibo)
			throws Exception {
		String resultJson = null;

		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("multimediaURL");
		keys.add("accountID");
		keys.add("parameterType");
		keys.add("level");
		keys.add("interval");
		keys.add("callbackURL");
		keys.add("amrtomp3");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(multimediaURL);
		values.add(accountID);
		values.add(parameterType);
		values.add("20");
		values.add("6000");
		values.add(callbackURL);
		values.add("1");
		String sign = Sha1.calculationSign(keys.toArray(new String[0]), values.toArray());

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(appConnectSendWeibo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appKey", appKey);
		map.put("sign", sign);
		map.put("multimediaURL", multimediaURL);
		map.put("accountID", accountID);
		map.put("parameterType", parameterType);
		map.put("level", "20");
		map.put("interval", "6000");
		map.put("callbackURL", callbackURL);
		map.put("amrtomp3", "1");

		Part[] parts = new Part[map.size()];
		int i = 0;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			String paramname = iter.next();
			String paramvalue = (String) map.get(paramname);
			StringPart part = new StringPart(paramname, paramvalue);
			parts[i] = part;
			i++;
		}
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int statusCode = client.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			resultJson = post.getResponseBodyAsString();
		} else {
			resultJson = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		return resultJson;
	}

	@Override
	public String followMicroChannel(String appKey, String secret, String accountID,
			String uniqueCode, String followType, String channelNumber, String followMicroChannel)
			throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "uniqueCode", "followType", "channelNumber" };
		Object[] values = { appKey, secret, accountID, uniqueCode, followType, channelNumber };
		String result = Sha1.httpPost(keys, values, followMicroChannel);
		return result;
	}

	@Override
	public String unFollowUserChannel(String appKey, String secret, String accountID,
			String channelNumber, String followType, String unFollowType) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelNumber", "followType" };
		Object[] values = { appKey, secret, accountID, channelNumber, followType };
		String result = Sha1.httpPost(keys, values, unFollowType);
		return result;
	}

	@Override
	public String fetchFollowListUserChannel(String appKey, String secret, String accountID,
			String channelNumber, String startPage, String pageCount,
			String fetchFollowListUserChannel) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("channelNumber");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(channelNumber);

		String[] keyContent = { "startPage", "pageCount" };
		Object[] valueContent = { startPage, pageCount };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchFollowListUserChannel);
		return result;
	}

	@Override
	public String getMicroChannelInfo(String appKey, String secret, String accountID,
			String channelNumber, String getMicroChannelInfo) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelNumber" };
		Object[] values = { appKey, secret, accountID, channelNumber, };
		String result = Sha1.httpPost(keys, values, getMicroChannelInfo);
		return result;
	}

	@Override
	public String getOwnerFollowListUserChannel(String appKey, String secret, String accountID,
			String startPage, String pageCount, String getOwnerFollowListUserChannel)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		String[] keyContent = { "startPage", "pageCount" };
		Object[] valueContent = { startPage, pageCount };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getOwnerFollowListUserChannel);
		return result;
	}

	@Override
	public String getOwnerUserChannelFollowList(String appKey, String secret, String accountID,
			String channelNumber, String startPage, String pageCount,
			String getOwnerUserChannelFollowList) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("channelNumber");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(channelNumber);

		String[] keyContent = { "startPage", "pageCount" };
		Object[] valueContent = { startPage, pageCount };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getOwnerUserChannelFollowList);
		return result;
	}

	@Override
	public String resetInviteUniqueCode(String appKey, String secret, String accountID,
			String channelNumber, String channelType, String resetInviteUniqueCode)
			throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "channelNumber", "channelType" };
		Object[] values = { appKey, secret, accountID, channelNumber, channelType };
		String result = Sha1.httpPost(keys, values, resetInviteUniqueCode);
		return result;
	}

	@Override
	public String getCatalogInfo(String appKey, String secret, String startPage, String pageCount,
			String getCatalogInfo) throws Exception {
		String[] keys = { "appKey", "secret", "startPage", "pageCount" };
		Object[] values = { appKey, secret, startPage, pageCount };
		String result = Sha1.httpPost(keys, values, getCatalogInfo);
		return result;
	}

	@Override
	public String getRecheckMicroChannelList(String appKey, String secret,
			String accountID, String startPage, String pageCount,
			String getRecheckMicroChannelList) throws Exception {
		String[] keys = { "appKey", "secret","accountID", "startPage", "pageCount" };
		Object[] values = { appKey, secret,accountID, startPage, pageCount };
		String result = Sha1.httpPost(keys, values, getRecheckMicroChannelList);
		return result;
	}

	@Override
	public String modifyMicroChannel(String appKey, String secret,
			String accountID, String beforeChannelNumber,
			String channelNumber, String channelName,
			String channelIntroduction, String chiefAnnouncerIntr,
			String channelCityCode, String channelCatalogID,
			String channelCatalogUrl, String infoType,String applyIdx, String modifyMicroChannel)
			throws Exception {
		String[] keys = { "appKey", "secret","accountID", "beforeChannelNumber", "channelNumber",
				"channelName","channelIntroduction","chiefAnnouncerIntr","channelCityCode","channelCatalogID",
				"channelCatalogUrl","infoType","applyIdx"};
		Object[] values = { appKey, secret,accountID, beforeChannelNumber, channelNumber,
                channelName,channelIntroduction,chiefAnnouncerIntr,channelCityCode,channelCatalogID,
                channelCatalogUrl,infoType,applyIdx};
		String result = Sha1.httpPost(keys, values, modifyMicroChannel);
		return result;
	}
	
	@Override
	public String saveFile(String appKey, String secret, MultipartFile file, String saveFile)
			throws Exception {
		String result = null;
		String filename = file.getOriginalFilename();
		String fileType = filename.substring(filename.indexOf(".") + 1);
		String[] keys = { "appKey", "secret", "length", "fileType" };
		Object[] values = { appKey, secret, String.valueOf(file.getSize()), fileType };
		String sign = Sha1.calculationSign(keys, values);

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(saveFile);
		StringPart appKeyPart = new StringPart("appKey", appKey, "utf-8");
		StringPart signPart = new StringPart("sign", sign, "utf-8");
		StringPart lengthPart = new StringPart("length", String.valueOf(file.getSize()), "utf-8");
		StringPart fileTypePart = new StringPart("fileType", fileType, "utf-8");
		ByteArrayPartSource arrayPartSource = new ByteArrayPartSource(file.getOriginalFilename(),
				file.getBytes());
		FilePart filePart = new FilePart("mmfile", arrayPartSource);
		Part[] parts = new Part[] { appKeyPart, signPart, lengthPart, fileTypePart, filePart };

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

}
