package com.daoke.mobileserver.application.controller;

import com.daoke.mobileserver.application.service.ApplicationService;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午9:58:52
 * @version 1.0
 */
@Controller
public class ApplicationController {

	private final Logger logger = Logger.getLogger(ApplicationController.class);

	@Value("#{apiConfig[fetchPoweronDiary]}")
	private String fetchPoweronDiary;

	@Value("#{apiConfig[deletePoweronDiary]}")
	private String deletePoweronDiary;

	@Value("#{apiConfig[fetchCollectNews]}")
	private String fetchCollectNews;

	@Value("#{apiConfig[applyExchangeGoods]}")
	private String applyRepairWEME;

	@Autowired
	private ApplicationService applicationService;

	/**
	 * 获取用户开机3句话日记
	 * 
	 * @param appKey
	 * @param accountID
	 * @param currentPage
	 * @param maxCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fetchPoweronDiary")
	public String fetchPoweronDiary(String appKey, String accountID, String currentPage,
			String maxCount, String startTime, String endTime) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = applicationService.fetchPoweronDiary(appkey, secret, accountID, currentPage,
					maxCount, startTime, endTime, fetchPoweronDiary);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

	/**
	 * 删除用户开机3句话日记
	 * 
	 * @param appKey
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletePoweronDiary")
	public String deletePoweronDiary(String appKey, String accountID, String idx) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = applicationService.deletePoweronDiary(appkey, secret, accountID, idx,
					deletePoweronDiary);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

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
	@ResponseBody
	@RequestMapping("/fetchCollectNews")
	public String fetchCollectNews(String appKey, String accountID, String filterCollect,
			String curPage, String maxRecords, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
			try {
			result = applicationService.fetchCollectNews(appkey, secret, accountID, filterCollect,
					curPage, maxRecords, fetchCollectNews);
			response.getOutputStream().write(result.getBytes("utf-8"));
			String ERRORCODE = JSONObject.fromObject(result).getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				logger.info(appkey
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
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

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
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyExchangeGoodsTest")
	public CommonJsonResult applyExchangeGoodsTest(String appKey, String IMEI, String accountID,
			String depositPassword, String expressNumber, String expressCompany, String name,
			String telephone, String address, String exchangeReason) {
        CommonJsonResult jsonResult = new CommonJsonResult();
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = applicationService.applyExchangeGoods(appkey, secret, IMEI, accountID,
					depositPassword, expressNumber, expressCompany, name, telephone, address,
					exchangeReason, applyRepairWEME);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT(result);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {

            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("操作异常");
            logger.error(e.toString());
            e.printStackTrace();

		}

		return jsonResult;
	}


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
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyExchangeGoods")
    public String applyExchangeGoods(String appKey, String IMEI, String accountID,
                                               String depositPassword, String expressNumber, String expressCompany, String name,
                                               String telephone, String address, String exchangeReason) {
        String result = null;
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        try {
            result = applicationService.applyExchangeGoods(appkey, secret, IMEI, accountID,
                    depositPassword, expressNumber, expressCompany, name, telephone, address,
                    exchangeReason, applyRepairWEME);
            logger.info(appKey + "===>" + result);
        } catch (Exception e) {

            logger.error(e.toString());
            e.printStackTrace();

        }
        return result;
    }

}
