package com.daoke.mobileserver.dianping.domain;

import java.io.Serializable;

/**
 * 
 * @author wangliming
 * @date 2014-9-17 下午5:07:43
 * @version 1.0
 */
public class DianPing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2008640207457692122L;
	private Integer id; // 自增id
	private String accountID; // 用户id
	private Integer latitude; // 纬度(纬度范围:-90-90),保留小数点后7位,常量10000000
	private Integer longitude; // 经度(经度范围:-180-180),保留小数点后7位,常量10000000
	private Integer cityCode; // 城市编码
	private String cityName; // 城市名
	private int POIType; // 类型1：停车场，2：优惠劵，3：团购
	private Integer createTime; // 记录创建时间

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
	 * 获取 latitude
	 * 
	 * @return latitude
	 */
	public Integer getLatitude() {
		return latitude;
	}

	/**
	 * 设置 latitude
	 * 
	 * @param latitude
	 */
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取 longitude
	 * 
	 * @return longitude
	 */
	public Integer getLongitude() {
		return longitude;
	}

	/**
	 * 设置 longitude
	 * 
	 * @param longitude
	 */
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取 cityCode
	 * 
	 * @return cityCode
	 */
	public Integer getCityCode() {
		return cityCode;
	}

	/**
	 * 设置 cityCode
	 * 
	 * @param cityCode
	 */
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 获取 cityName
	 * 
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 设置 cityName
	 * 
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 获取 pOIType
	 * 
	 * @return pOIType
	 */
	public int getPOIType() {
		return POIType;
	}

	/**
	 * 设置 pOIType
	 * 
	 * @param pOIType
	 */
	public void setPOIType(int pOIType) {
		POIType = pOIType;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return createTime
	 */
	public Integer getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}
