package com.daoke.mobileserver.reward.controller;

import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.reward.service.RewardService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午9:27:17
 * @version 1.0
 */
@Controller
public class RewardController {

	private final Logger logger = Logger.getLogger(RewardController.class);

	@Value("#{apiConfig[applyWithdraw ]}")
	private String applyWithdraw;

	@Value("#{apiConfig[payUserDeposit]}")
	private String payUserDeposit;

	@Value("#{apiConfig[getBalanceDetail]}")
	private String getBalanceDetail;

	@Value("#{apiConfig[getUserDepositInfo]}")
	private String getUserDepositInfo;

	@Value("#{apiConfig[getDepositTypeInfo]}")
	private String getDepositTypeInfo;

	@Value("#{apiConfig[getUserFinanceInfo]}")
	private String getUserFinanceInfo;

	@Value("#{apiConfig[judgeExistPassword]}")
	private String judgeExistPassword;

	@Value("#{apiConfig[applyWithdrawMoney]}")
	private String applyWithdrawMoney;

	@Value("#{apiConfig[fetchDepositHisotry]}")
	private String fetchDepositHisotry;

	@Value("#{apiConfig[applyCancelContract]}")
	private String applyCancelContract;

	@Value("#{apiConfig[applyWithdrawDeposit]}")
	private String applyWithdrawDeposit;

	@Value("#{apiConfig[confirmExchangeGoods]}")
	private String confirmExchangeGoods;

	@Value("#{apiConfig[updateDepositPassword]}")
	private String updateDepositPassword;

	@Value("#{apiConfig[returnGoodsWithoutUse]}")
	private String returnGoodsWithoutUse;

	@Value("#{apiConfig[getRewardAmountByMileage]}")
	private String getRewardAmountByMileage;

	@Autowired
	private RewardService rewardService;

	/**
	 * 申请提现
	 * 
	 * @param appKey
	 * @param IMEI
	 * @param accountID
	 * @param depositPassword
	 * @param applyWithdrawAmount
	 * @param withdrawAccount
	 * @param withdrawAccountType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyWithdraw")
	public String applyWithdraw(String appKey, String IMEI, String accountID,
			String depositPassword, String applyWithdrawAmount, String withdrawAccount,
			String withdrawAccountType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.applyWithdraw(appkey, secret, IMEI, accountID, depositPassword,
					applyWithdrawAmount, withdrawAccount, withdrawAccountType, applyWithdraw);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 用户缴纳押金
	 * 
	 * @param appKey
	 * @param IMEI
	 * @param depositType
	 * @param depositAmount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/payUserDeposit")
	public String payUserDeposit(String appKey, String IMEI, String depositType,
			String depositAmount) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.payUserDeposit(appkey, secret, IMEI, depositType, depositAmount,
					payUserDeposit);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 查询用户资金变化明细
	 * 
	 * @param appKey
	 * @param accountID
	 * @param startTime
	 * @param endTime
	 * @param startPage
	 * @param pageCount
     * @param moneyType  若输入资金类型（1密点、2微点、3（微点和密点同时返回）），4保险金，按实际金额做返回；
     *                   （在原有的moneyType类型上添加）5密点的实际金额（单位：元）、6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBalanceDetail")
	public String getBalanceDetail(String appKey, String accountID, String startTime,
			String endTime, String startPage, String pageCount, HttpServletResponse response, Short moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.getBalanceDetail(appkey, secret, accountID, startTime, endTime,
					startPage, pageCount,moneyType, getBalanceDetail);
			response.getOutputStream().write(result.getBytes("utf-8"));
			String ERRORCODE = JSONObject.fromObject(result).getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				logger.info(appKey
						+ "===>"
						+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
								ConstantsUtil.RESULT_OK));
			} else {
				logger.warn(appKey + "===>" + result);
			}
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 获得用户押金信息
	 * 
	 * @param appKey
	 * @param IMEI
     * @param moneyType  若输入资金类型（1密点、2微点、3（微点和密点同时返回）），
     *                   4保险金，按实际金额做返回；（在原有的moneyType类型上添加）5密点的实际金额（单位：元）、
     *                   6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserDepositInfo")
	public String getUserDepositInfo(String appKey, String IMEI,Short moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.getUserDepositInfo(appkey, secret, IMEI, moneyType,getUserDepositInfo);
			JSONObject resultJson = JSONObject.fromObject(result).getJSONObject("RESULT");
			String returnType = resultJson.getString("returnType");
			if (("2").equals(returnType)) {
				resultJson.put("depositType", "2");
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, resultJson);
			}
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 获得押金类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param depositType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDepositTypeInfo")
	public String getDepositTypeInfo(String appKey, String depositType, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.getDepositTypeInfo(appkey, secret, depositType,
					getDepositTypeInfo);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(appKey + "===>" + result);
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 获取用户当前资金信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
     * @param moneyType  若输入资金类型（1密点、2微点、3（微点和密点同时返回）），
     *                   4保险金，按实际金额做返回；（在原有的moneyType类型上添加）5密点的实际金额（单位：元）、
     *                   6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserFinanceInfo")
	public String getUserFinanceInfo(String appKey, String accountID, String moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.getUserFinanceInfo(appkey, secret, accountID, moneyType,
					getUserFinanceInfo);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
            e.printStackTrace();
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 检验是否设置押金密码
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/judgeExistPassword")
	public String judgeExistPassword(String appKey, String IMEI) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.judgeExistPassword(appkey, secret, IMEI, judgeExistPassword);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 用户申请资金提现
	 * 
	 * @param appKey
	 * @param accountID
	 * @param daokePassword
	 * @param applyWithdrawAmount
	 * @param withdrawAccount
	 * @param withdrawAccountType
     * @param moneyType 若输入资金类型（1密点、2微点、3（微点和密点同时返回）），4保险金，按实际金额做返回；（在原有的moneyType类型上添加）5密点的实际金额（单位：元）、
     *                  6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyWithdrawMoney")
	public String applyWithdrawMoney(String appKey, String accountID, String daokePassword,
			String applyWithdrawAmount, String withdrawAccount, String withdrawAccountType,Short moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.applyWithdrawMoney(appkey, secret, accountID, daokePassword,
					applyWithdrawAmount, withdrawAccount, withdrawAccountType,moneyType, applyWithdrawMoney);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 获得押金历史
	 * 
	 * @param appKey
	 * @param IMEI
	 * @param startTime
	 * @param endTime
	 * @param startPage
	 * @param pageCount
     * @param moneyType  若输入资金类型（1密点、2微点、3（微点和密点同时返回）），
     *                   4保险金，按实际金额做返回；（在原有的moneyType类型上添加）5密点的实际金额（单位：元）、
     *                   6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））

     * @return
	 */
	@ResponseBody
	@RequestMapping("/fetchDepositHisotry")
	public String fetchDepositHisotry(String appKey, String IMEI, String startTime, String endTime,
			String startPage, String pageCount,Short moneyType, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.fetchDepositHisotry(appkey, secret, IMEI, startTime, endTime,
					startPage, pageCount,moneyType, fetchDepositHisotry);
			response.getOutputStream().write(result.getBytes("utf-8"));
			String ERRORCODE = JSONObject.fromObject(result).getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				logger.info(appKey
						+ "===>"
						+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
								ConstantsUtil.RESULT_OK));
			} else {
				logger.warn(appKey + "===>" + result);
			}
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 申请退货
	 * 
	 * @param appKey
	 * @param IMEI
	 * @param accountID
	 * @param depositPassword
	 * @param expressNumber
	 * @param expressCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyCancelContract")
	public String applyCancelContract(String appKey, String IMEI, String accountID,
			String depositPassword, String expressNumber, String expressCompany, String returnReason) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.applyCancelContract(appkey, secret, IMEI, accountID,
					depositPassword, expressNumber, expressCompany, returnReason,
					applyCancelContract);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 用户申请押金提现
	 * 
	 * @param appKey
	 * @param IMEI
	 * @param depositPassword
	 * @param applyWithdrawAmount
	 * @param autoWithdraw
	 * @param accountID
     * @param moneyType  若输入资金类型（1密点、2微点、3（微点和密点同时返回）），
     *                   4保险金，按实际金额做返回；（在原有的moneyType类型上添加）5密点的实际金额（单位：元）、
     *                   6微点的实际金额（单位：元），无资金类型参数（微点和密点的实际金额（单位：元））
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyWithdrawDeposit")
	public String applyWithdrawDeposit(String appKey, String IMEI, String depositPassword,
			String applyWithdrawAmount, String autoWithdraw, String accountID,Short moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.applyWithdrawDeposit(appkey, secret, IMEI, depositPassword,
					applyWithdrawAmount, autoWithdraw, accountID,moneyType, applyWithdrawDeposit);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 确认换货申请
	 * 
	 * @param appKey
	 * @param secret
	 * @param oldIMEI
	 * @param newIMEI
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/confirmExchangeGoods")
	public String confirmExchangeGoods(String appKey, String oldIMEI, String newIMEI) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.confirmExchangeGoods(appkey, secret, oldIMEI, newIMEI,
					confirmExchangeGoods);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

	/**
	 * 设置押金密码
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param IMEI
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateDepositPassword")
	public String updateDepositPassword(String appKey, String accountID, String IMEI,
			String oldPassword, String newPassword) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.updateDepositPassword(appkey, secret, accountID, IMEI,
					oldPassword, newPassword, updateDepositPassword);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}

	/**
	 * 用户未使用直接退货
	 * 
	 * @param appKey
	 * @param secret
	 * @param IMEI
	 * @param expressNumber
	 * @param expressCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/returnGoodsWithoutUse")
	public String returnGoodsWithoutUse(String appKey, String IMEI, String expressNumber,
			String expressCompany) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.returnGoodsWithoutUse(appkey, secret, IMEI, expressNumber,
					expressCompany, returnGoodsWithoutUse);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.toString());
		}

		return result;
	}

	/**
	 * 获得收入
	 * 
	 * @param appKey
	 * @param accountID
	 * @param mileage
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRewardAmountByMileage")
	public String getRewardAmountByMileage(String appKey, String accountID, String mileage,Short moneyType) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = rewardService.getRewardAmountByMileage(appkey, secret, accountID, mileage, moneyType,
					getRewardAmountByMileage);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							e.toString()));
		}

		return result;
	}
}
