package com.daoke.mobileserver.report.dto;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-9-9 下午5:19:18
 * @version 1.0
 */
public class RankInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602432429116574831L;
	private String accountID;
	private Integer mileageLiftingPosition;
	private Integer mileageRankNum;
	private Integer statsMonth;
	private Long sumMileage;
	private Long sumTime;
	private Integer timeLiftingPosition;
	private Integer timeRankNum;
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
	 * 获取 mileageLiftingPosition
	 * 
	 * @return mileageLiftingPosition
	 */
	public Integer getMileageLiftingPosition() {
		return mileageLiftingPosition;
	}

	/**
	 * 设置 mileageLiftingPosition
	 * 
	 * @param mileageLiftingPosition
	 */
	public void setMileageLiftingPosition(Integer mileageLiftingPosition) {
		this.mileageLiftingPosition = mileageLiftingPosition;
	}

	/**
	 * 获取 mileageRankNum
	 * 
	 * @return mileageRankNum
	 */
	public Integer getMileageRankNum() {
		return mileageRankNum;
	}

	/**
	 * 设置 mileageRankNum
	 * 
	 * @param mileageRankNum
	 */
	public void setMileageRankNum(Integer mileageRankNum) {
		this.mileageRankNum = mileageRankNum;
	}

	/**
	 * 获取 statsMonth
	 * 
	 * @return statsMonth
	 */
	public Integer getStatsMonth() {
		return statsMonth;
	}

	/**
	 * 设置 statsMonth
	 * 
	 * @param statsMonth
	 */
	public void setStatsMonth(Integer statsMonth) {
		this.statsMonth = statsMonth;
	}

	/**
	 * 获取 sumMileage
	 * 
	 * @return sumMileage
	 */
	public Long getSumMileage() {
		return sumMileage;
	}

	/**
	 * 设置 sumMileage
	 * 
	 * @param sumMileage
	 */
	public void setSumMileage(Long sumMileage) {
		this.sumMileage = sumMileage;
	}

	/**
	 * 获取 sumTime
	 * 
	 * @return sumTime
	 */
	public Long getSumTime() {
		return sumTime;
	}

	/**
	 * 设置 sumTime
	 * 
	 * @param sumTime
	 */
	public void setSumTime(Long sumTime) {
		this.sumTime = sumTime;
	}

	/**
	 * 获取 timeLiftingPosition
	 * 
	 * @return timeLiftingPosition
	 */
	public Integer getTimeLiftingPosition() {
		return timeLiftingPosition;
	}

	/**
	 * 设置 timeLiftingPosition
	 * 
	 * @param timeLiftingPosition
	 */
	public void setTimeLiftingPosition(Integer timeLiftingPosition) {
		this.timeLiftingPosition = timeLiftingPosition;
	}

	/**
	 * 获取 timeRankNum
	 * 
	 * @return timeRankNum
	 */
	public Integer getTimeRankNum() {
		return timeRankNum;
	}

	/**
	 * 设置 timeRankNum
	 * 
	 * @param timeRankNum
	 */
	public void setTimeRankNum(Integer timeRankNum) {
		this.timeRankNum = timeRankNum;
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
