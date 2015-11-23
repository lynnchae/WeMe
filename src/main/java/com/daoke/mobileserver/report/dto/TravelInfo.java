package com.daoke.mobileserver.report.dto;

/**
 * 旅程信息
 * 
 * @author wangliming
 * @date 2014-6-26 上午10:32:42
 * @version 1.0
 */
public class TravelInfo {

	private int actualMileage; // 实际里程
	private String endCityCode; // 结束城市编码
	private String startCityName; // 开始城市名字
	private String endCityName; // 结束城市名字
	private String startCityCode;// 开始城市编码
	private int endLatitude;// 结束纬度
	private int endLongitude;// 结束经度
	private int endTime;// 结束时间
	private int startLatitude;// 开始纬度
	private int startLongitude;// 开始经度
	private int startTime; // 开始时间
	private int sumMileage; // 总里程
	private int totalTime; // 总时间
	private String travelID; // ；旅程ID
	private String endRoadName;
	private String startRoadName;
	private int averageSpeed;
	private int maximumSpeed;

	/**
	 * 获取 actualMileage
	 * 
	 * @return actualMileage
	 */
	public int getActualMileage() {
		return actualMileage;
	}

	/**
	 * 设置 actualMileage
	 * 
	 * @param actualMileage
	 */
	public void setActualMileage(int actualMileage) {
		this.actualMileage = actualMileage;
	}

	/**
	 * 获取 endCityCode
	 * 
	 * @return endCityCode
	 */
	public String getEndCityCode() {
		return endCityCode;
	}

	/**
	 * 设置 endCityCode
	 * 
	 * @param endCityCode
	 */
	public void setEndCityCode(String endCityCode) {
		this.endCityCode = endCityCode;
	}

	/**
	 * 获取 startCityName
	 * 
	 * @return startCityName
	 */
	public String getStartCityName() {
		return startCityName;
	}

	/**
	 * 设置 startCityName
	 * 
	 * @param startCityName
	 */
	public void setStartCityName(String startCityName) {
		this.startCityName = startCityName;
	}

	/**
	 * 获取 endCityName
	 * 
	 * @return endCityName
	 */
	public String getEndCityName() {
		return endCityName;
	}

	/**
	 * 设置 endCityName
	 * 
	 * @param endCityName
	 */
	public void setEndCityName(String endCityName) {
		this.endCityName = endCityName;
	}

	/**
	 * 获取 startCityCode
	 * 
	 * @return startCityCode
	 */
	public String getStartCityCode() {
		return startCityCode;
	}

	/**
	 * 设置 startCityCode
	 * 
	 * @param startCityCode
	 */
	public void setStartCityCode(String startCityCode) {
		this.startCityCode = startCityCode;
	}

	/**
	 * 获取 endLatitude
	 * 
	 * @return endLatitude
	 */
	public int getEndLatitude() {
		return endLatitude;
	}

	/**
	 * 设置 endLatitude
	 * 
	 * @param endLatitude
	 */
	public void setEndLatitude(int endLatitude) {
		this.endLatitude = endLatitude;
	}

	/**
	 * 获取 endLongitude
	 * 
	 * @return endLongitude
	 */
	public int getEndLongitude() {
		return endLongitude;
	}

	/**
	 * 设置 endLongitude
	 * 
	 * @param endLongitude
	 */
	public void setEndLongitude(int endLongitude) {
		this.endLongitude = endLongitude;
	}

	/**
	 * 获取 endTime
	 * 
	 * @return endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置 endTime
	 * 
	 * @param endTime
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取 startLatitude
	 * 
	 * @return startLatitude
	 */
	public int getStartLatitude() {
		return startLatitude;
	}

	/**
	 * 设置 startLatitude
	 * 
	 * @param startLatitude
	 */
	public void setStartLatitude(int startLatitude) {
		this.startLatitude = startLatitude;
	}

	/**
	 * 获取 startLongitude
	 * 
	 * @return startLongitude
	 */
	public int getStartLongitude() {
		return startLongitude;
	}

	/**
	 * 设置 startLongitude
	 * 
	 * @param startLongitude
	 */
	public void setStartLongitude(int startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * 获取 startTime
	 * 
	 * @return startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * 设置 startTime
	 * 
	 * @param startTime
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取 sumMileage
	 * 
	 * @return sumMileage
	 */
	public int getSumMileage() {
		return sumMileage;
	}

	/**
	 * 设置 sumMileage
	 * 
	 * @param sumMileage
	 */
	public void setSumMileage(int sumMileage) {
		this.sumMileage = sumMileage;
	}

	/**
	 * 获取 totalTime
	 * 
	 * @return totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * 设置 totalTime
	 * 
	 * @param totalTime
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * 获取 travelID
	 * 
	 * @return travelID
	 */
	public String getTravelID() {
		return travelID;
	}

	/**
	 * 设置 travelID
	 * 
	 * @param travelID
	 */
	public void setTravelID(String travelID) {
		this.travelID = travelID;
	}

	/**
	 * 获取 endRoadName
	 * 
	 * @return endRoadName
	 */
	public String getEndRoadName() {
		return endRoadName;
	}

	/**
	 * 设置 endRoadName
	 * 
	 * @param endRoadName
	 */
	public void setEndRoadName(String endRoadName) {
		this.endRoadName = endRoadName;
	}

	/**
	 * 获取 startRoadName
	 * 
	 * @return startRoadName
	 */
	public String getStartRoadName() {
		return startRoadName;
	}

	/**
	 * 设置 startRoadName
	 * 
	 * @param startRoadName
	 */
	public void setStartRoadName(String startRoadName) {
		this.startRoadName = startRoadName;
	}

	/**
	 * 获取 averageSpeed
	 *
	 * @return averageSpeed
	 */
	public int getAverageSpeed() {
		return averageSpeed;
	}

	/**
	 * 设置 averageSpeed
	 *
	 * @param averageSpeed
	 */
	public void setAverageSpeed(int averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	/**
	 * 获取 maximumSpeed
	 *
	 * @return maximumSpeed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	/**
	 * 设置 maximumSpeed
	 *
	 * @param maximumSpeed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

}
