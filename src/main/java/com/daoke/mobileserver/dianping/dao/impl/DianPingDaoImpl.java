package com.daoke.mobileserver.dianping.dao.impl;

import com.daoke.mobileserver.dianping.dao.DianPingDao;
import com.daoke.mobileserver.dianping.domain.DianPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 *
 * @author wangliming
 * @date 2014-9-17 下午5:18:55
 * @version 1.0
 */
@Repository
public class DianPingDaoImpl implements DianPingDao {

	@Autowired
	private JdbcTemplate wemeJdbcTemplate;
	
	@Override
	public boolean addDianPing(DianPing dianPing) {
		Long createTime = System.currentTimeMillis() / 1000;
		String sql = "insert into dianping(id, accountID, latitude, longitude, " +
				"cityCode, cityName, POIType, createTime) values (null, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = { dianPing.getAccountID(), dianPing.getLatitude(),
				dianPing.getLongitude(), dianPing.getCityCode(), dianPing.getCityName(),
				dianPing.getPOIType(), createTime };
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

}
