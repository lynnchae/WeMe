package com.daoke.mobileserver.report.dao;


import com.daoke.mobileserver.report.dto.HomeMessage;

/**
 *
 * @author wangliming
 * @date 2014-8-8 下午5:22:39
 * @version 1.0
 */
public interface HomeConnDao {

	/**
	 * 添加家人连线用户信息
	 * 
	 * @param homeMessage
	 * @return
	 */
	boolean addHomeMessage(HomeMessage homeMessage);
	
	/**
	 * 根据accountID获取手机imei
	 * 
	 * @param accountID
	 * @return
	 */
	HomeMessage getHomeMessage(String accountID);
	
	/**
	 * 根据手机imei更新accountID
	 * 
	 * @param accountID
	 * @param phoneImei
	 * @return
	 */
	boolean updateHomeMessage(String accountID, String phoneImei);
	
	/**
	 * 根据accountID, phoneImei获取信息
	 * 
	 * @param accountID
	 * @return
	 */
	HomeMessage getHomeMessage(String accountID, String phoneImei);
}
