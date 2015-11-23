package com.daoke.mobileserver.weibo.service.impl;

import com.daoke.mobileserver.util.Sha1;
import com.daoke.mobileserver.weibo.service.WeiBoService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author wangliming
 * @date 2014-11-12 上午10:40:15
 * @version 1.0
 */
@Service
public class WeiBoServiceImpl implements WeiBoService {

	@Override
	public String sendSms(String appKey, String secret, String mobile, String content,
			String sendSms) throws Exception {
            String[] keys = { "appKey", "secret", "mobile", "content" };
		Object[] values = { appKey, secret, mobile, content };
		String result = Sha1.httpPost(keys, values, sendSms);
		return result;
	}

}










