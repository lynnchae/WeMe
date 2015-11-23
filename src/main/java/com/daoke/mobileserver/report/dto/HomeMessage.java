package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-8-8 下午5:23:39
 * @version 1.0
 */
public class HomeMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8417574247484252044L;
	private Integer id;
	private String accountID;
	private String phoneImei;
	private Long createTime;
	private Long updateTime;
	
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
	 * 获取 account
	 * 
	 * @return account
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * 设置 account
	 * 
	 * @param account
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
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
