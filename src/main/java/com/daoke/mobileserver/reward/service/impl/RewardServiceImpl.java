package com.daoke.mobileserver.reward.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.reward.service.RewardService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-10-28 下午9:26:15
 * @version 1.0
 */
@Service
public class RewardServiceImpl implements RewardService {

	@Override
	public String applyWithdraw(String appKey, String secret, String IMEI, String accountID,
			String depositPassword, String applyWithdrawAmount, String withdrawAccount,
			String withdrawAccountType, String applyWithdraw) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("IMEI");
		keys.add("accountID");
		keys.add("depositPassword");
		keys.add("applyWithdrawAmount");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(IMEI);
		values.add(accountID);
		values.add(depositPassword);
		values.add(applyWithdrawAmount);

		String[] keyContent = { "withdrawAccount", "withdrawAccountType" };
		Object[] valueContent = { withdrawAccount, "1" };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), applyWithdraw);
		return result;
	}

	@Override
	public String payUserDeposit(String appKey, String secret, String IMEI, String depositType,
			String depositAmount, String payUserDeposit) throws Exception {
		String[] keys = { "appKey", "secret", "IMEI", "depositType", "depositAmount" };
		Object[] values = { appKey, secret, IMEI, depositType, depositAmount };
		String result = Sha1.httpPost(keys, values, payUserDeposit);
		return result;
	}

	@Override
	public String getBalanceDetail(String appKey, String secret, String accountID,
			String startTime, String endTime, String startPage, String pageCount,  Short moneyType,
			String getBalanceDetail) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "startTime", "endTime", "startPage", "pageCount" ,"moneyType"};
		Object[] valueContent = { startTime, endTime, startPage, pageCount ,moneyType};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getBalanceDetail);
		return result;
	}
	
	@Override
	public String getUserDepositInfo(String appKey, String secret, String IMEI,Short moneyType,
			String getUserDepositInfo) throws Exception {
		String[] keys = { "appKey", "secret", "IMEI" ,"moneyType"};
		Object[] values = { appKey, secret, IMEI ,moneyType};
		String result = Sha1.httpPost(keys, values, getUserDepositInfo);
		return result;
	}
	
	@Override
	public String getDepositTypeInfo(String appKey, String secret, String depositType,
			String getDepositTypeInfo) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);

		String[] keyContent = { "depositType" };
		Object[] valueContent = { depositType };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getDepositTypeInfo);
		return result;
	}
	
	@Override
	public String getUserFinanceInfo(String appKey, String secret, String accountID,
			String moneyType, String getUserFinanceInfo) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		String[] keyContent = { "moneyType" };
		Object[] valueContent = { moneyType };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				getUserFinanceInfo);
		return result;
	}
	
	
	@Override
	public String judgeExistPassword(String appKey, String secret, String IMEI,
			String judgeExistPassword) throws Exception {
		String[] keys = { "appKey", "secret", "IMEI" };
		Object[] values = { appKey, secret, IMEI };
		String result = Sha1.httpPost(keys, values, judgeExistPassword);
		return result;
	}
	
	@Override
	public String applyWithdrawMoney(String appKey, String secret, String accountID,
			String daokePassword, String applyWithdrawAmount, String withdrawAccount,
			String withdrawAccountType,Short moneyType, String applyWithdrawMoney) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "daokePassword", "applyWithdrawAmount",
				"withdrawAccount", "withdrawAccountType" ,"moneyType"};
		Object[] values = { appKey, secret, accountID, daokePassword, applyWithdrawAmount,
				withdrawAccount, withdrawAccountType, moneyType};
		String result = Sha1.httpPost(keys, values, applyWithdrawMoney);
		return result;
	}

	@Override
	public String fetchDepositHisotry(String appKey, String secret, String IMEI, String startTime,
			String endTime, String startPage, String pageCount,Short moneyType, String fetchDepositHisotry)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("IMEI");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(IMEI);

		String[] keyContent = { "startTime", "endTime", "startPage", "pageCount","moneyType" };
		Object[] valueContent = { startTime, endTime, startPage, pageCount,moneyType };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchDepositHisotry);
		return result;
	}
	
	@Override
	public String applyCancelContract(String appKey, String secret, String IMEI, String accountID,
			String depositPassword, String expressNumber, String expressCompany,
			String returnReason, String applyCancelContract) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("IMEI");
		keys.add("accountID");
		keys.add("depositPassword");
		keys.add("expressNumber");
		keys.add("expressCompany");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(IMEI);
		values.add(accountID);
		values.add(depositPassword);
		values.add(expressNumber);
		values.add(expressCompany);

		String[] keyContent = { "returnReason" };
		Object[] valueContent = { returnReason };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				applyCancelContract);
		return result;
	}
	
	@Override
	public String applyWithdrawDeposit(String appKey, String secret, String IMEI,
			String depositPassword, String applyWithdrawAmount, String autoWithdraw,
			String accountID,Short moneyType, String applyWithdrawDeposit) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("IMEI");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(IMEI);
		values.add(accountID);

		String[] keyContent = { "depositPassword", "applyWithdrawAmount", "autoWithdraw","moneyType" };
		String[] valueContent = { depositPassword, applyWithdrawAmount, autoWithdraw ,moneyType+""};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				applyWithdrawDeposit);
		return result;
	}

	@Override
	public String confirmExchangeGoods(String appKey, String secret, String oldIMEI,
			String newIMEI, String confirmExchangeGoods) throws Exception {
		String[] keys = { "appKey", "secret", "oldIMEI", "newIMEI" };
		Object[] values = { appKey, secret, oldIMEI, newIMEI };
		String result = Sha1.httpPost(keys, values, confirmExchangeGoods);
		return result;
	}
	
	@Override
	public String updateDepositPassword(String appKey, String secret, String accountID,
			String IMEI, String oldPassword, String newPassword, String updateDepositPassword)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");
		keys.add("IMEI");
		keys.add("newPassword");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);
		values.add(IMEI);
		values.add(newPassword);

		String[] keyContent = { "oldPassword" };
		Object[] valueContent = { oldPassword };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				updateDepositPassword);
		return result;
	}
	
	@Override
	public String returnGoodsWithoutUse(String appKey, String secret, String IMEI,
			String expressNumber, String expressCompany, String returnGoodsWithoutUse)
			throws Exception {
		String[] keys = { "appKey", "secret", "IMEI", "expressNumber", "expressCompany" };
		Object[] values = { appKey, secret, IMEI, expressNumber, expressCompany };
		String result = Sha1.httpPost(keys, values, returnGoodsWithoutUse);
		return result;
	}
	
	@Override
	public String getRewardAmountByMileage(String appKey, String secret, String accountID,
			String mileage,Short moneyType, String getRewardAmountByMileage) throws Exception {
        String result = null;
        if(moneyType == null){
            String[] keys = { "appKey", "secret", "accountID", "mileage" };
            Object[] values = { appKey, secret, accountID, mileage };
             result = Sha1.httpPost(keys, values, getRewardAmountByMileage);
        } else{
            String[] keys = { "appKey", "secret", "accountID", "mileage","moneyType" };
            Object[] values = { appKey, secret, accountID, mileage ,moneyType};
             result = Sha1.httpPost(keys, values, getRewardAmountByMileage);
        }

		return result;
	}

}
