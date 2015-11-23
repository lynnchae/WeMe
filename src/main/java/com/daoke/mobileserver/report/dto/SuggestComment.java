package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-8-26 下午1:48:28
 * @version 1.0
 */
public class SuggestComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -802021895511165446L;
	private Integer id; // 自增长id
	private Integer suggestID; // 反馈ID
	private String replyName; // 回复者
	private String replyContent; // 回复的具体内容
	private Integer commentTime; // 产生该条记录的时间
	private String accountID;
	private String nickName;
	private String suggestContent;
	private Integer suggestTime;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取 suggestID
	 * 
	 * @return suggestID
	 */
	public Integer getSuggestID() {
		return suggestID;
	}

	/**
	 * 设置 suggestID
	 * 
	 * @param suggestID
	 */
	public void setSuggestID(Integer suggestID) {
		this.suggestID = suggestID;
	}

	/**
	 * 获取 replyName
	 * 
	 * @return replyName
	 */
	public String getReplyName() {
		return replyName;
	}

	/**
	 * 设置 replyName
	 * 
	 * @param replyName
	 */
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	/**
	 * 获取 replyContent
	 * 
	 * @return replyContent
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * 设置 replyContent
	 * 
	 * @param replyContent
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * 获取 commentTime
	 *
	 * @return commentTime
	 */
	public Integer getCommentTime() {
		return commentTime;
	}

	/**
	 * 设置 commentTime
	 *
	 * @param commentTime
	 */
	public void setCommentTime(Integer commentTime) {
		this.commentTime = commentTime;
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
	 * 获取 nickName
	 *
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 设置 nickName
	 *
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 获取 suggestContent
	 *
	 * @return suggestContent
	 */
	public String getSuggestContent() {
		return suggestContent;
	}

	/**
	 * 设置 suggestContent
	 *
	 * @param suggestContent
	 */
	public void setSuggestContent(String suggestContent) {
		this.suggestContent = suggestContent;
	}

	/**
	 * 获取 suggestTime
	 *
	 * @return suggestTime
	 */
	public Integer getSuggestTime() {
		return suggestTime;
	}

	/**
	 * 设置 suggestTime
	 *
	 * @param suggestTime
	 */
	public void setSuggestTime(Integer suggestTime) {
		this.suggestTime = suggestTime;
	}
}
