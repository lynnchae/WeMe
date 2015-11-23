package com.daoke.mobileserver.sharepoint.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.sharepoint.service.SharePointService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wangliming
 * @date 2014-10-22 下午5:07:50
 * @version 1.0
 */
@Service
public class SharePointServiceImpl implements SharePointService {

	@Override
	public String getGrowthInfo(String appKey, String secret, String accountID, String getGrowthInfo)
			throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getGrowthInfo);
		return result;
	}

	@Override
	public String getGrowthDetail(String appKey, String secret, String accountID, String startPage,
			String pageCount, String getGrowthDetail) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "startPage", "pageCount" };
		Object[] values = { appKey, secret, accountID, startPage, pageCount };
		String result = Sha1.httpPost(keys, values, getGrowthDetail);
		return result;
	}

	@Override
	public String getShareValue(String appKey, String secret, String accountID, String uniqueCode, String taskID,
			String obatainType, String getShareValue) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("taskID");
		keys.add("obtainType");
		
		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(taskID);
		values.add(obatainType);
		
		String[] keyContent = {"uniqueCode"};
		Object[] valueContent = {uniqueCode};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getShareValue);
		return result;
	}

	@Override
	public String getTaskList(String appKey, String secret, String accountID, String taskType,
			String getTaskList) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		
		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		
		String[] keyContent = {"taskType"};
		Object[] valueContent = {taskType};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getTaskList);
		return result;
	}

}
