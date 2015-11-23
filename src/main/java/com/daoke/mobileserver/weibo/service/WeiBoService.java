package com.daoke.mobileserver.weibo.service;

/**
 * 
 * @author wangliming
 * @date 2014-11-12 上午10:38:25
 * @version 1.0
 */
public interface WeiBoService {

	/**
	 * 发送短信
	 * 
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param content
	 * @param sendSms
	 * @return
	 * @throws Exception
	 */
	public String sendSms(String appKey, String secret, String mobile, String content,
			String sendSms) throws Exception;

}
