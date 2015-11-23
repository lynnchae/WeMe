package com.daoke.mobileserver.custom.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author wangliming
 * @date 2014-10-29 上午10:11:02
 * @version 1.0
 */
public interface CustomService {

	/**
	 * 用户获取已经设置的参数
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param actionType
	 * @return
	 * @throws Exception
	 */
	public String getCustomInfo(String appKey, String secret, String accountID, String actionType,
                                String getCustomInfo) throws Exception;

	/**
	 * 用户设置
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param actionType
	 * @param customType
	 * @param customParameter
	 * @param setCustomInfo
	 * @return
	 * @throws Exception
	 */
	public String setCustomInfo(String appKey, String secret, String accountID, String actionType,
                                String customType, String customParameter, String setCustomInfo) throws Exception;

	/**
	 * 获取接受微博类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getSubscribeMsg
	 * @return
	 * @throws Exception
	 */
	public String getSubscribeMsg(String appKey, String secret, String accountID,
                                  String getSubscribeMsg) throws Exception;

	/**
	 * 设置接受微博类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param subParameter
	 * @param setSubscribeMsg
	 * @return
	 * @throws Exception
	 */
	public String setSubscribeMsg(String appKey, String secret, String accountID,
                                  String subParameter, String setSubscribeMsg) throws Exception;

	/**
	 * 建立家人连线
	 * 
	 * @param appKey
	 * @param secret
	 * @param file
	 * @param accountID
	 * @param parameterType
	 * @param callbackURL
	 * @param appConnectSendWeibo
	 * @return
	 * @throws Exception
	 */
	public String appConnectSendWeibo(String appKey, String secret, String multimediaURL,
                                      String accountID, String parameterType, String callbackURL, String appConnectSendWeibo)
			throws Exception;

	/**
	 * 关注微频道API
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param uniqueCode
	 * @param followType
	 * @param followUserChannel
	 * @return
	 * @throws Exception
	 */
	public String followMicroChannel(String appKey, String secret, String accountID,
                                     String uniqueCode, String followType, String channelNumber, String followMicroChannel)
			throws Exception;

	/**
	 * 取消关注微频道API
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param followType
	 * @param unFollowType
	 * @return
	 * @throws Exception
	 */
	public String unFollowUserChannel(String appKey, String secret, String accountID,
                                      String channelNumber, String followType, String unFollowType) throws Exception;

	/**
	 * 获取微频道关注的用户列表信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param startPage
	 * @param pageCount
	 * @param fetchFollowListUserChannel
	 * @return
	 * @throws Exception
	 */
	public String fetchFollowListUserChannel(String appKey, String secret, String accountID,
                                             String channelNumber, String startPage, String pageCount,
                                             String fetchFollowListUserChannel) throws Exception;

	/**
	 * 根据频道编码获取频道详情与关注(加入状态)
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	public String getMicroChannelInfo(String appKey, String secret, String accountID,
                                      String channelNumber, String getMicroChannelInfo) throws Exception;

	/**
	 * 用户查询自己关注的所有微频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getOwnerFollowListUserChannel
	 * @return
	 * @throws Exception
	 */
	public String getOwnerFollowListUserChannel(String appKey, String secret, String accountID,
                                                String startPage, String pageCount, String getOwnerFollowListUserChannel)
			throws Exception;

	/**
	 * 主播查询本频道所有关注用户列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param getOwnerUserChannelFollowList
	 * @return
	 * @throws Exception
	 */
	public String getOwnerUserChannelFollowList(String appKey, String secret, String accountID,
                                                String channelNumber, String startPage, String pageCount,
                                                String getOwnerUserChannelFollowList) throws Exception;

	/**
	 * 重置频道当前邀请的惟一码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param channelNumber
	 * @param channelType
	 * @param resetInviteUniqueCode
	 * @return
	 * @throws Exception
	 */
	public String resetInviteUniqueCode(String appKey, String secret, String accountID,
                                        String channelNumber, String channelType, String resetInviteUniqueCode)
			throws Exception;

	/**
	 * 获取微频道所有类别的列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param startPage
	 * @param pageCount
	 * @param getCatalogInfo
	 * @return
	 * @throws Exception
	 */
	public String getCatalogInfo(String appKey, String secret, String startPage, String pageCount,
                                 String getCatalogInfo) throws Exception;
	
	/**
	 * 获取待重审的微频道列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startPage
	 * @param pageCount
	 * @param getRecheckMicroChannelList
	 * @return
	 * @throws Exception
	 */
	public String getRecheckMicroChannelList(String appKey, String secret, String accountID, String startPage,
                                             String pageCount, String getRecheckMicroChannelList) throws Exception;
	
	
	/**
	 * 修改微频道/被驳回的频道
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param before_channelNumber
	 * @param after_channelNumber
	 * @param channelName
	 * @param channelIntroduction
	 * @param chiefAnnouncerIntr
	 * @param channelCityCode
	 * @param channelCatalogID
	 * @param channelCatalogUrl
	 * @param infoType
	 * @return
	 * @throws Exception
	 */
	public String modifyMicroChannel(String appKey, String secret, String accountID, String before_channelNumber,
                                     String after_channelNumber, String channelName, String channelIntroduction, String chiefAnnouncerIntr,
                                     String channelCityCode, String channelCatalogID, String channelCatalogUrl, String infoType,String applyIdx, String modifyMicroChannel) throws Exception;
	
	
	/**
	 * 存储任何文件类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param file
	 * @param saveFile
	 * @return
	 * @throws Exception
	 */
	public String saveFile(String appKey, String secret, MultipartFile file, String saveFile)
			throws Exception;
}



