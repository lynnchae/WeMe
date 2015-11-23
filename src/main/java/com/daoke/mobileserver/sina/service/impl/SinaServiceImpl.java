package com.daoke.mobileserver.sina.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.sina.service.SinaService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:14:21
 * @version 1.0
 */
@Service
public class SinaServiceImpl implements SinaService {

	@Override
	public String sinaSaveOauth(String appKey, String secret, String accountID, String sinaWeiboID,
			String accessToken, String accessTokenExpiration, String appInfo, String sinaAppKey,
			String nickname, String sinaSaveOauth) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "sinaWeiboID", "accessToken", "accessTokenExpiration", "appInfo",
				"sinaAppKey", "nickname" };
		Object[] valueContent = { sinaWeiboID, accessToken, accessTokenExpiration, appInfo,
				sinaAppKey, nickname };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), sinaSaveOauth);
		return result;
	}

	@Override
	public String sinaCheckOauth(String appKey, String secret, String accountID, String sinaAppKey,
			String sinaCheckOauth) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "sinaAppKey" };
		Object[] values = { appKey, secret, accountID, sinaAppKey };
		String result = Sha1.httpPost(keys, values, sinaCheckOauth);
		return result;
	}

}
