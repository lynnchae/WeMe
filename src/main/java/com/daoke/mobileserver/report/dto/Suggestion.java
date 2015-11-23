package com.daoke.mobileserver.report.dto;

/**
 * 用户建议类
 * 
 * @author wangliming
 * @date 2014-6-17 上午10:35:55
 * @version 1.0
 */
public class Suggestion {

	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private String senderAccountID;
	
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 反馈内容
	 */
	private String text;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 意见类型 0-用户提议1-语镜反馈用户
	 */
	private String suggestionType;

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
	 * 获取 senderAccountID
	 *
	 * @return senderAccountID
	 */
	public String getSenderAccountID() {
		return senderAccountID;
	}

	/**
	 * 设置 senderAccountID
	 *
	 * @param senderAccountID
	 */
	public void setSenderAccountID(String senderAccountID) {
		this.senderAccountID = senderAccountID;
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
	 * 获取 text
	 *
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置 text
	 *
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取 createTime
	 *
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 *
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 suggestionType
	 *
	 * @return suggestionType
	 */
	public String getSuggestionType() {
		return suggestionType;
	}

	/**
	 * 设置 suggestionType
	 *
	 * @param suggestionType
	 */
	public void setSuggestionType(String suggestionType) {
		this.suggestionType = suggestionType;
	}
	
}
