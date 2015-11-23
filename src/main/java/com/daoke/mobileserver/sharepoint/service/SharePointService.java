package com.daoke.mobileserver.sharepoint.service;

/**
 * 
 * @author wangliming
 * @date 2014-10-22 下午5:05:28
 * @version 1.0
 */
public interface SharePointService {

	/**
	 * 获取某用户的成长信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getGrowthInfo
	 * @return
	 * @throws Exception
	 */
	public String getGrowthInfo(String appKey, String secret, String accountID, String getGrowthInfo)
			throws Exception;

	/**
	 * 获取某用户的成长明细
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startPage
	 * @param pageCount
	 * @param getGrowthDetail
	 * @return
	 * @throws Exception
	 */
	public String getGrowthDetail(String appKey, String secret, String accountID, String startPage,
			String pageCount, String getGrowthDetail) throws Exception;

	/**
	 * 用户领取某任务的谢尔值
	 * 
	 * @param appKey
	 * @param secret
	 * @param uniqueCode
	 * @param taskID
	 * @param obatainType
	 * @param getShareValue
	 * @return
	 * @throws Exception
	 */
	public String getShareValue(String appKey, String secret, String accountID, String uniqueCode,
			String taskID, String obatainType, String getShareValue) throws Exception;

	/**
	 * 获取某用户的任务列表以及完成情况
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param taskType
	 * @param getTaskList
	 * @return
	 * @throws Exception
	 */
	public String getTaskList(String appKey, String secret, String accountID, String taskType,
			String getTaskList) throws Exception;
}
