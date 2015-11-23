package com.daoke.mobileserver.user.service.impl;

import java.util.*;

import cn.jpush.api.push.PushResult;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.report.dto.AssociateAccount;
import com.daoke.mobileserver.user.dao.IMessageCentreDao;
import com.daoke.mobileserver.user.dao.IUserDao;
import com.daoke.mobileserver.user.dao.IUserGradeDao;
import com.daoke.mobileserver.user.entity.MessageCentre;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.JsonMapper;
import com.daoke.mobileserver.util.SendMessageUtil;
import com.daoke.mobileserver.util.Sha1;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wangliming
 * @version 1.0
 * @date 2014-9-26 上午10:20:54
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private IMessageCentreDao messageCentreDao;

	@Autowired
	private IUserGradeDao userGradeDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public String getImeiPhone(String appKey, String secret, String accountID, String getImeiPhone)
			throws Exception {
		String[] keys = {"appKey", "secret", "accountID"};
		Object[] values = {appKey, secret, accountID};
		String result = Sha1.httpPost(keys, values, getImeiPhone);
		return result;
	}

	@Override
	public String getMirrtalkInfoByImei(String appKey, String secret, String IMEI,
										String getMirrtalkInfoByImei) throws Exception {
		String[] keys = {"appKey", "secret", "IMEI"};
		Object[] values = {appKey, secret, IMEI};
		String result = Sha1.httpPost(keys, values, getMirrtalkInfoByImei);
		return result;
	}

	@Override
	public String userBindAccountMirrtalk(String appKey, String secret, String accountID,
										  String IMEI, String userBindThirdAccountMirrtalk) throws Exception {
		String[] keys = {"appKey", "secret", "accountID", "IMEI"};
		Object[] values = {appKey, secret, accountID, IMEI};
		String result = Sha1.httpPost(keys, values, userBindThirdAccountMirrtalk);
		return result;
	}

	@Override
	public String userBindThirdAccountMirrtalk(String appKey, String secret, String accountID,
											   String IMEI, String userBindThirdAccountMirrtalk) throws Exception {
		String[] keys = {"appKey", "secret", "accountID", "IMEI"};
		Object[] values = {appKey, secret, accountID, IMEI};
		String result = Sha1.httpPost(keys, values, userBindThirdAccountMirrtalk);
		return result;
	}

	@Override
	public String fixUserInfo(String appKey, String secret, String accountID, String name,
							  String nickname, String gender, String plateNumber, String drivingLicense,
							  String homeAddress, String fixUserInfo) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = {"name", "nickname", "gender", "plateNumber", "drivingLicense",
				"homeAddress"};
		Object[] valueContent = {name, nickname, gender, plateNumber, drivingLicense, homeAddress};
		Sha1.addContent(keys, values, keyContent, valueContent);

		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), fixUserInfo);
		return result;
	}

	@Override
	public String getUserInfo(String appKey, String secret, String accountID, String getUserInfo)
			throws Exception {
		String[] keys = {"appKey", "secret", "accountID"};
		Object[] values = {appKey, secret, accountID};
		String result = Sha1.httpPost(keys, values, getUserInfo);
		return result;
	}

	@Override
	public String checkLogin(String appKey, String secret, String username, String daokePassword,
							 String clientIP, String checkLogin) throws Exception {
		String[] keys = {"appKey", "secret", "username", "daokePassword", "clientIP"};
		Object[] values = {appKey, secret, username, daokePassword, clientIP};
		String result = Sha1.httpPost(keys, values, checkLogin);
		return result;
	}

	@Override
	public String checkRegistration(String appKey, String secret, String mobile,
									String checkRegistration) throws Exception {
		String[] keys = {"appKey", "secret", "username"};
		Object[] values = {appKey, secret, mobile};
		String result = Sha1.httpPost(keys, values, checkRegistration);
		return result;
	}

	@Override
	public String getMobileVerificationCode(String appKey, String secret, String mobile,
											String content, String getMobileVerificationCode) throws Exception {
		String[] keys = {"appKey", "secret", "mobile", "content"};
		Object[] values = {appKey, secret, mobile, content};
		String result = Sha1.httpPost(keys, values, getMobileVerificationCode);
		return result;
	}

	@Override
	public String addCustomAccount(String appKey, String secret, String daokePassword,
								   String accountType, String nickname, String mobile, String addCustomAccount)
			throws Exception {
		String[] keys = {"appKey", "secret", "daokePassword", "accountType", "nickname", "mobile"};
		Object[] values = {appKey, secret, daokePassword, accountType, nickname, mobile};
		String result = Sha1.httpPost(keys, values, addCustomAccount);
		return result;
	}

	@Override
	public String addThirdCustomAccount(String appKey, String secret, String username, String mobile, String userEmail, String daokePassword, String accountType, String nickname,
										String gender, String name, String plateNumber, String addThirdCustomAccount)
			throws Exception {
		List<String> keys = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		keys.add("appKey");
		values.add(appKey);
		keys.add("secret");
		values.add(secret);
		if (StringUtils.isNotBlank(username)) {
			keys.add("username");
			values.add(username);
		}
		if (StringUtils.isNotBlank(mobile)) {
			keys.add("mobile");
			values.add(mobile);
		}
		if (StringUtils.isNotBlank(userEmail)) {
			keys.add("userEmail");
			values.add(userEmail);
		}
		keys.add("daokePassword");
		values.add(daokePassword);
		keys.add("accountType");
		values.add(accountType);
		keys.add("nickname");
		values.add(nickname);
		if (StringUtils.isNotBlank(gender)) {
			keys.add("gender");
			values.add(gender);
		}
		if (StringUtils.isNotBlank(name)) {
			keys.add("name");
			values.add(name);
		}
		if (StringUtils.isNotBlank(plateNumber)) {
			keys.add("plateNumber");
			values.add(plateNumber);
		}
//		String[] keys = { "appKey","secret", "username", "mobile", "userEmail", "daokePassword","accountType","nickname","gender","name","plateNumber"};
//
//		Object[] values = { appKey,secret, username, mobile, userEmail, daokePassword,accountType,nickname,gender,name,plateNumber };

		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), addThirdCustomAccount);
		return result;
	}
//	public String addCustomAccount(String appKey, String secret, String daokePassword,
//			String accountType, String nickname, String mobile, String addCustomAccount)
//			throws Exception {
//		String[] keys = { "appKey", "secret", "daokePassword", "accountType", "nickname", "mobile" };
//		Object[] values = { appKey, secret, daokePassword, accountType, nickname, mobile };
//		String result = Sha1.httpPost(keys, values, addCustomAccount);
//		return result;
//	}

	@Override
	public String resetUserPassword(String appKey, String secret, String accountID,
									String resetUserPassword) throws Exception {
		String[] keys = {"appKey", "secret", "accountID"};
		Object[] values = {appKey, secret, accountID};
		String result = Sha1.httpPost(keys, values, resetUserPassword);
		return result;
	}

	@Override
	public String updateUserPassword(String appKey, String secret, String accountID,
									 String oldPassword, String newPassword, String updateUserPassword) throws Exception {
		String[] keys = {"appKey", "secret", "accountID", "oldPassword", "newPassword"};
		Object[] values = {appKey, secret, accountID, oldPassword, newPassword};
		String result = Sha1.httpPost(keys, values, updateUserPassword);
		return result;
	}

	@Override
	public String verifyAndreset(String appKey, String secret, String mobile, String accountID,
								 String oldPassword, String newPassword, String verifyEmailOrMobile,
								 String updateUserPassword) throws Exception {
		String result = verifyEmailOrMobile(appKey, secret, mobile, accountID, verifyEmailOrMobile);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String ERRORCODE = jsonObject.getString("ERRORCODE");
		if (("0").equals(ERRORCODE)) {
			result = updateUserPassword(appKey, secret, accountID, "", newPassword,
					updateUserPassword);
		}
		return result;
	}

	@Override
	public String verifyEmailOrMobile(String appKey, String secret, String mobile,
									  String accountID, String verifyEmailOrMobile) throws Exception {
		String[] keys = {"appKey", "secret", "mobile", "accountID"};
		Object[] values = {appKey, secret, mobile, accountID};
		String result = Sha1.httpPost(keys, values, verifyEmailOrMobile);
		return result;
	}

	@Override
	public String getAccountIDFromMobile(String appKey, String secret, String mobile,
										 String getAccountIDFromMobile) throws Exception {
		String[] keys = {"appKey", "secret", "mobile"};
		Object[] values = {appKey, secret, mobile};
		String result = Sha1.httpPost(keys, values, getAccountIDFromMobile);
		return result;
	}

	@Override
	public String associateAccountWithAccountID(AssociateAccount associateAccount,
												String associateAccountWithAccountID) throws Exception {
		String[] keys = {"appKey", "secret", "accountID", "account", "loginType", "token",
				"refreshToken", "accessToken", "accessTokenExpiration"};
		Object[] values = {associateAccount.getAppKey(), associateAccount.getSecret(),
				associateAccount.getAccountID(), associateAccount.getAccount(),
				associateAccount.getLoginType(), associateAccount.getToken(),
				associateAccount.getRefreshToken(), associateAccount.getAccessToken(),
				associateAccount.getAccessTokenExpiration()};
		String result = Sha1.httpPost(keys, values, associateAccountWithAccountID);
		return result;
	}

	@Override
	public String createAccountID(String appKey, String secret, String account, String loginType,
								  String token, String accessToken, String accessTokenExpiration, String nickname,
								  String username, String daokePassword, String createAccountID) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("account");
		keys.add("loginType");
		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(account);
		values.add(loginType);
		String[] keyContent = {"token", "accessToken", "accessTokenExpiration", "nickname", "username", "daokePassword"};
		Object[] valueContent = {token, accessToken, accessTokenExpiration, nickname, username, daokePassword};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				createAccountID);
		return result;
	}

	@Override
	public String createThirdAccountID(String appKey, String secret, String account, String loginType, String nickname,
									   String token, String accessToken, String accessTokenExpiration, String refreshToken,
									   String createThirdAccountID) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("account");
		keys.add("loginType");
		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(account);
		values.add(loginType);
		String[] keyContent = {"nickname", "token", "accessToken", "accessTokenExpiration", "refreshToken"};
		Object[] valueContent = {nickname, token, accessToken, accessTokenExpiration, refreshToken};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				createThirdAccountID);
		return result;
	}

	@Override
	public String disconnectAccount(String appKey, String secret, String accountID,
									String disconnectAccount) throws Exception {
		String[] keys = {"appKey", "secret", "accountID"};
		Object[] values = {appKey, secret, accountID};
		String result = Sha1.httpPost(keys, values, disconnectAccount);
		return result;
	}

	@Override
	public String checkImei(String appKey, String secret, String IMEI, String checkImei)
			throws Exception {
		String[] keys = {"appKey", "secret", "IMEI"};
		Object[] values = {appKey, secret, IMEI};
		String result = Sha1.httpPost(keys, values, checkImei);
		return result;

	}

	@Override
	public String associateDeviceIDWithImei(String appKey, String secret, String deviceID, String model, String associateDeviceIDWithImei) throws Exception {
		String[] keys = {"appKey", "secret", "deviceID", "model"};
		Object[] values = {appKey, secret, deviceID, model};
		String result = Sha1.httpPost(keys, values, associateDeviceIDWithImei);
		return result;
	}


	@Override
	public Integer pushMessage(String msgTitle, String accountID, String alert, String pushType, Map<String, Object> params)
			throws Exception {
		SendMessageUtil.pushMessage(msgTitle, accountID, alert, pushType, params);
//		StringBuffer sb = new StringBuffer();
//		if (params != null && params.size() != 0) {
//			Set<Map.Entry<String, Object>> paramsEntry = params.entrySet();
//			for (Map.Entry<String, Object> paramsMap : paramsEntry) {
//				String key = paramsMap.getKey();
//				String value = paramsMap.getValue().toString();
//				if (sb == null || StringUtils.isBlank(sb.toString())) {
//					sb.append(key).append("=").append(value);
//				} else {
//					sb.append("&").append(key).append("=").append(value);
//				}
//			}
//		}
		String paramsJson = "";
		if (params != null && params.size() != 0) {
			paramsJson = JsonMapper.toJson(params, false);
		}
		//保存消息中心
		MessageCentre messageContre = new MessageCentre();
		messageContre.setIsValid(1);
		messageContre.setCreateTime(System.currentTimeMillis() / 1000);
		messageContre.setAccountID(accountID);
		messageContre.setContent(alert);
		messageContre.setParam(paramsJson);
		messageContre.setMsgTitle(msgTitle);
		messageContre.setMessageType(pushType);
		messageContre.setIsRead(0);
		messageCentreDao.insert(messageContre);
		return messageContre.getId();
	}

	@Override
	public Integer pushMessage(String msgTitle, String accountID, String alert, String pushType, Map<String, Object> params, String senderAccountID)
			throws Exception {
		PushResult result = SendMessageUtil.pushMessage(msgTitle, accountID, alert, pushType, params);
		String paramsJson = "";
		if (params != null && params.size() != 0) {
			paramsJson = JsonMapper.toJson(params, false);
		}
		//保存消息中心
		MessageCentre messageContre = new MessageCentre();
		messageContre.setIsValid(1);
		messageContre.setCreateTime(System.currentTimeMillis() / 1000);
		messageContre.setAccountID(accountID);
		messageContre.setContent(alert);
		messageContre.setParam(paramsJson);
		messageContre.setMsgTitle(msgTitle);
		messageContre.setSenderAccountID(senderAccountID);
		messageContre.setMessageType(pushType);
		messageContre.setIsRead(0);
		messageCentreDao.insert(messageContre);
		return messageContre.getId();
	}

	@Override
	public PageList<MessageCentre> queryMessageCentre(String accountID, String messageType, Integer toPage, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("accountID", accountID);
		map.put("messageType", messageType);
		return messageCentreDao.queryMessageCentre(map, toPage, pageSize);
	}

	@Override
	public void updateMessageIsRead(Integer messageID) {
		messageCentreDao.updateMessageIsRead(messageID);
	}

	@Override
	public void insert(MessageCentre messageContre) {
		messageCentreDao.insert(messageContre);
	}

	/**
	 * 记录验证码信息
	 *
	 * @param map
	 * @return
	 */
	@Override
	public int insertIdentifyingCode(String mobile, String validateCode, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("validateCode", validateCode);
		map.put("type", type);
		map.put("validTime", 30);
		return userDao.insertIdentifyingCode(map);
	}


	/**
	 * 记录验证码信息
	 *
	 * @param map
	 * @return
	 */
	@Override
	public int insertIdentifyingCode(String mobile, String validateCode, String type,String validTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("validateCode", validateCode);
		map.put("type", type);
		map.put("validTime", validTime);
		return userDao.insertIdentifyingCode(map);
	}

	public int findValidCode(String validateCode, String mobilePhone) {
		Map map = new HashMap();
		map.put("validateCode", validateCode);
		map.put("mobilePhone", mobilePhone);
		return userDao.findValidCode(map);
	}

	@Override
	public void removeMessageCentre( Integer[] messageCentreIDs,String accountID) {
		List<Integer> messageCentreIDList =null;
		if(messageCentreIDs!=null) {
			messageCentreIDList = Arrays.asList(messageCentreIDs);
		}
		messageCentreDao.removeMessageCentre(messageCentreIDList,accountID);
	}

	@Override
	public void updateMessageIsAgree(Integer messageID, char c) {
		messageCentreDao.updateMessageIsAgree(messageID,c);
	}

	@Override
	public int countNewMessageCentre(String accountID) {

		return messageCentreDao.countNewMessageCentre(accountID);
	}
}
