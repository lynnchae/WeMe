package com.daoke.mobileserver.application.service;

/**
 *
 * @author wangliming
 * @date 2014-10-28 下午9:56:48
 * @version 1.0
 */
public interface ApplicationService {

	/**
	 * 获取用户开机3句话日记
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param currentPage
	 * @param maxCount
	 * @param fetchPoweronDiary
	 * @return
	 * @throws Exception
	 */
	public String fetchPoweronDiary(String appKey, String secret, String accountID,
			String currentPage, String maxCount, String startTime, String endTime,
			String fetchPoweronDiary) throws Exception;
	
	/**
	 * 删除用户开机3句话日记
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param id
	 * @param deletePoweronDiary
	 * @return
	 * @throws Exception
	 */
	public String deletePoweronDiary(String appKey, String secret, String accountID, String idx,
			String deletePoweronDiary) throws Exception;
	
	/**
	 * 获取收藏新闻
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param curPage
	 * @param maxRecords
	 * @return
	 * @throws Exception
	 */
	public String fetchCollectNews(String appKey, String secret, String accountID,
			String filterCollect, String curPage, String maxRecords, String fetchCollectNews)
			throws Exception;
	
	/**
	 * 申请换货
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param accountID
	 * @param depositPassword
	 * @param expressNumber
	 * @param expressCompany
	 * @param name
	 * @param telephone
	 * @param address
	 * @param exchangeReason
	 * @param applyExchangeGoods
	 * @return
	 * @throws Exception
	 */
	public String applyExchangeGoods(String appKey, String secret, String IMEI, String accountID,
			String depositPassword, String expressNumber, String expressCompany, String name,
			String telephone, String address, String exchangeReason, String applyExchangeGoods)
			throws Exception;

}
