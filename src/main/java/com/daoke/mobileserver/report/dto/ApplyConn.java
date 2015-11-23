package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 *
 * @author wangliming
 * @date 2014-9-3 上午10:56:39
 * @version 1.0
 */
public class ApplyConn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566901569570668685L;
	private String accountID;
	private String nickname;
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
	 * 获取 nickname
	 *
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置 nickname
	 *
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
