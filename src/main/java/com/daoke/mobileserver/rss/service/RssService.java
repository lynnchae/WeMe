package com.daoke.mobileserver.rss.service;



/**
 * 
 * @author wangliming
 * @date 2014-9-26 下午2:26:02
 * @version 1.0
 */
public interface RssService {

	/**
	 * 获取可用RSS源列表和用户已订阅的源列表
	 * 
	 * @param accountID
	 * @param getChannels
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getChannels(String accountID, String getChannels) throws Exception;

	/**
	 * 获取可用RSS源列表和用户已订阅的源列表
	 * 
	 * @param accountID
	 * @param getVoiceChannels
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getVoiceChannels(String accountID, String getVoiceChannels) throws Exception;

	/**
	 * 保存个人订阅的新闻源
	 * 
	 * @param accountID
	 * @param channels
	 * @param subChannels
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String subChannels(String accountID, String channels, String subChannels)
			throws Exception;

	/**
	 * 保存个人订阅的新闻源
	 * 
	 * @param accountID
	 * @param channels
	 * @param subVoiceChannels
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String subVoiceChannels(String accountID, String channels, String subVoiceChannels)
			throws Exception;

	/**
	 * 频道设置
	 * 
	 * @param appKey
	 * @param secret
	 * @param channelID
	 * @param accountID
	 * @param channelset
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String channelset(String appKey, String secret, String channelID, String accountID,
                             String channelset) throws Exception;

	/**
	 * 设置频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelID
	 * @param actionType
	 * @param setChannel
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String setChannel(String appKey, String secret, String accountID, String channelID,
                             String actionType, String setChannel) throws Exception;

	/**
	 * 重置频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param resetChannel
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String resetChannel(String appKey, String secret, String accountID, String actionType,
                               String resetChannel) throws Exception;

	/**
	 * 查询用户当前频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getUserChannel
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getUserChannel(String appKey, String secret, String accountID,
                                 String getUserChannel) throws Exception;

	/**
	 * 查询频道用户列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelID
	 * @param getChannelUserList
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getChannelUserList(String appKey, String secret, String accountID,
                                     String channelID, String getChannelUserList) throws Exception;
}
