package com.daoke.mobileserver.map.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.map.service.MapService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author wangliming
 * @date 2014-10-24 下午4:53:53
 * @version 1.0
 */
@Controller
public class MapController {

	private final Logger logger = Logger.getLogger(MapController.class) ;

	@Value("#{apiConfig[getNearbyDaoke]}")
	private String getNearbyDaoke;

	@Value("#{apiConfig[getLocation]}")
	private String getLocation;

	@Value("#{apiConfig[saveFile]}")
	private String saveFile;

	@Autowired
	private MapService mapService;
	@Autowired
	private AudioService audioService;

	/**
	 * 获取附近道客
	 * 
	 * @param appKey
	 * @param longitude
	 * @param latitude
	 * @param distance
	 * @param resultCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNearbyDaoke")
	public String getNearbyDaoke(String appKey, String longitude, String latitude, String distance,
			String resultCount) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = mapService.getNearbyDaoke(appkey, secret, longitude, latitude, distance,
					resultCount, getNearbyDaoke);
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
	 * 获取用户当前位置信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLocation")
	public String getLocation(String appKey, String accountID, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = mapService.getLocation(appkey, secret, accountID, getLocation);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(appKey + "===>" + result);
			result = null;
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + result);
		}

		return result;
	}
    /**
     * 获取用户当前热点信息
     *
     * @param accountID
     * @return
     */
    @ResponseBody
    @RequestMapping("/getHotPoint")
    public CommonJsonResult getHotPoint( String accountID) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        Map<String,Object> map = null;
        try {
            map = mapService.getHotPoint(accountID);
        } catch (Exception e) {
            logger.error("获取用户当前热点信息失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取用户当前热点信息失败");
            e.printStackTrace();
        }
        jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        jsonResult.setRESULT(map);
        return jsonResult;
    }


	/**
	 * 查询用户到过的城市
	 *
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getArriveCity")
	public CommonJsonResult getArriveCity( String accountID) {
		CommonJsonResult jsonResult = new CommonJsonResult();
		try {
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
			jsonResult.setRESULT( mapService.queryUserArriveCity(accountID));
		}catch (Exception e){
			logger.error("获取用户到过的城市失败",e);
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
			jsonResult.setRESULT("获取用户到过的城市失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	/**
	 * 上传足迹图片
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadFootmarkShare")
	public CommonJsonResult uploadFootmarkShare(@RequestParam String appKey, HttpServletRequest request) {
		CommonJsonResult jsonResult = new CommonJsonResult();
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try { MultipartFile file = null;
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
				logger.error("图片保存失败",e1);
				jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
				jsonResult.setRESULT("图片保存失败");
			}
			if(file!=null) {
				String result = audioService.saveFile(appkey, secret, file, saveFile);
				JSONObject jsonObject = JSONObject.fromObject(result);
				String ERRORCODE = jsonObject.getString("ERRORCODE");
				if (("0").equals(ERRORCODE)) {
					String imgUrl = jsonObject.getJSONObject("RESULT").getString("url");
					jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
					jsonResult.setRESULT("http://192.168.11.85:3006/shareTrack?imgURL="+imgUrl);
				}else{
					logger.error("上传足迹失败");
					jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
					jsonResult.setRESULT("上传足迹失败");
				}
			}
		}catch (Exception e){
			logger.error("上传足迹失败",e);
			jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
			jsonResult.setRESULT("上传足迹失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
}