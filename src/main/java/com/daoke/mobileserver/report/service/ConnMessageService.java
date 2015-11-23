package com.daoke.mobileserver.report.service;

import com.daoke.mobileserver.report.dto.ConnMessage;

import java.util.List;


/**
 *
 * @author wangliming
 * @date 2014-8-11 上午10:10:11
 * @version 1.0
 */
public interface ConnMessageService {

	/**
	 * 添加家人连线信息
	 * 
	 * @param connMessage
	 * @return
	 */
	boolean addConnMessage(ConnMessage connMessage);
	
	/**
	 * 根据手机imei获取连线消息
	 * 
	 * @param phoneImei
	 * @return
	 */
	List<ConnMessage> getConnMessages(String phoneImei, String accountID);
	
	/**
	 * 根据手机imei获取
	 * 
	 * @param phoneImei
	 * @return
	 */
	boolean updateConnMessage(String phoneImei, String accountID);
	
}
