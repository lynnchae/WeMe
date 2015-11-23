package com.daoke.mobileserver.channel.service;

/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午7:15:38
 * @version 1.0
 */
public interface ChannelService {

	/**
	 * 用户通过APP申请微频道/密频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param channelName
	 * @param channelIntroduction
	 * @param channelCityCode
	 * @param channelCatalog
	 * @param channelType
	 * @param openType
	 * @param chiefAnnouncerIntr
	 * @param channelLogoURL
	 * @param userApplyChannel
	 * @return
	 * @throws Exception
	 */
	public String applyMicroChannel(String appKey, String secret, String accountID,
			String channelNumber, String channelName, String channelIntroduction,
			String chiefAnnouncerIntr, String channelCityCode, String channelCatalogID,
			String channelCatalogUrl, String applyMicroChannel) throws Exception;

	/**
	 * 获取微频道/密频道列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startPage
	 * @param pageCount
	 * @param channelType
	 * @param fetchUserChannel
	 * @return
	 * @throws Exception
	 */
	public String fetchMicroChannel(String appKey, String secret, String accountID,
			String startPage, String pageCount, String infoType, String channelStatus,
			String citycode, String likeName, String catalogid,
			String fetchMicroChannel) throws Exception;

	/**
	 * 创建微频道/密频道的的邀请唯一码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param customType
	 * @param createChannel
	 * @return
	 * @throws Exception
	 */
	public String createChannel(String appKey, String secret, String accountID,
			String channelNumber, String customType, String createChannel) throws Exception;

	/**
	 * 删除微频道/密频道的的邀请唯一码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param inviteUniqueCode
	 * @param customType
	 * @param delChannel
	 * @return
	 * @throws Exception
	 */
	public String delChannel(String appKey, String secret, String accountID, String channelNumber,
			String inviteUniqueCode, String customType, String delChannel) throws Exception;

	/**
	 * 获取微频道/密频道的的邀请唯一码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param customType
	 * @param startPage
	 * @param pageCount
	 * @param filterValidity
	 * @param getChannel
	 * @return
	 * @throws Exception
	 */
	public String getChannel(String appKey, String secret, String accountID, String channelNumber,
			String customType, String startPage, String pageCount, String filterValidity,
			String getChannel) throws Exception;
}
