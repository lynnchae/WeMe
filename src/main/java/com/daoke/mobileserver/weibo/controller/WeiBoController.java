package com.daoke.mobileserver.weibo.controller;

import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import com.daoke.mobileserver.weibo.service.WeiBoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author wangliming
 * @date 2014-11-12 上午10:43:42
 * @version 1.0
 */
@Controller
public class WeiBoController {

	private final Logger logger = Logger.getLogger(WeiBoController.class);
	
	@Value("#{apiConfig[sendSms]}")
	private String sendSms;
	
	@Value("#{contentConfig[message]}")
	private String content;
	
	@Autowired
	private WeiBoService weiBoService;
	
	@ResponseBody
	@RequestMapping("/sendSms")
	public String sendSms(String appKey, String mobile){
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			content = new String(content.getBytes("iso-8859-1"), "utf-8");
			result = weiBoService.sendSms(appkey, secret, mobile, content, sendSms);
			logger.info(result);
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
