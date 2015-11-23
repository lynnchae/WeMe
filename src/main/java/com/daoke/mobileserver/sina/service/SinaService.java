package com.daoke.mobileserver.sina.service;

/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:13:26
 * @version 1.0
 */
public interface SinaService {

	/**
	 * 保存用户新浪授权文档
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param sinaWeiboID
	 * @param accessToken
	 * @param accessTokenExpiration
	 * @param appInfo
	 * @param sinaAppKey
	 * @param nickname
	 * @param sinaSaveOauth
	 * @return
	 * @throws Exception
	 */
	public String sinaSaveOauth(String appKey, String secret, String accountID, String sinaWeiboID,
			String accessToken, String accessTokenExpiration, String appInfo, String sinaAppKey,
			String nickname, String sinaSaveOauth) throws Exception;

	/**
	 * 检验用户新浪授权文档
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param sinaAppKey
	 * @param sinaCheckOauth
	 * @return
	 * @throws Exception
	 */
	public String sinaCheckOauth(String appKey, String secret, String accountID, String sinaAppKey,
			String sinaCheckOauth) throws Exception;

}
