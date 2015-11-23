package com.daoke.mobileserver.dianping.dao;


import com.daoke.mobileserver.dianping.domain.DianPing;

/**
 *
 * @author wangliming
 * @date 2014-9-17 下午5:17:18
 * @version 1.0
 */
public interface DianPingDao {

	/**
	 * 添加经纬度信息
	 * 
	 * @param dianPing
	 * @return
	 */
	public boolean addDianPing(DianPing dianPing);
}
