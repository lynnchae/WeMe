package com.daoke.mobileserver.reward.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 *
 * @author wangliming
 * @date 2014-10-28 下午9:24:54
 * @version 1.0
 */
public interface RewardService {

	/**
	 * 申请提现
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param accountID
	 * @param depositPassword
	 * @param applyWithdrawAmount
	 * @param withdrawAccount
	 * @param withdrawAccountType
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String applyWithdraw(String appKey, String secret, String IMEI, String accountID,
                                String depositPassword, String applyWithdrawAmount, String withdrawAccount,
                                String withdrawAccountType, String applyWithdraw) throws Exception;
	
	/**
	 * 用户缴纳押金
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param depositType
	 * @param depositAmount
	 * @param payUserDeposit
	 * @return
	 * @throws Exception
	 */
	public String payUserDeposit(String appKey, String secret, String IMEI, String depositType,
                                 String depositAmount, String payUserDeposit) throws Exception;
	
	/**
	 * 查询用户资金变化明细
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startTime
	 * @param endTime
	 * @param startPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public String getBalanceDetail(String appKey, String secret, String accountID,
                                   String startTime, String endTime, String startPage, String pageCount, Short moneyType,
                                   String getBalanceDetail) throws Exception;
	
	/**
	 * 获得用户押金信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param getUserDepositInfo
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getUserDepositInfo(String appKey, String secret, String IMEI,Short moneyType ,
                                     String getUserDepositInfo) throws Exception;
	
	/**
	 * 获得押金类型信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param depositType
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getDepositTypeInfo(String appKey, String secret, String depositType,
                                     String getDepositTypeInfo) throws Exception;
	
	/**
	 * 获取用户当前资金信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getUserFinanceInfo
	 * @return
	 * @throws Exception
	 */
	public String getUserFinanceInfo(String appKey, String secret, String accountID,
                                     String moneyType, String getUserFinanceInfo) throws Exception;
	
	/**
	 * 检验是否设置押金密码
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param judgeExistPassword
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String judgeExistPassword(String appKey, String secret, String IMEI,
                                     String judgeExistPassword) throws Exception;
	
	/**
	 * 用户申请资金提现
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param daokePassword
	 * @param applyWithdrawAmount
	 * @param withdrawAccount
	 * @param withdrawAccountType
	 * @param applyWithdrawMoney
	 * @return
	 * @throws Exception
	 */
	public String applyWithdrawMoney(String appKey, String secret, String accountID,
                                     String daokePassword, String applyWithdrawAmount, String withdrawAccount,
                                     String withdrawAccountType,Short moneyType, String applyWithdrawMoney) throws Exception;
	
	/**
	 * 获得押金类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param startTime
	 * @param endTime
	 * @param startPage
	 * @param pageCount
	 * @param fetchDepositHisotry
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String fetchDepositHisotry(String appKey, String secret, String IMEI, String startTime,
                                      String endTime, String startPage, String pageCount,Short moneyType, String fetchDepositHisotry)
			throws Exception;
	
	/**
	 * 申请退货
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param accountID
	 * @param depositPassword
	 * @param expressNumber
	 * @param expressCompany
	 * @param applyCancelContract
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String applyCancelContract(String appKey, String secret, String IMEI, String accountID,
                                      String depositPassword, String expressNumber, String expressCompany,
                                      String returnReason, String applyCancelContract) throws Exception;
	
	/**
	 * 用户申请押金提现
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param depositPassword
	 * @param applyWithdrawAmount
	 * @param autoWithdraw
	 * @param accountID
     * @param moneyType
	 * @param applyWithdrawDeposit
	 * @return
	 * @throws Exception
	 */
	public String applyWithdrawDeposit(String appKey, String secret, String IMEI,
                                       String depositPassword, String applyWithdrawAmount, String autoWithdraw,
                                       String accountID,Short moneyType, String applyWithdrawDeposit) throws Exception;
	
	/**
	 * 确认换货申请
	 * 
	 * @param appKey
	 * @param secret
	 * @param oldIMEI
	 * @param newIMEI
	 * @param confirmExchangeGoods
	 * @return
	 * @throws Exception
	 */
	public String confirmExchangeGoods(String appKey, String secret, String oldIMEI,
                                       String newIMEI, String confirmExchangeGoods) throws Exception;

	/**
	 * 设置押金密码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param IMEI
	 * @param oldPassword
	 * @param newPassword
	 * @param updateDepositPassword
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String updateDepositPassword(String appKey, String secret, String accountID,
                                        String IMEI, String oldPassword, String newPassword, String updateDepositPassword)
			throws Exception;
	
	/**
	 * 用户未使用直接退货
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param expressNumber
	 * @param expressCompany
	 * @param returnGoodsWithoutUse
	 * @return
	 * @throws Exception
	 */
	public String returnGoodsWithoutUse(String appKey, String secret, String IMEI,
                                        String expressNumber, String expressCompany, String returnGoodsWithoutUse)
			throws Exception;
	
	/**
	 * 获得收入
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param mileage
	 * @param getRewardAmountByMileage
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getRewardAmountByMileage(String appKey, String secret, String accountID,
                                           String mileage,Short moneyType, String getRewardAmountByMileage) throws Exception;
}
