package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-8-11 上午9:45:41
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ConnMessage implements Serializable{

	private Integer id; // 主键id
	private String accountID; // 发送者账号
	private String nickName; // 发送者昵称
	private String phoneImei; // 接收者的手机imei
	private String fileURL; // 音频文件地址
	private String fileDuration; // 录音时长
	private String isRead; // 是否被读，0：未读，1：已读
	private Long createTime; // 创建时间
	private Long updateTime; //跟新时间

	public ConnMessage() {

	}

	public ConnMessage(String accountID, String nickName, String phoneImei, String fileURL,
			String fileDuration, String isRead) {
		this.accountID = accountID;
		this.nickName = nickName;
		this.phoneImei = phoneImei;
		this.fileURL = fileURL;
		this.fileDuration = fileDuration;
		this.isRead = isRead;
	}

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
	 * 获取 phoneImei
	 * 
	 * @return phoneImei
	 */
	public String getPhoneImei() {
		return phoneImei;
	}

	/**
	 * 设置 phoneImei
	 * 
	 * @param phoneImei
	 */
	public void setPhoneImei(String phoneImei) {
		this.phoneImei = phoneImei;
	}

	/**
	 * 获取 fileURL
	 * 
	 * @return fileURL
	 */
	public String getFileURL() {
		return fileURL;
	}

	/**
	 * 设置 fileURL
	 * 
	 * @param fileURL
	 */
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	/**
	 * 获取 fileDuration
	 * 
	 * @return fileDuration
	 */
	public String getFileDuration() {
		return fileDuration;
	}

	/**
	 * 设置 fileDuration
	 * 
	 * @param fileDuration
	 */
	public void setFileDuration(String fileDuration) {
		this.fileDuration = fileDuration;
	}

	/**
	 * 获取 isRead
	 * 
	 * @return isRead
	 */
	public String getIsRead() {
		return isRead;
	}

	/**
	 * 设置 isRead
	 * 
	 * @param isRead
	 */
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ConnMessage [id=" + id + ", accountID=" + accountID + ", nickName=" + nickName
				+ ", phoneImei=" + phoneImei + ", fileURL=" + fileURL + ", fileDuration="
				+ fileDuration + ", isRead=" + isRead + ", createTime=" + createTime + "]";
	}

	/**
	 * 获取 updateTime
	 *
	 * @return updateTime
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 *
	 * @param updateTime
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
