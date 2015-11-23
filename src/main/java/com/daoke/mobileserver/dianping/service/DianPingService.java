package com.daoke.mobileserver.dianping.service;


import com.daoke.mobileserver.dianping.domain.DianPing;

/**
 *
 * @author wangliming
 * @date 2014-9-10 下午2:58:35
 * @version 1.0
 */
public interface DianPingService {

	/**
	 * 查询停车场
	 * 
	 * @param latitude
	 * @param longitude
	 * @param category
	 * @return
	 * @throws Exception 
	 */
	public String findParking(String latitude, String longitude) throws Exception;
	
	/**
	 * 查询优惠劵
	 * 
	 * @param city
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception 
	 */
	public String findCoupons(String city, String latitude, String longitude) throws Exception;
	
	/**
	 * 查询团购
	 * 
	 * @param city
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception 
	 */
	public String findDeals(String city, String latitude, String longitude) throws Exception;
	
	/**
	 * 添加经纬度信息
	 * 
	 * @param dianPing
	 * @return
	 */
	public boolean addDianPing(DianPing dianPing);
}
