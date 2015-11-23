package com.daoke.mobileserver.report.controller;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.custom.service.CustomService;
import com.daoke.mobileserver.data.service.DataService;
import com.daoke.mobileserver.exception.SystemException;
import com.daoke.mobileserver.report.dto.*;
import com.daoke.mobileserver.report.service.ConnMessageService;
import com.daoke.mobileserver.report.service.HomeConnService;
import com.daoke.mobileserver.report.service.MirrTalkService;
import com.daoke.mobileserver.reward.service.RewardService;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.jpush.api.push.PushResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * 语境控制层
 * 
 * @author wangliming
 * @date 2014-5-14 上午10:31:25
 * @version 1.0
 */
@Controller
public class MirrTalkController {

	private final Logger logger = LoggerFactory.getLogger(MirrTalkController.class);

	@Value("#{apiConfig[getMirrtalkInfoByImei]}")
	private String getMirrtalkInfoByImei;

	@Value("#{apiConfig[getMileageForAccount]}")
	private String getMileageForAccount;

	@Value("#{apiConfig[setCustomInfo]}")
	private String setCustomInfo;

	@Value("#{apiConfig[getAccountIDFromMobile]}")
	private String getAccountIDFromMobile;
	
	@Value("#{apiConfig[getRewardAmountByMileage]}")
	private String getRewardAmountByMileage;

	@Value("#{apiConfig[saveSound]}")
	private String saveSound;

	@Value("#{apiConfig[txt2voice]}")
	private String txt2voice;

	@Value("#{apiConfig[feedbackURL]}")
	private String feedbackURL;

	@Value("#{apiConfig[appConnectSendWeibo]}")
	private String appConnectSendWeibo;

	@Value("#{apiConfig[wav2voice]}")
	private String wav2voice;

    @Value("#{apiConfig[transcriptsImageUrl]}")
    private String transcriptsImageUrl;

    @Value("#{apiConfig[transcriptsUrl]}")
    private String transcriptsUrl;

	@Autowired
	private UserService userService;

	@Autowired
	private AudioService audioService;

	@Autowired
	private MirrTalkService mirrTalkService;

	@Autowired
	private HomeConnService homeConnService;

	@Autowired
	private ConnMessageService connMessageService;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private RewardService rewardService;
	
	@Autowired
	private CustomService customService;

    @Value("#{apiConfig[getUserInfo]}")
    private String getUserInfo;

	@ResponseBody
	@RequestMapping("/test")
	public String test() throws SystemException {
		throw new SystemException(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                ConstantsUtil.RESULT_SERVICE_ERROR));
	}

	/**
	 * 添加用户反馈信息
	 * 
	 * @param appKey
	 * @param accountID
	 * @param nickName
	 * @param suggestContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addSuggestion")
	public String addSuggestion(String appKey, String accountID, String nickName, String suggestContent) {
		String result = null;
		int phoneType = "iOS".equals(appKey) ? 1 : 0;
		boolean b = mirrTalkService.addSuggestion(accountID, nickName, suggestContent, phoneType);
		if (b) {
			result = JsonPuserUtil
					.jsonToString(ConstantsUtil.ERRORCODE_OK, ConstantsUtil.RESULT_OK);
			logger.info(result);
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(result);
		}

		return result;
	}

	/**
	 * 申请建立家人连线
	 * 
	 * @param appKey  平台类型  iOS Android
     * @param accountID 道客用户ID     微密IMEI/手机号  当  parameterType  为2 输入手机号 为3输入IMEI
     * @param parameterType 查询家人参数类型  手机号查询 输入"2"  根据IMEI查询  输入"3"
     * @param phoneImei  手机的设备号
     * @param name 用户名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyConnectSendWeibo")
	public String applyConnectSendWeibo(String appKey, String accountID, String parameterType,
			String phoneImei, String name, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			if (("2").equals(parameterType)) {
				result = userService.getAccountIDFromMobile(appkey, secret, accountID,
						getAccountIDFromMobile);
                CommonJsonResult commonJsonResult = (CommonJsonResult)JsonMapper.fromJson(result, CommonJsonResult.class);
                if(commonJsonResult.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)){
                    System.out.println(commonJsonResult.toString());
                    List list =(ArrayList)commonJsonResult.getRESULT();
                    if(list != null && list.size() > 0){
                        Map map =(Map)list.get(0);
                        accountID = map.get("accountID").toString();
                    }
                }
			} else if (("3").equals(parameterType)) {
				result = userService.getMirrtalkInfoByImei(appkey, secret, accountID,
						getMirrtalkInfoByImei);
                CommonJsonResult commonJsonResult = (CommonJsonResult)JsonMapper.fromJson(result, CommonJsonResult.class);
                if(commonJsonResult.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)){
                    Map map = (HashMap)commonJsonResult.getRESULT();
                    accountID = map.get("accountID").toString();
                }
			}
			parameterType = "1";
			HomeMessage homeMessages = homeConnService.getHomeMessage(accountID, phoneImei);
			//logger.info("homeMessages=" + homeMessages + "-----------------");
			if (homeMessages == null) {
				String callbackURL = feedbackURL + "/WeMe/pushWeiBoMessage.do?phoneImei="
						+ phoneImei;
				name = StringUtils.isEmpty(name) ? "某某某" : name;
				String txt = "尊敬的道客," + name + "申请与你建立家人连线，按加键表示同意，加加键表示拒绝！";
                //因失败率比较高,做重试机制,最多调用3次
                for(int i =0; i<3;i++){
                    result = audioService.txt2voice(appkey, secret, txt, txt2voice);
                    JSONObject jsonObject = JSONObject.fromObject(result);
                    if(jsonObject.getString("ERRORCODE").equals("0")){
                         break;
                    }
                }
				JSONObject jsonObject = JSONObject.fromObject(result);
				String ERRORCODE = jsonObject.getString("ERRORCODE");
                //如果生成语音失败，则采用默认语音   文本：尊敬的道客,神秘人物申请与你建立家人连线，按加键表示同意，加加键表示拒绝！
                String multimediaURL ="http://g4.tweet.daoke.me/group4/M04/E7/26/c-dJT1S40k6ARe4uAAAXiRT9O2g416.amr";
				if (("0").equals(ERRORCODE)) {
					 multimediaURL = jsonObject.getJSONObject("RESULT").getString("url");
				} else {
					logger.error(appKey + "===>" + result);
				}
                result = customService.appConnectSendWeibo(appkey, secret, multimediaURL,
                        accountID, parameterType, callbackURL, appConnectSendWeibo);
			} else {
				ApplyConn applyConn = new ApplyConn();
				applyConn.setAccountID(accountID);
				applyConn.setNickname(name);
				result = JsonPuserUtil.jsonToString("1", applyConn);
				response.getOutputStream().write(result.getBytes("utf-8"));
				//logger.info(appKey + "===>" + result);
				result = null;
			}
		} catch (Exception e) {
            e.printStackTrace();
			/*result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);*/
			logger.error(appKey + "===>" + result);
		}
		return result;
	}

	/**
	 * 建立家人连线
	 * 
	 * @param appKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/appConnectSendWeibo")
	public String appConnectSendWeibo(HttpServletRequest request, String appKey, String accountID, String parameterType,
			String phoneImei) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);

        MultipartFile file = null;
        try {
            //上传图片并返回url
            CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    file = multiRequest.getFile(iter.next());
                    break;
                }
            }
        } catch (Exception e1) {
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,   "图片保存失败");
            return result;
        }

		try {
			String callbackURL = feedbackURL + "/WeMe/pushWeiBoMessage.do?phoneImei=" + phoneImei;
			if (file != null && file.getOriginalFilename().endsWith(".wav")) {
				result = audioService.wav2voice(appkey, secret, file, wav2voice);
				JSONObject jsonObject = JSONObject.fromObject(result);
				String ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					String multimediaURL = jsonObject.getJSONObject("RESULT").getString("url");
					result = customService.appConnectSendWeibo(appkey, secret, multimediaURL,
							accountID, parameterType, callbackURL, appConnectSendWeibo);
					logger.info(appKey + "===>" + result);
				} else {
					logger.error(appKey + "===>" + result);
				}
			} else {
				result = audioService.saveSound(appkey, secret, saveSound, file);
				JSONObject jsonObject = JSONObject.fromObject(result);
				String ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					String multimediaURL = jsonObject.getJSONObject("RESULT").getString("url");
					result = customService.appConnectSendWeibo(appkey, secret, multimediaURL,
							accountID, parameterType, callbackURL, appConnectSendWeibo);
					logger.info(appKey + "===>" + result);
				} else {
					logger.error(appKey + "===>" + result);
				}
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.toString());
		}
		return result;
	}

	/**
	 * 推送微博信息
	 * 
	 * @param accountID
	 * @param nickname
	 * @param fileURL
	 * @param fileDuration
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pushWeiBoMessage")
	public String pushMessage(String accountID, String phoneImei, String nickname, String fileURL,
			String fileDuration, String actionType) {
		logger.info("callbackURL success");
		logger.info(fileURL + "-----------------------------");
		logger.info(phoneImei + "-----------------------------");
		String result = null;
		try {
			PushResult messageResult = null;
			JSONObject jsonObject = new JSONObject();
			Map<String, Object> content = new HashMap<String, Object>();
			content.put("senderAccountID", accountID);
			content.put("nickname", nickname);
			jsonObject.put("msgContent", content);
			String msgContent = jsonObject.toString();
			//手机端首先开始发起语音通话
			if (("10").equals(actionType)) {
				ConnMessage connMessage = new ConnMessage(accountID, nickname, phoneImei, fileURL,
						fileDuration, "0");
				boolean isSuccess = connMessageService.addConnMessage(connMessage);
				if (isSuccess) {
					result = connMessage.toString();
					logger.info(result);
					messageResult = mirrTalkService.pushMessage("receiveMessage_online",
							msgContent, phoneImei);
				} else {
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							ConstantsUtil.RESULT_SERVICE_ERROR);
					logger.error(result);
				}
			//终端首先开始发起语音通话
			} else if (("4").equals(actionType)) {
				HomeMessage homeMessage = homeConnService.getHomeMessage(accountID);
				phoneImei = homeMessage.getPhoneImei();
				ConnMessage connMessage = new ConnMessage(accountID, nickname, phoneImei, fileURL,
						fileDuration, "0");
				boolean isSuccess = connMessageService.addConnMessage(connMessage);
				if (isSuccess) {
					messageResult = mirrTalkService.pushMessage("receiveMessage_online",
							msgContent, phoneImei);
				} else {
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
							ConstantsUtil.RESULT_SERVICE_ERROR);
					logger.error(result);
				}
			//用户同意
			} else if (("6").equals(actionType)) {
				HomeMessage homeMessages = homeConnService.getHomeMessage(accountID, phoneImei);
				if (homeMessages == null) {
					HomeMessage homeMessage = homeConnService.getHomeMessage(accountID);
					if (homeMessage != null) {
						homeConnService.updateHomeMessage(accountID, phoneImei);
					} else {
						homeMessage = new HomeMessage();
						homeMessage.setAccountID(accountID);
						homeMessage.setPhoneImei(phoneImei);
						homeConnService.addHomeMessage(homeMessage);
					}
					messageResult = mirrTalkService.pushMessage("startOnline_success", msgContent,
							phoneImei);
					result = messageResult.toString();
					logger.info("用户按了yes!" + result);
				}
			//用户拒绝
			} else if (("7").equals(actionType)) {
				HomeMessage homeMessages = homeConnService.getHomeMessage(accountID, phoneImei);
				if (homeMessages == null) {
					messageResult = mirrTalkService.pushMessage("startOnline_refuse", msgContent,
							phoneImei);
					result = messageResult.toString();
					logger.info("用户按了no!" + result);
				}
			//用户关机
			} else {
				messageResult = mirrTalkService.pushMessage("startOnline_close", msgContent,
						phoneImei);
				result = messageResult.toString();
				logger.info("用户按了关机!" + result);
			}
		} catch (Exception e) {
            e.printStackTrace();
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}
		return result;
	}

	/**
	 * 获取连线信息
	 * 
	 * @param phoneImei
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getConnMessages")
	public String getConnMessages(String phoneImei, String accountID, HttpServletResponse response) {
		String result = null;
		try {
			List<ConnMessage> connMessages = connMessageService.getConnMessages(phoneImei,
					accountID);
			if (connMessages != null && connMessages.size() > 0) {
				connMessageService.updateConnMessage(phoneImei, accountID);
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, connMessages);
				response.getOutputStream().write(result.getBytes("utf-8"));
				result = null;
			} else {
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_EMPTY_ERROR,
						ConstantsUtil.RESULT_EMPTY_ERROR);
			}
			logger.info(result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}

		return result;
	}

    /**
     * 查询客户反馈信息
     *
     * @return
     */
    @RequestMapping(value = "/querySuggest",method = RequestMethod.POST)
    public void querySuggest(HttpServletRequest request,
                             HttpServletResponse response,
                             String username,
                             String tip,
                             @RequestParam(required = false, defaultValue = "1")Integer currentPage,
                             @RequestParam(required = false, defaultValue = "10")Integer numPerPage) throws  Exception{

        if (("mirrtalk").equals(username) && ("success").equals(tip)) {
            Map<String, Object> map = new HashMap<String, Object>();
            Pagination page = mirrTalkService.queryPageSuggest(currentPage, numPerPage);
            List<Map<String, Object>> suggestList = page.getResultList();
            map.put("totalPage", page.getTotalPages());
            map.put("currentPage", page.getCurrentPage());
            map.put("totalRows", page.getTotalRows());
            map.put("numPerPage", page.getNumPerPage());
            map.put("suggestList", suggestList);

            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json.toString());
        }
    }

    /**
     * 查询客户反馈信息
     *
     * @return
     */
    @RequestMapping(value = "/querySuggest",method = RequestMethod.GET)
    public String querySuggest1(String username, String tip) {
        if (("mirrtalk").equals(username) && ("success").equals(tip)) {
            return "suggest";
        }
        return "login";
    }

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username, String password, Model model,
			HttpServletRequest request) {
		if (("mirrtalk").equals(username) && ("mirrtalk").equals(password)) {
            model.addAttribute("username", username);
            model.addAttribute("tip", "success");
			return "redirect:querySuggest.do";
		}
		return "login";
	}

	/**
	 * 分页查询客户反馈信息
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryPageSuggest")
	public Map<String, Object> queryPageSuggest(Integer currentPage, Integer numPerPage,
			HttpServletRequest request) {
		Pagination page = null;
		List<Map<String, Object>> suggestList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (currentPage == null || numPerPage == null) {
				page = mirrTalkService.queryPageSuggest(1, 10);
			} else {
				page = mirrTalkService.queryPageSuggest(currentPage, numPerPage);
			}
			suggestList = page.getResultList();
			map.put("totalPage", page.getTotalPages());
			map.put("currentPage", page.getCurrentPage());
			map.put("totalRows", page.getTotalRows());
			map.put("numPerPage", page.getNumPerPage());
			map.put("suggestList", suggestList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return map;
	}

	/**
	 * 通过扫描二维码建立家人连线(未加密imei)
	 * 
	 * @param appKey
	 * @param phoneImei
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyConnectScan")
	public String applyConnectScan(String appKey, String phoneImei, String IMEI) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = userService.getMirrtalkInfoByImei(appkey, secret, IMEI, getMirrtalkInfoByImei);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				String accountID = jsonObject.getJSONObject("RESULT").getString("accountID");
				result = customService.setCustomInfo(appkey, secret, accountID, "4", "5", "",
						setCustomInfo);
				jsonObject = JSONObject.fromObject(result);
				ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					HomeMessage homeMessage = homeConnService.getHomeMessage(accountID);
					if (homeMessage != null) {
						homeConnService.updateHomeMessage(accountID, phoneImei);
					} else {
						homeMessage = new HomeMessage();
						homeMessage.setAccountID(accountID);
						homeMessage.setPhoneImei(phoneImei);
						homeConnService.addHomeMessage(homeMessage);
					}
				} else {
					logger.info(appKey + "===>" + result);
				}
			} else {
				logger.info(appKey + "===>" + result);
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.toString());
		}

		return result;
	}

	/**
	 * 通过扫描二维码建立家人连线(加密imei)
	 * 
	 * @param appKey
	 * @param phoneImei
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyConnScan")
	public String applyConnScan(String appKey, String phoneImei, String IMEI,
			HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			IMEI = new String(DESUtils.decrypt(IMEI, "mirrtalk"));
			result = userService.getMirrtalkInfoByImei(appkey, secret, IMEI, getMirrtalkInfoByImei);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				jsonObject = jsonObject.getJSONObject("RESULT");
				String accountID = jsonObject.getString("accountID");
				String nickname = jsonObject.getString("nickname");
				result = customService.setCustomInfo(appkey, secret, accountID, "4", "5", "",
						setCustomInfo);
				jsonObject = JSONObject.fromObject(result);
				ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					HomeMessage homeMessage = homeConnService.getHomeMessage(accountID);
					if (homeMessage != null) {
						homeConnService.updateHomeMessage(accountID, phoneImei);
					} else {
						homeMessage = new HomeMessage();
						homeMessage.setAccountID(accountID);
						homeMessage.setPhoneImei(phoneImei);
						homeConnService.addHomeMessage(homeMessage);
					}
					ApplyConn applyConn = new ApplyConn();
					applyConn.setAccountID(accountID);
					applyConn.setNickname(nickname);
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, applyConn);
					response.getOutputStream().write(result.getBytes("utf-8"));
					result = null;
				} else {
					logger.info(appKey + "===>" + result);
				}
			} else {
				logger.info(appKey + "===>" + result);
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.toString());
		}

		return result;
	}

	/**
	 * 是否连线
	 * 
	 * @param accountID
	 * @param phoneImei
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isConnect")
		public String isConnect(String accountID, String phoneImei) {
		String result = null;
		try {
			HomeMessage homeMessage = homeConnService.getHomeMessage(accountID, phoneImei);
			Boolean isConnect = homeMessage != null ? true : false;
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, isConnect);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(e.toString());
		}
		return result;
	}

	/**
	 * 获取成绩单
	 * 
	 * @param appKey
	 * @param accountID
     * @param moneyType  货币类型  2金钱  老版本 1密点 新版本
	 * @return
	 */
	@RequestMapping("/getTranscript")
	public @ResponseBody CommonJsonResult getTranscript(String appKey, String accountID,@RequestParam(required = false) Short moneyType ) {
		//String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);

        CommonJsonResult  jsonResult = new CommonJsonResult();
		try {
            String result = dataService.getMileageForAccount(appkey, secret, accountID,
					getMileageForAccount);
            CommonJsonResult resJsonResult = (CommonJsonResult)JsonMapper.fromJson(result,CommonJsonResult.class);
			if (resJsonResult.getERRORCODE().equals("0")) {
                Map<String,String> map = ( Map<String,String>)resJsonResult.getRESULT();
				String daySumMileage = map.get("daySumMileage");
				String weekSumMileage =  map.get("weekSumMileage");
				String yearSumMileage =  map.get("yearSumMileage");
				String dayTime =  map.get("dayTime");
				String monthSumMileage = map.get("monthSumMileage");
				String allSumMileage = map.get("allSumMileage");
				String monthTime =  map.get("monthTime");
				Double dayReward = null, weekReward = null, monthReward = null, yearReward = null, totalReward = null;
				String[] mileage = { daySumMileage, weekSumMileage, monthSumMileage, yearSumMileage };
				for (int i = 0; i < mileage.length; i++) {
					if (!("0").equals(mileage[i])) {
						String result1 = rewardService.getRewardAmountByMileage(appkey, secret,
								accountID, mileage[i],moneyType, getRewardAmountByMileage);
                        CommonJsonResult resJsonResult1 =   (CommonJsonResult)JsonMapper.fromJson(result1,CommonJsonResult.class);
						if (resJsonResult1.getERRORCODE().equals("0")) {
                            Map<String,Object> map1 = ( Map<String,Object>)resJsonResult1.getRESULT();
							Double reward = Double.valueOf(map1.get("rewardAmount")+"");
							switch (i) {
                                case 0:
                                    dayReward = reward;
                                    continue;
                                case 1:
                                    weekReward = reward;
                                    continue;
                                case 2:
                                    monthReward = reward;
                                    Calendar instance = Calendar.getInstance();
                                    int month = instance.get(Calendar.MONTH) + 1;
                                    if (month == Integer.parseInt((monthTime).substring(4))) {
                                        if(map1.get("totalEarnedAmount") != null){
                                            totalReward =Double.parseDouble(map1.get("totalEarnedAmount")+"")
                                                    + monthReward;
                                        }else{
                                            totalReward =monthReward;
                                        }

                                    } else {
                                        totalReward = Double.parseDouble(map1.get("totalEarnedAmount")+"");
                                    }
                                    continue;
                                case 3:
                                    yearReward = reward;
                                    continue;
                                default:
                                    break;
							}
						}
					}
				}

                Map<String,Object> param = new HashMap();
                param.put("daySumMileage",daySumMileage == null ? "" : daySumMileage);
                param.put("weekSumMileage",weekSumMileage == null ? "" : weekSumMileage);
                param.put("yearSumMileage",yearSumMileage == null ? "" : yearSumMileage);
                param.put("dayTime",dayTime  == null ? "" : dayTime);
                param.put("monthSumMileage",monthSumMileage  == null ? "" : monthSumMileage);
                param.put("allSumMileage",allSumMileage   == null ? "" : allSumMileage);
                param.put("monthTime",monthTime   == null ? "" : monthTime);
                param.put("dayTime",dayTime   == null ? "" : dayTime);
                param.put("dayReward",dayReward  == null ? 0 : dayReward);
                param.put("monthReward",monthReward == null ? 0 : monthReward);
                param.put("weekReward",weekReward == null ? 0 : weekReward);
                param.put("yearReward",yearReward  == null ? 0 : yearReward);
                param.put("totalReward",totalReward  == null ? 0 : totalReward);

                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(param);
			} else {
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("查询成绩单失败");
			}
		} catch (Exception e) {
           e.printStackTrace();
		   logger.error("查询成绩单失败",e.getMessage());
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("服务器处理失败");
		}

		return jsonResult;
	}

    /**
     * 获取年度成绩单
     * @param appKey
     * @param accountID
     * @return
     */
    @RequestMapping("/getYearTranscripts")
    public @ResponseBody CommonJsonResult getYearTranscripts(@RequestParam String appKey, @RequestParam String accountID){
        CommonJsonResult jsonResult = new CommonJsonResult();
        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        //获取昵称
        String nickName ="";
        try {
            String userInfo = userService.getUserInfo(appkey, secret, accountID, getUserInfo);
            CommonJsonResult jsonResult3 = (CommonJsonResult)JsonMapper.fromJson(userInfo,CommonJsonResult.class);
            if(jsonResult3.getERRORCODE().equals("0")){
                Map<String,String> map3 =( Map<String,String>) jsonResult3.getRESULT();
                nickName = map3.get("nickname");
                Map<String,Object> param = new HashMap();
                String md5 =MD5Signature.encryptImage(accountID,"MD5");
                String imageUrl = transcriptsImageUrl +md5+".png";
                String url = transcriptsUrl +md5 +".html" ;
                param.put("transcriptsImage",imageUrl);
                StringBuilder textbuf = new StringBuilder();
                textbuf.append("我正在使用一款神奇车上神器-WEME，硬件、服务、流量统统都是免费的，还有奖金拿，就是这么任性。我在道客社区叫").append(nickName)
                        .append("，这是我的2014年度成绩单：").append(url);
                param.put("transcriptsText",textbuf.toString());
                param.put("transcriptsUrl",imageUrl);
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
                jsonResult.setRESULT(param);
            }else{
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
                jsonResult.setRESULT("查询年度成绩单失败");
            }

        }catch (Exception e){

            e.printStackTrace();
            logger.error("查询年度成绩失败",e.getMessage());
        }
        return jsonResult;
    }

	/**
	 * 添加反馈回复
	 * 
	 * @param suggestID
	 * @param replyName
	 * @param replyContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addSuggestComment")
	public String addSuggestComment(String suggestID, String replyName, String replyContent) {
		String result = null;
		boolean isSuccess = mirrTalkService.addSuggestComment(suggestID, replyName, replyContent);
		if (isSuccess) {
			result = JsonPuserUtil
					.jsonToString(ConstantsUtil.ERRORCODE_OK, ConstantsUtil.RESULT_OK);
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		return result;
	}

	/**
	 * 查询客户反馈信息
	 * 
	 * @return
	 */
	@RequestMapping("/toAddSuggestComment")
	public String toAddSuggestComment(String suggestID, String accountID, HttpServletRequest request) {
		try {
			List<SuggestComment> suggestComments = mirrTalkService.querySuggestComment(accountID,
					suggestID);
			request.setAttribute("suggestID", suggestID);
			request.setAttribute("suggestComments", suggestComments);
			return "suggestComment";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取用户反馈回复信息
	 * 
	 * @param accountID
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/querySuggestComment")
	public String querySuggestComment(String accountID, HttpServletResponse response) {
		String result = null;
		try {
			if (StringUtils.isEmpty(accountID)) {
				result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_FIELD_EMPTY,
						"The accountID is empty!");
			} else {
				List<SuggestComment> suggestComment = mirrTalkService.querySuggestComment(
						accountID, null);
				if (suggestComment != null) {
					JSONArray jsonArray = new JSONArray();
					JSONArray reply = new JSONArray();
					for (int i = 0; i < suggestComment.size();) {
						JSONObject object = new JSONObject();
						object.put("accountID", suggestComment.get(i).getAccountID());
						object.put("nickName", suggestComment.get(i).getNickName());
						object.put("suggestContent", suggestComment.get(i).getSuggestContent());
						object.put("suggestID", suggestComment.get(i).getSuggestID());
						object.put("suggestTime", suggestComment.get(i).getSuggestTime());
						JSONObject firstObject = new JSONObject();
						firstObject.put("id", suggestComment.get(i).getId());
						firstObject.put("commentTime", suggestComment.get(i).getCommentTime());
						String replyContent = suggestComment.get(i).getReplyContent();
						replyContent = StringUtils.isNotEmpty(replyContent) ? replyContent : "您的反馈我们已经收到!";
						String replyName = suggestComment.get(i).getReplyName();
						replyName = StringUtils.isNotEmpty(replyName) ? replyName : "客服";
						firstObject.put("replyContent", replyContent);
						firstObject.put("replyName", replyName);
						reply.add(firstObject);
						for (int j = i + 1; j < suggestComment.size(); j++) {
							JSONObject secondObject = new JSONObject();
							if ((suggestComment.get(i).getSuggestID()).equals(suggestComment.get(j)
									.getSuggestID()) && suggestComment.get(i).getSuggestContent()
									.equals(suggestComment.get(j).getSuggestContent())) {
								secondObject.put("id", suggestComment.get(j).getId());
								secondObject.put("commentTime", suggestComment.get(j)
										.getCommentTime());
								secondObject.put("replyContent", suggestComment.get(j)
										.getReplyContent());
								secondObject.put("replyName", suggestComment.get(j).getReplyName());
								reply.add(secondObject);
								if (j == suggestComment.size() - 1) {
									i = suggestComment.size();
								}
							} else {
								i = j;
								break;
							}
						}
						if (i == suggestComment.size() - 1) {
							i = suggestComment.size();
						}
						object.put("reply", reply);
						jsonArray.add(object);
						reply = new JSONArray();
					}
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, jsonArray);
					response.getOutputStream().write(result.getBytes("utf-8"));
					result = null;
				} else {
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_EMPTY_ERROR,
							ConstantsUtil.RESULT_EMPTY_ERROR);
				}
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}

		return result;
	}

}
