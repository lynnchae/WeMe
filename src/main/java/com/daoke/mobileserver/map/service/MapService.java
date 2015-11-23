package com.daoke.mobileserver.map.service;

import com.daoke.mobileserver.map.entity.PowerOffHotVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangliming
 * @date 2014-10-24 下午4:36:16
 * @version 1.0
 */
public interface MapService {

	/**
	 * 获取附近道客
	 * 
	 * @param appKey
	 * @param secret
	 * @param longitude
	 * @param latitude
	 * @param distance
	 * @param resultCount
	 * @param getNearbyDaoke
	 * @return
	 * @throws Exception 
	 */
	public String getNearbyDaoke(String appKey, String secret, String longitude, String latitude,
			String distance, String resultCount, String getNearbyDaoke) throws Exception;

	/**
	 * 获取用户当前位置信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getLocation
	 * @return
	 * @throws Exception
	 */
	public String getLocation(String appKey, String secret, String accountID, String getLocation)
			throws Exception;
	/**
	 * 查询热点
	 * @param accountID
	 * @return
	 */
	public Map<String,Object> getHotPoint(String accountID) throws ParseException;
    public  List<Map<String,String>> queryMapCityCode();
    public  List<Map<String,String>> queryUserArriveCity(String accountID);
}
