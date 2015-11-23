package com.daoke.mobileserver.audio.controller;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午11:13:12
 * @version 1.0
 */
@Controller
public class AudioController {

	private final Logger logger = Logger.getLogger(AudioController.class);

	@Value("#{apiConfig[url2multimedia]}")
	private String url2multimedia;

	@Autowired
	private AudioService audioService;

	/**
	 * amr转mp3
	 * 
	 * @param appKey
	 * @param secret
	 * @param fileURLS
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/url2multimedia")
	public String url2multimedia(String appKey, String fileURLS) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = audioService.url2multimedia(appkey, secret, fileURLS, url2multimedia);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

}
