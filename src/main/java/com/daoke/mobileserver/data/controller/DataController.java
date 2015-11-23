package com.daoke.mobileserver.data.controller;


import javax.servlet.http.HttpServletResponse;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.data.service.DataService;
import com.daoke.mobileserver.report.dto.*;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author wangliming
 * @date 2014-10-28 下午7:42:37
 * @version 1.0
 */
@Controller
public class DataController {

	private final Logger logger = Logger.getLogger(DataController.class);

	@Value("#{apiConfig[getGrade]}")
	private String getGrade;

	@Value("#{apiConfig[getRouteList]}")
	private String getRouteList;

	@Value("#{apiConfig[getGradeByTravelID]}")
	private String getGradeByTravelID;

	@Value("#{apiConfig[getTravelByAccount]}")
	private String getTravelByAccount;

	@Value("#{apiConfig[getMileageForAccount]}")
	private String getMileageForAccount;

	@Value("#{apiConfig[getRouteWayToRoadList]}")
	private String getRouteWayToRoadList;

	@Value("#{apiConfig[getSumMileageList]}")
	private String getSumMileageList;

	@Value("#{apiConfig[getRankInfo]}")
	private String getRankInfo;

	@Value("#{apiConfig[getUserInfo]}")
	private String getUserInfo;

	@Value("#{apiConfig[queryTweetCountRank]}")
	private String queryTweetCountRank;

	@Value("#{apiConfig[getLastFansTotal]}")
	private String getLastFansTotal;

	@Autowired
	private DataService dataService;

	@Autowired
	private UserService userService;

	/**
	 * 获取驾驶评分
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGrade")
	public String getGrade(String appKey, String accountID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getGrade(appkey, secret, accountID, getGrade);
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
	 * 查询某用户的行程
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getRouteList")
	public String getRouteList(String appKey, String accountID, String pageNumber, String pageSize,
			String sort, String startTime, String endTime, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getRouteList(appkey, secret, accountID, pageNumber, pageSize,
					sort, startTime, endTime, getRouteList);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				JSONArray jsonArray = jsonObject.getJSONArray("RESULT");
				List<TravelInfo> travelInfos = (List<TravelInfo>) JSONArray.toCollection(jsonArray,
						TravelInfo.class);
				List<TravelGrade> travelGrades = new ArrayList<TravelGrade>();
				for (TravelInfo travelInfo : travelInfos) {
					int actualMileage = travelInfo.getActualMileage();
					String endCityCode = travelInfo.getEndCityCode();
					String startCityName = travelInfo.getStartCityName();
					String endCityName = travelInfo.getEndCityName();
					String startCityCode = travelInfo.getStartCityCode();
					int endLatitude = travelInfo.getEndLatitude();
					int endLongitude = travelInfo.getEndLongitude();
					int formatEndTime = travelInfo.getEndTime();
					int startLatitude = travelInfo.getStartLatitude();
					int startLongitude = travelInfo.getStartLongitude();
					int formatStartTime = travelInfo.getStartTime();
					int sumMileage = travelInfo.getSumMileage();
					int totalTime = travelInfo.getTotalTime();
					String endRoadName = travelInfo.getEndRoadName();
					String startRoadName = travelInfo.getStartRoadName();
					int averageSpeed = travelInfo.getAverageSpeed();
					int maximumSpeed = travelInfo.getMaximumSpeed();
					String travelID = travelInfo.getTravelID();
					result = dataService.getGradeByTravelID(appkey, secret, travelID,
							getGradeByTravelID);
					jsonObject = JSONObject.fromObject(result);
					ERRORCODE = jsonObject.getString("ERRORCODE");
					if (("0").equals(ERRORCODE)) {
						jsonObject = jsonObject.getJSONObject("RESULT");
						if (!jsonObject.isNullObject()) {
							GradeInfo gradeInfo = (GradeInfo) JSONObject.toBean(jsonObject,
									GradeInfo.class);
							int cityGrade = gradeInfo.getCityGrade();
							int gsensorGrade = gradeInfo.getGsensorGrade();
							int habitGrade = gradeInfo.getHabitGrade();
							int isSpeedGrade = gradeInfo.getIsSpeedGrade();
							int roadInfoGrade = gradeInfo.getRoadInfoGrade();
							int speedGrade = gradeInfo.getSpeedGrade();
							int timeGrade = gradeInfo.getTimeGrade();
							int travelGrade = gradeInfo.getTravelGrade();

							TravelGrade grade = new TravelGrade(actualMileage, endCityCode,
									startCityName, endCityName, startCityCode, endLatitude,
									endLongitude, formatEndTime, startLatitude, startLongitude,
									formatStartTime, sumMileage, totalTime, travelID, endRoadName,
									startRoadName, averageSpeed, maximumSpeed, accountID,
									cityGrade, gsensorGrade, habitGrade, isSpeedGrade,
									roadInfoGrade, speedGrade, timeGrade, travelGrade);
							travelGrades.add(grade);
						} else {
							TravelGrade grade = new TravelGrade(actualMileage, endCityCode,
									startCityName, endCityName, startCityCode, endLatitude,
									endLongitude, formatEndTime, startLatitude, startLongitude,
									formatStartTime, sumMileage, totalTime, travelID, endRoadName,
									startRoadName, averageSpeed, maximumSpeed, accountID, 0, 0, 0,
									0, 0, 0, 0, 0);
							travelGrades.add(grade);
						}
					} else if (("ME01002").equals(ERRORCODE)) {
						TravelGrade grade = new TravelGrade(actualMileage, endCityCode,
								startCityName, endCityName, startCityCode, endLatitude,
								endLongitude, formatEndTime, startLatitude, startLongitude,
								formatStartTime, sumMileage, totalTime, travelID, endRoadName,
								startRoadName, averageSpeed, maximumSpeed, accountID, 0, 0, 0, 0,
								0, 0, 0, 0);
						travelGrades.add(grade);
					} else {
						logger.info(appKey
								+ "===>"
								+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
										ConstantsUtil.RESULT_OK));
					}
				}

				if (travelGrades != null && travelGrades.size() > 0) {
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							JSONArray.fromObject(travelGrades));
					logger.info(appKey
							+ "===>"
							+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
									ConstantsUtil.RESULT_OK));
				} else {
					logger.warn(appKey + "===>" + result);
				}
			} else {
				logger.warn(appKey + "===>" + result);
			}
			response.getOutputStream().write(result.getBytes("utf-8"));
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
	 * 用户途经道路
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTravelByAccount")
	public String getTravelByAccount(String appKey, String accountID, String startTime,
			String endTime) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getTravelByAccount(appkey, secret, accountID, startTime, endTime,
					getTravelByAccount);
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
	 * 获得里程
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMileageForAccount")
	public String getMileageForAccount(String appKey, String accountID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getMileageForAccount(appkey, secret, accountID,
					getMileageForAccount);
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
	 * 查询某用户的某行程途径道路集合
	 * 
	 * @param appKey
	 * @param secret
	 * @param travelID
	 * @param sort
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRouteWayToRoadList")
	public String getRouteWayToRoadList(String appKey, String travelID, String sort,
			String startTime, String endTime, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getRouteWayToRoadList(appkey, secret, travelID, sort, startTime,
					endTime, getRouteWayToRoadList);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							ConstantsUtil.RESULT_OK));
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
	 * 获取总的里程列表
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param tokenCode
	 * @param dtype
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSumMileageList")
	public String getSumMileageList(String appKey, String accountID, String tokenCode,
			String dtype, String startTime, String endTime) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getSumMileageList(appkey, secret, accountID, tokenCode, dtype,
					startTime, endTime, getSumMileageList);
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
	 * 查询某用户的驾驶里程和时长排名
	 * 
	 * @param appKey
	 * @param secret
	 * @param pageNumber
	 * @param pageSize
	 * @param startTime
	 * @param sort
	 * @param accountID
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@ResponseBody
	@RequestMapping("/getRankInfo")
	public String getRankInfo(String appKey, String pageNumber, String pageSize, String startTime,
			String sort, String accountID, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		List<RankInfo> rankInfos = new ArrayList<RankInfo>();
		try {
			result = dataService.getRankInfo(appkey, secret, pageNumber, pageSize, startTime, sort,
					accountID, getRankInfo);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				JSONArray jsonArray = jsonObject.getJSONArray("RESULT");
				rankInfos = (List<RankInfo>) jsonArray.toCollection(jsonArray, RankInfo.class);
				for (RankInfo rankInfo : rankInfos) {
					accountID = rankInfo.getAccountID();
					String userInfo = userService.getUserInfo(appkey, secret, accountID,
							getUserInfo);
					String nickname = JSONObject.fromObject(userInfo).getJSONObject("RESULT")
							.getString("nickname");
					rankInfo.setNickname(nickname);
				}
			}
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, rankInfos);
			response.getOutputStream().write(result.getBytes("utf-8"));
			logger.info(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							ConstantsUtil.RESULT_OK));
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
	 * 查询吐槽排名
	 * 
	 * @param appKey
	 * @param secret
	 * @param dateTime
	 * @param queryType
	 * @param accountID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/queryTweetCountRank")
	public String queryTweetCountRank(String appKey, String dateTime, String queryType,
			String accountID, String flag, HttpServletResponse response) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.queryTweetCountRank(appkey, secret, dateTime, queryType,
					accountID, queryTweetCountRank);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				if (StringUtils.isNotEmpty(flag) && StringUtils.isNotEmpty(accountID)) {
					jsonObject = jsonObject.getJSONObject("RESULT");
					TweetCountRank tweetCountRank = (TweetCountRank) JSONObject.toBean(jsonObject,
							TweetCountRank.class);
					result = userService.getUserInfo(appkey, secret, accountID, getUserInfo);
					String nickname = JSONObject.fromObject(result).getJSONObject("RESULT")
							.getString("nickname");
					tweetCountRank.setAccountID(accountID);
					tweetCountRank.setNickname(nickname);

					result = dataService.queryTweetCountRank(appkey, secret, dateTime, "2", null,
							queryTweetCountRank);
					jsonObject = JSONObject.fromObject(result);
					JSONArray jsonArray = jsonObject.getJSONArray("RESULT");
					List<TweetCountRank> tweetCountRanks = (List<TweetCountRank>) JSONArray
							.toCollection(jsonArray, TweetCountRank.class);
					for (TweetCountRank tweetCount : tweetCountRanks) {
						result = userService.getUserInfo(appkey, secret, tweetCount.getAccountID(),
								getUserInfo);
						nickname = JSONObject.fromObject(result).getJSONObject("RESULT")
								.getString("nickname");
						tweetCount.setNickname(nickname);
					}
					tweetCountRanks.add(tweetCountRank);
					result = JsonPuserUtil
							.jsonToString(ConstantsUtil.ERRORCODE_OK, tweetCountRanks);
				} else if (StringUtils.isNotEmpty(accountID)) {
					jsonObject = jsonObject.getJSONObject("RESULT");
					TweetCountRank tweetCountRank = (TweetCountRank) JSONObject.toBean(jsonObject,
							TweetCountRank.class);
					result = userService.getUserInfo(appkey, secret, accountID, getUserInfo);
					String nickname = JSONObject.fromObject(result).getJSONObject("RESULT")
							.getString("nickname");
					tweetCountRank.setAccountID(accountID);
					tweetCountRank.setNickname(nickname);
					result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK, tweetCountRank);
				} else {
					JSONArray jsonArray = jsonObject.getJSONArray("RESULT");
					List<TweetCountRank> tweetCountRanks = (List<TweetCountRank>) JSONArray
							.toCollection(jsonArray, TweetCountRank.class);
					for (TweetCountRank tweetCountRank : tweetCountRanks) {
						result = userService.getUserInfo(appkey, secret,
								tweetCountRank.getAccountID(), getUserInfo);
						String nickname = JSONObject.fromObject(result).getJSONObject("RESULT")
								.getString("nickname");
						tweetCountRank.setNickname(nickname);
					}
					result = JsonPuserUtil
							.jsonToString(ConstantsUtil.ERRORCODE_OK, tweetCountRanks);
				}
				response.getOutputStream().write(result.getBytes("utf-8"));
				result = null;
			}
			logger.info(appKey
					+ "===>"
					+ JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_OK,
							ConstantsUtil.RESULT_OK));
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.getMessage());
		}

		return result;
	}

	/**
	 * 获取用户weme信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInfo")
	public String getInfo(String appKey, String accountID) {
		String result = null;
		String appkey = ConstantsUtil.getAppKey(appKey);
		String secret = ConstantsUtil.getSecret(appKey);
		try {
			result = dataService.getLastFansTotal(appkey, secret, accountID, getLastFansTotal);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String ERRORCODE = jsonObject.getString("ERRORCODE");
			if (("0").equals(ERRORCODE)) {
				result = dataService.getMileageForAccount(appkey, secret, accountID,
						getMileageForAccount);
				JSONObject mileageJson = JSONObject.fromObject(result);
				ERRORCODE = mileageJson.getString("ERRORCODE");
				String daySumMileage = "";
				String dayActualMileage = "";
				String allSumMileage = "";
				String dayTime = "";
				if (("0").equals(ERRORCODE)) {
					JSONObject mileage = mileageJson.getJSONObject("RESULT");
					daySumMileage = mileage.getString("daySumMileage");
					dayActualMileage = mileage.getString("dayActualMileage");
					allSumMileage = mileage.getString("allSumMileage");
					dayTime = mileage.getString("dayTime");
				}
				JSONObject object = new JSONObject();
				object.put("daySumMileage", daySumMileage);
				object.put("dayActualMileage", dayActualMileage);
				object.put("allSumMileage", allSumMileage);
				object.put("dayTime", dayTime);
				jsonObject.getJSONObject("RESULT").put("mileage", object);
			}
			result = jsonObject.toString();
			logger.info(appKey + "===>" + result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(appKey + "===>" + e.getMessage());
		}
		return result;
	}


}
