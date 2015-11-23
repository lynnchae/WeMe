package com.daoke.mobileserver.dianping.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.dianping.domain.DianPing;
import com.daoke.mobileserver.dianping.service.DianPingService;
import com.daoke.mobileserver.report.service.MirrTalkService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.push.PushResult;


/**
 *
 * @author wangliming
 * @date 2014-9-10 下午3:08:34
 * @version 1.0
 */
@Controller
public class DianPingController {

	private static final Logger logger = Logger.getLogger(DianPingController.class);
	
	@Autowired
	private DianPingService dianPingService;
	
	@Autowired
	private MirrTalkService mirrTalkService;
	
	/**
	 * 查找停车场
	 * 
	 * @param accountID
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/findParking")
	public String findParking(String latitude, String longitude, HttpServletResponse response){
		String result = null;
		try {
			result = dianPingService.findParking(latitude, longitude);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String status = jsonObject.getString("status");
			if (("OK").equals(status)) {
				JSONArray jsonArray = jsonObject.getJSONArray("businesses");
				List parkings = (List) JSONArray.toCollection(jsonArray);
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, parkings);
				response.getOutputStream().write(result.getBytes("utf-8"));
				logger.info(result);
				result = null;
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}
		return result;
	}
	
	/**
	 * 查找优惠劵
	 * 
	 * @param city
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/findCoupons")
	public String findCoupons(String city, String latitude, String longitude, HttpServletResponse response){
		String result = null;
		try {
			result = dianPingService.findCoupons("上海", latitude, longitude);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String status = jsonObject.getString("status");
			if (("OK").equals(status)) {
				JSONArray jsonArray = jsonObject.getJSONArray("coupons");
				List coupons = (List) JSONArray.toCollection(jsonArray);
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, coupons);
				response.getOutputStream().write(result.getBytes("utf-8"));
				logger.info(result);
				result = null;
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}
		return result;
	}
	
	/**
	 * 查找团购
	 * 
	 * @param city
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/findDeals")
	public String findDeals(String city, String latitude, String longitude, HttpServletResponse response){
		String result = null;
		try {
			result = dianPingService.findDeals(city, latitude, longitude);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String status = jsonObject.getString("status");
			if (("OK").equals(status)) {
				JSONArray jsonArray = jsonObject.getJSONArray("deals");
				List deals = (List) JSONArray.toCollection(jsonArray);
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, deals);
				response.getOutputStream().write(result.getBytes("utf-8"));
				logger.info(result);
				result = null;
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}
		return result;
	}
	
	/**
	 * 推送大众点评消息
	 * 停车场、优惠劵、团购
	 * 
	 * @param dianPing
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pushDianPingMessage")
	public String pushMessage(DianPing dianPing){
		String result = null;
		String msgTitle = null;
		String msgContent = null;
		int type = dianPing.getPOIType();
		try {
			if (type == 0) {
				msgTitle = "parking";
			}else if (type == 1) {
				msgTitle = "coupons";
			}else if (type == 2) {
				msgTitle = "deals";
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("city", dianPing.getCityName());
			jsonObject.put("latitude", dianPing.getLatitude());
			jsonObject.put("longitude", dianPing.getLongitude());
			msgContent = jsonObject.toString();
			PushResult pushMessage = mirrTalkService.pushMessage(msgTitle, msgContent, dianPing.getAccountID());
			result = pushMessage.toString();
		    dianPingService.addDianPing(dianPing);
			logger.info(result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(result);
		}
		return result;
	}
	
}
