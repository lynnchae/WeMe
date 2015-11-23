package com.daoke.mobileserver.report.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-8-25 下午4:19:24
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Transcript implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162445037830080286L;
	private String daySumMileage; // 最新的里程
	private String weekSumMileage;
	private String yearSumMileage;
	private String dayTime;// 最新的日期
	private String monthSumMileage; // 月里程
	private String allSumMileage; // 总里程
	private String monthTime;// 最新的一个月

	private Double dayReward;
	private Double monthReward;
	private Double weekReward;
	private Double yearReward;
	private Double totalReward;

	public Transcript(String daySumMileage, String weekSumMileage, String yearSumMileage,
			String dayTime, String monthSumMileage, String allSumMileage, String monthTime,
			Double dayReward, Double monthReward, Double weekReward, Double yearReward,
			Double totalReward) {
		super();
		this.daySumMileage = daySumMileage;
		this.weekSumMileage = weekSumMileage;
		this.yearSumMileage = yearSumMileage;
		this.dayTime = dayTime;
		this.monthSumMileage = monthSumMileage;
		this.allSumMileage = allSumMileage;
		this.monthTime = monthTime;
		this.dayReward = dayReward;
		this.monthReward = monthReward;
		this.weekReward = weekReward;
		this.yearReward = yearReward;
		this.totalReward = totalReward;
	}

	/**
	 * 获取 daySumMileage
	 * 
	 * @return daySumMileage
	 */
	public String getDaySumMileage() {
		return daySumMileage;
	}

	/**
	 * 设置 daySumMileage
	 * 
	 * @param daySumMileage
	 */
	public void setDaySumMileage(String daySumMileage) {
		this.daySumMileage = daySumMileage;
	}

	/**
	 * 获取 weekSumMileage
	 * 
	 * @return weekSumMileage
	 */
	public String getWeekSumMileage() {
		return weekSumMileage;
	}

	/**
	 * 设置 weekSumMileage
	 * 
	 * @param weekSumMileage
	 */
	public void setWeekSumMileage(String weekSumMileage) {
		this.weekSumMileage = weekSumMileage;
	}

	/**
	 * 获取 yearSumMileage
	 * 
	 * @return yearSumMileage
	 */
	public String getYearSumMileage() {
		return yearSumMileage;
	}

	/**
	 * 设置 yearSumMileage
	 * 
	 * @param yearSumMileage
	 */
	public void setYearSumMileage(String yearSumMileage) {
		this.yearSumMileage = yearSumMileage;
	}

	/**
	 * 获取 dayTime
	 * 
	 * @return dayTime
	 */
	public String getDayTime() {
		return dayTime;
	}

	/**
	 * 设置 dayTime
	 * 
	 * @param dayTime
	 */
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	/**
	 * 获取 monthSumMileage
	 * 
	 * @return monthSumMileage
	 */
	public String getMonthSumMileage() {
		return monthSumMileage;
	}

	/**
	 * 设置 monthSumMileage
	 * 
	 * @param monthSumMileage
	 */
	public void setMonthSumMileage(String monthSumMileage) {
		this.monthSumMileage = monthSumMileage;
	}

	/**
	 * 获取 allSumMileage
	 * 
	 * @return allSumMileage
	 */
	public String getAllSumMileage() {
		return allSumMileage;
	}

	/**
	 * 设置 allSumMileage
	 * 
	 * @param allSumMileage
	 */
	public void setAllSumMileage(String allSumMileage) {
		this.allSumMileage = allSumMileage;
	}

	/**
	 * 获取 monthTime
	 * 
	 * @return monthTime
	 */
	public String getMonthTime() {
		return monthTime;
	}

	/**
	 * 设置 monthTime
	 * 
	 * @param monthTime
	 */
	public void setMonthTime(String monthTime) {
		this.monthTime = monthTime;
	}

	/**
	 * 获取 dayReward
	 * 
	 * @return dayReward
	 */
	public double getDayReward() {
		return dayReward;
	}

	/**
	 * 设置 dayReward
	 * 
	 * @param dayReward
	 */
	public void setDayReward(double dayReward) {
		this.dayReward = dayReward;
	}

	/**
	 * 获取 monthReward
	 * 
	 * @return monthReward
	 */
	public double getMonthReward() {
		return monthReward;
	}

	/**
	 * 设置 monthReward
	 * 
	 * @param monthReward
	 */
	public void setMonthReward(double monthReward) {
		this.monthReward = monthReward;
	}

	/**
	 * 获取 weekReward
	 * 
	 * @return weekReward
	 */
	public double getWeekReward() {
		return weekReward;
	}

	/**
	 * 设置 weekReward
	 * 
	 * @param weekReward
	 */
	public void setWeekReward(double weekReward) {
		this.weekReward = weekReward;
	}

	/**
	 * 获取 yearReward
	 * 
	 * @return yearReward
	 */
	public double getYearReward() {
		return yearReward;
	}

	/**
	 * 设置 yearReward
	 * 
	 * @param yearReward
	 */
	public void setYearReward(double yearReward) {
		this.yearReward = yearReward;
	}

	/**
	 * 获取 totalReward
	 * 
	 * @return totalReward
	 */
	public double getTotalReward() {
		return totalReward;
	}

	/**
	 * 设置 totalReward
	 * 
	 * @param totalReward
	 */
	public void setTotalReward(double totalReward) {
		this.totalReward = totalReward;
	}

}
