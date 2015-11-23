package com.daoke.mobileserver.note.controller;

import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.note.service.NoteService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-10-29 上午9:43:08
 * @version 1.0
 */
@Controller
public class NoteController {

	private final Logger logger = Logger.getLogger(NoteController.class);

	@Value("#{apiConfig[updateVoiceNotepad]}")
	private String updateVoiceNotepad;

	@Value("#{apiConfig[deleteVoiceNotepad]}")
	private String deleteVoiceNotepad;

	@Value("#{apiConfig[fetchVoiceNotepad]}")
	private String fetchVoiceNotepad;

	@Autowired
	private NoteService noteService;

	/**
	 * 更新用户语音记事本备注
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param idx
	 * @param remark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateVoiceNotepad")
	public String updateVoiceNotepad(String appKey, String accountID, String idx, String remark) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = noteService.updateVoiceNotepad(appkey, secret, accountID, idx, remark,
					updateVoiceNotepad);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

	/**
	 * 删除用户语音记事本
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param idx
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteVoiceNotepad")
	public String deleteVoiceNotepad(String appKey, String accountID, String idx) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = noteService.deleteVoiceNotepad(appkey, secret, accountID, idx,
					deleteVoiceNotepad);
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

	/**
	 * 获取用户语音记事本内容
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param currentPage
	 * @param maxCount
	 * @param remark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fetchVoiceNotepad")
	public String fetchVoiceNotepad(String appKey, String accountID, String currentPage,
			String maxCount, String remark, String fileType, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = noteService.fetchVoiceNotepad(appkey, secret, accountID, currentPage,
					maxCount, remark, fileType, fetchVoiceNotepad);
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
			logger.error(appKey + "===>" + result);
		}

		return result;
	}

}
