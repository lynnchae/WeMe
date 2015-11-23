package com.daoke.mobileserver.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.application.service.ApplicationService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-10-28 下午9:57:38
 * @version 1.0
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public String fetchPoweronDiary(String appKey, String secret, String accountID,
			String currentPage, String maxCount, String startTime, String endTime,
			String fetchPoweronDiary) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "currentPage", "maxCount", "startTime", "endTime" };
		Object[] valueContent = { currentPage, maxCount, startTime, endTime };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchPoweronDiary);
		return result;
	}
	
	@Override
	public String deletePoweronDiary(String appKey, String secret, String accountID, String idx,
			String deletePoweronDiary) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "idx" };
		Object[] values = { appKey, secret, accountID, idx };
		String result = Sha1.httpPost(keys, values, deletePoweronDiary);
		return result;
	}
	
	@Override
	public String fetchCollectNews(String appKey, String secret, String accountID,
			String filterCollect, String curPage, String maxRecords, String fetchCollectNews)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "curPage", "maxRecords", "filterCollect" };
		Object[] valueContent = { curPage, maxRecords, filterCollect };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchCollectNews);
		return result;
	}
	
	@Override
	public String applyExchangeGoods(String appKey, String secret, String IMEI, String accountID,
			String depositPassword, String expressNumber, String expressCompany, String name,
			String telephone, String address, String exchangeReason, String applyExchangeGoods)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("IMEI");
		keys.add("accountID");
		keys.add("depositPassword");
		keys.add("expressNumber");
		keys.add("expressCompany");
		keys.add("name");
		keys.add("telephone");
		keys.add("address");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(IMEI);
		values.add(accountID);
		values.add(depositPassword);
		values.add(expressNumber);
		values.add(expressCompany);
		values.add(name);
		values.add(telephone);
		values.add(address);

		String[] keyContent = { "exchangeReason" };
		Object[] valueContent = { exchangeReason };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				applyExchangeGoods);
		return result;
	}
	
}
