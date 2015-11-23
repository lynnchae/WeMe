package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-9-23 下午5:36:47
 * @version 1.0
 */
public class AssociateAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730399195272362819L;
	private String appKey; // 语镜公司下发的APP的标识
	private String secret; // 加密签名
	private String accountID; // 账户编号
	private String account; // 用户名
	private String loginType; // 登陆类型:
								// 1：手机号码登录；2：用户邮箱登陆；3：QQ登录；4：MSN登录；5：微信登陆；6：新浪微博
	private String token; // 第三方授权信息，不允许为空
	private String refreshToken; // 第三方授权信息，不允许为空
	private String accessToken; // 第三方授权信息，不允许为空
	private String accessTokenExpiration; // 授权结束时间，单位为秒

	/**
	 * 获取 appKey
	 * 
	 * @return appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * 设置 appKey
	 * 
	 * @param appKey
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * 获取 accountID
	 * 
	 * @return accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * 设置 accountID
	 * 
	 * @param accountID
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * 获取 account
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置 account
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取 loginType
	 * 
	 * @return loginType
	 */
	public String getLoginType() {
		return loginType;
	}

	/**
	 * 设置 loginType
	 * 
	 * @param loginType
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/**
	 * 获取 token
	 * 
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置 token
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取 refreshToken
	 * 
	 * @return refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * 设置 refreshToken
	 * 
	 * @param refreshToken
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * 获取 accessToken
	 * 
	 * @return accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * 设置 accessToken
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * 获取 accessTokenExpiration
	 * 
	 * @return accessTokenExpiration
	 */
	public String getAccessTokenExpiration() {
		return accessTokenExpiration;
	}

	/**
	 * 设置 accessTokenExpiration
	 * 
	 * @param accessTokenExpiration
	 */
	public void setAccessTokenExpiration(String accessTokenExpiration) {
		this.accessTokenExpiration = accessTokenExpiration;
	}

	/**
	 * 获取 secret
	 * 
	 * @return secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * 设置 secret
	 * 
	 * @param secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

}
