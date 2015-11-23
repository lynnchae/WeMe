package com.daoke.mobileserver.data.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午7:40:57
 * @version 1.0
 */
public interface DataService {

	/**
	 * 获取驾驶评分
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getGrade
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getGrade(String appKey, String secret, String accountID, String getGrade)
			throws Exception;

	/**
	 * 查询某用户的行程
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param startTime
	 * @param endTime
	 * @param getRouteList
	 * @return
	 * @throws Exception
	 */
	public String getRouteList(String appKey, String secret, String accountID, String pageNumber,
                               String pageSize, String sort, String startTime, String endTime, String getRouteList)
			throws Exception;

	/**
	 * 获取用户单次途径评分
	 * 
	 * @param appKey
	 * @param secret
	 * @param travelID
	 * @param getGradeByTravelID
	 * @return
	 * @throws Exception
	 */
	public String getGradeByTravelID(String appKey, String secret, String travelID,
                                     String getGradeByTravelID) throws Exception;

	/**
	 * 用户途径道路
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startTime
	 * @param endTime
	 * @param getTravelByAccount
	 * @return
	 * @throws Exception
	 */
	public String getTravelByAccount(String appKey, String secret, String accountID,
                                     String startTime, String endTime, String getTravelByAccount) throws Exception;

	/**
	 * 获得里程
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getMileageForAccount
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getMileageForAccount(String appKey, String secret, String accountID,
                                       String getMileageForAccount) throws Exception;

	/**
	 * 查询某用户的某行程途径道路集合
	 * 
	 * @param appKey
	 * @param secret
	 * @param travelID
	 * @param sort
	 * @param startTime
	 * @param endTime
	 * @param getRouteWayToRoadList
	 * @return
	 * @throws Exception
	 */
	public String getRouteWayToRoadList(String appKey, String secret, String travelID, String sort,
                                        String startTime, String endTime, String getRouteWayToRoadList) throws Exception;

	/**
	 * 获取总的里程列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param tokenCode
	 * @param dtype
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public String getSumMileageList(String appKey, String secret, String accountID,
                                    String tokenCode, String dtype, String startTime, String endTime,
                                    String getSumMileageList) throws Exception;

	/**
	 * 查询某用户的驾驶里程和时长排名
	 * 
	 * @param appKey
	 * @param secret
	 * @param pageNumber
	 * @param pageSize
	 * @param startTime
	 * @param sort
	 * @param accountID
	 * @param getRankInfo
	 * @return
	 * @throws Exception
	 */
	public String getRankInfo(String appKey, String secret, String pageNumber, String pageSize,
                              String startTime, String sort, String accountID, String getRankInfo) throws Exception;

	/**
	 * 查询吐槽排名
	 * 
	 * @param appKey
	 * @param secret
	 * @param dateTime
	 * @param queryType
	 * @param accountID
	 * @param queryTweetCountRank
	 * @return
	 * @throws Exception
	 */
	public String queryTweetCountRank(String appKey, String secret, String dateTime,
                                      String queryType, String accountID, String queryTweetCountRank) throws Exception;
	
	/**
	 * 获取单次开关机内粉丝的总数及增长数
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getLastFansTotal
	 * @return
	 * @throws Exception
	 */
	public String getLastFansTotal(String appKey, String secret, String accountID,
                                   String getLastFansTotal) throws Exception;
	
}
