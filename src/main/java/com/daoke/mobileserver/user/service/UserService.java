package com.daoke.mobileserver.user.service;


import cn.jpush.api.push.PushResult;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.report.dto.AssociateAccount;
import com.daoke.mobileserver.user.entity.MessageCentre;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午10:20:35
 * @version 1.0
 */
public interface UserService {

	/**
	 * 通过用户账号编号获得imei和手机号码
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getImeiPhone
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getImeiPhone(String appKey, String secret, String accountID, String getImeiPhone)
			throws Exception;

	/**
	 * 通过imei获取语境用户信息
	 *
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param getMirrtalkInfoByImei
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getMirrtalkInfoByImei(String appKey, String secret, String IMEI,
										String getMirrtalkInfoByImei) throws Exception;

	/**
	 * 绑定用户账号
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param IMEI
	 * @param userBindAccountMirrtalk
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String userBindAccountMirrtalk(String appKey, String secret, String accountID,
										  String IMEI, String userBindAccountMirrtalk) throws Exception;

	/**
	 * 用户绑定语镜账号和IMEI
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param IMEI
	 * @param userBindThirdAccountMirrtalk
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String userBindThirdAccountMirrtalk(String appKey, String secret, String accountID,
											   String IMEI, String userBindThirdAccountMirrtalk) throws Exception;

	/**
	 * 修改用户信息
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param name
	 * @param nickname
	 * @param gender
	 * @param plateNumber
	 * @param drivingLicense
	 * @param homeAddress
	 * @param fixUserInfo
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String fixUserInfo(String appKey, String secret, String accountID, String name,
							  String nickname, String gender, String plateNumber, String drivingLicense,
							  String homeAddress, String fixUserInfo) throws Exception;

	/**
	 * 获取用户信息
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param getUserInfo
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getUserInfo(String appKey, String secret, String accountID, String getUserInfo)
			throws Exception;

	/**
	 * 核对用户登录
	 *
	 * @param appKey
	 * @param secret
	 * @param username
	 * @param daokePassword
	 * @param clientIP
	 * @param checkLogin
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String checkLogin(String appKey, String secret, String username, String daokePassword,
							 String clientIP, String checkLogin) throws Exception;

	/**
	 * 核对该手机是否已注册
	 *
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param checkRegistration
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String checkRegistration(String appKey, String secret, String mobile,
									String checkRegistration) throws Exception;

	/**
	 * 获取手机验证码
	 *
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param content
	 * @param getMobileVerificationCode
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String getMobileVerificationCode(String appKey, String secret, String mobile,
											String content, String getMobileVerificationCode) throws Exception;

	/**
	 * 添加用户
	 *
	 * @param appKey
	 * @param secret
	 * @param daokePassword
	 * @param accountType
	 * @param nickname
	 * @param mobile
	 * @param addCustomAccount
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String addCustomAccount(String appKey, String secret, String daokePassword,
								   String accountType, String nickname, String mobile, String addCustomAccount)
			throws Exception;

	/**
	 * 添加用户自定义账号
	 *
	 * @param appKey
	 * @param secret
	 * @param username
	 * @param mobile
	 * @param userEmail
	 * @param daokePassword
	 * @param accountType
	 * @param nickname
	 * @param gender
	 * @param name
	 * @param plateNumber
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String addThirdCustomAccount(String appKey, String secret, String username, String mobile, String userEmail, String daokePassword, String accountType, String nickname, String gender,
										String name, String plateNumber, String addThirdCustomAccount)
			throws Exception;
//	public String addCustomAccount(String appKey, String secret, String daokePassword,
//                                   String accountType, String nickname, String mobile, String addCustomAccount)
//			throws Exception;

	/**
	 * 重置用户密码
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param resetUserPassword
	 * @return
	 * @throws Exception
	 */
	public String resetUserPassword(String appKey, String secret, String accountID,
									String resetUserPassword) throws Exception;

	/**
	 * 为用户更新密码
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param oldPassword
	 * @param newPassword
	 * @param updateUserPassword
	 * @return
	 * @throws Exception
	 */
	public String updateUserPassword(String appKey, String secret, String accountID,
									 String oldPassword, String newPassword, String updateUserPassword) throws Exception;

	/**
	 * 验证手机号并修改密码
	 *
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param accountID
	 * @param oldPassword
	 * @param newPassword
	 * @param verifyEmailOrMobile
	 * @param updateUserPassword
	 * @return 验证结果
	 * @throws Exception
	 */
	public String verifyAndreset(String appKey, String secret, String mobile, String accountID,
								 String oldPassword, String newPassword, String verifyEmailOrMobile,
								 String updateUserPassword) throws Exception;

	/**
	 * 验证手机号或邮箱
	 *
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param accountID
	 * @param verifyEmailOrMobile
	 * @return
	 * @throws Exception
	 */
	public String verifyEmailOrMobile(String appKey, String secret, String mobile,
									  String accountID, String verifyEmailOrMobile) throws Exception;

	/**
	 * 根据注册手机号获取账号编号
	 *
	 * @param appKey
	 * @param secret
	 * @param mobile
	 * @param getAccountIDFromMobile
	 * @return
	 * @throws Exception
	 */
	public String getAccountIDFromMobile(String appKey, String secret, String mobile,
										 String getAccountIDFromMobile) throws Exception;

	/**
	 * 绑定第三方帐户与语镜帐号
	 *
	 * @param associateAccount
	 * @param associateAccountWithAccountID
	 * @return
	 * @throws Exception
	 */
	public String associateAccountWithAccountID(AssociateAccount associateAccount,
												String associateAccountWithAccountID) throws Exception;

	/**
	 * 根据第三方账户创建语镜帐号
	 *
	 * @param appKey
	 * @param secret
	 * @param account
	 * @param loginType
	 * @param token
	 * @param accessToken
	 * @param accessTokenExpiration
	 * @param nickname
	 * @param createAccountID
	 * @return
	 * @throws Exception
	 */
	public String createAccountID(String appKey, String secret, String account, String loginType,
								  String token, String accessToken, String accessTokenExpiration, String nickname,
								  String username, String daokePassword, String createAccountID) throws Exception;

	/**
	 * 根据第三方的账户（如微信、QQ等）创建语镜帐号
	 *
	 * @param appKey
	 * @param secret
	 * @param account
	 * @param loginType
	 * @param nickname
	 * @param token
	 * @param accessToken
	 * @param accessTokenExpiration
	 * @param refreshToken
	 * @return
	 * @throws Exception
	 */
	public String createThirdAccountID(String appKey, String secret, String account, String loginType, String nickname,
									   String token, String accessToken, String accessTokenExpiration, String refreshToken,
									   String createThirdAccountID) throws Exception;

	/**
	 * 解绑
	 *
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param disconnectAccount
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String disconnectAccount(String appKey, String secret, String accountID,
									String disconnectAccount) throws Exception;

	/**
	 * 判断给定imei是否允许用户绑定
	 *
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param checkImei
	 * @return
	 * @throws Exception
	 */
	public String checkImei(String appKey, String secret, String IMEI, String checkImei)
			throws Exception;

	/**
	 * 判断给定imei是否允许用户绑定
	 *
	 * @param appKey
	 * @param secret
	 * @param deviceID
	 * @param model
	 * @param associateDeviceIDWithImei
	 * @return
	 * @throws Exception
	 */
	public String associateDeviceIDWithImei(String appKey, String secret, String deviceID, String model, String associateDeviceIDWithImei)
			throws Exception;

	/**
	 * 发送推送消息
	 *
	 * @param msgTitle
	 * @param userID
	 * @param pushType
	 * @return
	 * @throws Exception
	 */
	public Integer pushMessage(String msgTitle, String userID, String alert, String pushType, Map<String, Object> params)
			throws Exception;

	public Integer pushMessage(String msgTitle, String accountID, String alert, String pushType, Map<String, Object> params, String senderAccountID)
			throws Exception;

	/**
	 * 查询推送消息
	 *
	 * @param accountID
	 * @param messageType
	 * @return
	 */
	public PageList<MessageCentre> queryMessageCentre(String accountID, String messageType, Integer toPage, Integer pageSize);

	/**
	 * 修改消息状态为已读
	 *
	 * @param messageID
	 */
	public void updateMessageIsRead(Integer messageID);

	public void insert(MessageCentre messageContre);

    /**
     * 插入验证码
     * @param map
     * @return
     */
    public int insertIdentifyingCode(String mobile,String validateCode,String type) ;


	/**
	 * 插入验证码
	 * @param mobile
	 * @param validateCode
	 * @param type
	 * @param validTime 失效时间
	 * @return
	 */
	public int insertIdentifyingCode(String mobile,String validateCode,String type,String validTime) ;

    public int findValidCode(String validateCode,String mobilePhone);

	/**
	 *删除消息
	 * @param messageCentreIDs
	 */
	public void removeMessageCentre(Integer[] messageCentreIDs,String accountID);

	/**
	 * 修改信息同意拒绝状态
	 * @param messageID
	 * @param c
	 */
	public void updateMessageIsAgree(Integer messageID, char c);

	/**
	 * 查询最新消息中心条数
	 * @param accountID
	 * @return
	 */
	public int countNewMessageCentre(String accountID);
}
