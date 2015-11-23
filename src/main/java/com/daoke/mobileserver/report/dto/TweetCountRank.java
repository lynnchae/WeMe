package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-9-23 上午10:20:11
 * @version 1.0
 */
public class TweetCountRank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407935448031032106L;
	private String accountID;
	private int tweetCount;
	private int tweetCountRank;
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
	 * 获取 tweetCount
	 * 
	 * @return tweetCount
	 */
	public int getTweetCount() {
		return tweetCount;
	}

	/**
	 * 设置 tweetCount
	 * 
	 * @param tweetCount
	 */
	public void setTweetCount(int tweetCount) {
		this.tweetCount = tweetCount;
	}

	/**
	 * 获取 tweetCountRank
	 * 
	 * @return tweetCountRank
	 */
	public int getTweetCountRank() {
		return tweetCountRank;
	}

	/**
	 * 设置 tweetCountRank
	 * 
	 * @param tweetCountRank
	 */
	public void setTweetCountRank(int tweetCountRank) {
		this.tweetCountRank = tweetCountRank;
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
