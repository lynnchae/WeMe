package com.daoke.mobileserver.dianping.service.impl;

import com.daoke.mobileserver.dianping.dao.DianPingDao;
import com.daoke.mobileserver.dianping.domain.DianPing;
import com.daoke.mobileserver.dianping.service.DianPingService;
import com.daoke.mobileserver.util.DianPingSha1;
import com.daoke.mobileserver.util.DianPingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-9-10 下午3:01:29
 * @version 1.0
 */
@Service
public class DianPingServiceImpl implements DianPingService {

	@Autowired
	private DianPingDao dianPingDao;
	
	@Override
	public String findParking(String latitude, String longitude) throws Exception {
		String[] keys = {"latitude", "longitude", "category"};
		Object[] values = {latitude, longitude, "停车场"};
		String result = DianPingSha1.httpGet(DianPingUtil.appKey, DianPingUtil.secret, keys, values, DianPingUtil.PARKINGURL);
		return result;
	}

	@Override
	public String findCoupons(String city, String latitude, String longitude) throws Exception {
		String[] keys = {"latitude", "longitude", "city"};
		Object[] values = {latitude, longitude, city};
		String result = DianPingSha1.httpGet(DianPingUtil.appKey, DianPingUtil.secret, keys, values, DianPingUtil.COUPONSURL);
		return result;
	}

	@Override
	public String findDeals(String city, String latitude, String longitude) throws Exception {
		String[] keys = {"latitude", "longitude", "city"};
		Object[] values = {latitude, longitude, city};
		String result = DianPingSha1.httpGet(DianPingUtil.appKey, DianPingUtil.secret, keys, values, DianPingUtil.DEALSURL);
		return result;
	}

	@Override
	public boolean addDianPing(DianPing dianPing) {
		return dianPingDao.addDianPing(dianPing);
	}

}
