package com.daoke.mobileserver.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.data.service.DataService;
import com.daoke.mobileserver.util.AbDateUtil;
import com.daoke.mobileserver.util.Sha1;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-10-28 下午7:41:29
 * @version 1.0
 */
@Service
public class DataServiceImpl implements DataService {

	@Override
	public String getGrade(String appKey, String secret, String accountID, String getGrade)
			throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getGrade);
		return result;
	}
	
	@Override
	public String getRouteList(String appKey, String secret, String accountID, String pageNumber,
			String pageSize, String sort, String startTime, String endTime, String getRouteList)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("pageNumber");
		keys.add("pageSize");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(pageNumber);
		values.add(pageSize);

		String[] keyContent = { "sort", "startTime", "endTime" };
		Object[] valueContent = { sort, startTime, endTime };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getRouteList);
		return result;
	}
	
	@Override
	public String getGradeByTravelID(String appKey, String secret, String travelID,
			String getGradeByTravelID) throws Exception {
		String[] keys = { "appKey", "secret", "travelID" };
		Object[] values = { appKey, secret, travelID };
		String result = Sha1.httpPost(keys, values, getGradeByTravelID);
		return result;
	}
	
	@Override
	public String getTravelByAccount(String appKey, String secret, String accountID,
			String startTime, String endTime, String getTravelByAccount) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "startTime", "endTime" };
		Object[] values = { appKey, secret, accountID, startTime, endTime };
		String result = Sha1.httpPost(keys, values, getTravelByAccount);
		return result;
	}
	
	@Override
	public String getMileageForAccount(String appKey, String secret, String accountID,
			String getMileageForAccount) throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getMileageForAccount);
		return result;
	}

	@Override
	public String getRouteWayToRoadList(String appKey, String secret, String travelID, String sort,
			String startTime, String endTime, String getRouteWayToRoadList) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("travelID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(travelID);

		String[] keyContent = { "sort", "startTime", "endTime" };
		Object[] valueContent = { sort, startTime, endTime };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getRouteWayToRoadList);
		return result;
	}
	
	@Override
	public String getSumMileageList(String appKey, String secret, String accountID,
			String tokenCode, String dtype, String startTime, String endTime,
			String getSumMileageList) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);

		String[] keyContent = { "accountID", "tokenCode", "dtype", "startTime", "endTime" };
		Object[] valueContent = { accountID, tokenCode, dtype, startTime, endTime };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getSumMileageList);
		return result;
	}

	@Override
	public String getRankInfo(String appKey, String secret, String pageNumber, String pageSize,
			String startTime, String sort, String accountID, String getRankInfo) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("pageNumber");
		keys.add("pageSize");
		keys.add("startTime");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add("1");
		values.add("20");
		values.add(startTime);

		String[] keyContent = { "sort", "accountID" };
		Object[] valueContent = { sort, accountID };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getRankInfo);
		return result;
	}
	
	@Override
	public String queryTweetCountRank(String appKey, String secret, String dateTime,
			String queryType, String accountID, String queryTweetCountRank) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("dateTime");
		keys.add("queryType");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(dateTime);
		values.add(queryType);

		String[] keyContent = { "accountID" };
		Object[] valueContent = { accountID };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				queryTweetCountRank);
		return result;
	}
	
	@Override
	public String getLastFansTotal(String appKey, String secret, String accountID,
			String getLastFansTotal) throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getLastFansTotal);
		return result;
	}
	
}
