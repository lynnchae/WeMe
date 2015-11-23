package com.daoke.mobileserver.map.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.daoke.mobileserver.map.dao.IMapDao;
import com.daoke.mobileserver.map.entity.PowerOffHotVo;
import com.daoke.mobileserver.map.service.MapService;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserDriveInfoVo;
import com.daoke.mobileserver.util.AbDateUtil;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 *
 * @author wangliming
 * @date 2014-10-24 下午4:39:49
 * @version 1.0
 */
@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private IMapDao mapDao;
    public static Map<String,String> CITY_CODE = new HashMap<String, String>();
	@Override
	public String getNearbyDaoke(String appKey, String secret, String longitude, String latitude,
			String distance, String resultCount, String getNearbyDaoke) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("longitude");
		keys.add("latitude");
		
		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(longitude);
		values.add(latitude);
		
		String[] keyContent = {"distance", "resultCount"};
		Object[] valueContent = {distance, resultCount};
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(), getNearbyDaoke);
		return result;
	}

	@Override
	public String getLocation(String appKey, String secret, String accountID, String getLocation)
			throws Exception {
		String[] keys = { "appKey", "secret", "accountID" };
		Object[] values = { appKey, secret, accountID };
		String result = Sha1.httpPost(keys, values, getLocation);
		return result;
	}

	@Override
	public Map<String, Object> getHotPoint(String accountID) throws ParseException {

		Map<String,Object> resultMap = new HashMap<String, Object>(3);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String firstDayOfMonth = AbDateUtil.getFirstDayOfMonth("yyyy-MM-dd hh:mm:ss");
		String lastDayOfMonth = AbDateUtil.getLastDayOfMonth("yyyy-MM-dd hh:mm:ss");
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put("accountID",accountID);
		map.put("firstDayOfMonth", df.parse(firstDayOfMonth).getTime()/1000);
		map.put("lastDayOfMonth",df.parse(lastDayOfMonth).getTime()/1000);
		List<PowerOffHotVo> powerOffHotVolist = mapDao.queryPowerOffHot(map);
		List<String> cityCodeList = mapDao.queryCityCode(map);
		UserDriveInfoVo userDriveInfoVo = mapDao.queryUserDriveInfoVo(map);
		resultMap.put("powerOffHotList",powerOffHotVolist);
		resultMap.put("cityCodeList",cityCodeList);
		resultMap.put("userDriveInfoVo",userDriveInfoVo);
		return resultMap;
	}
    /**
     *查询所有城市code
     * @param
     * @return
     */
    public  List<Map<String,String>> queryMapCityCode(){
       return mapDao.queryMapCityCode();
    }
    @PostConstruct
    public  void getCityCode() {
        List<Map<String,String>> lis = this.queryMapCityCode();
        for (Map<String,String> entity : lis) {
            CITY_CODE.put(entity.get("code"),entity.get("name"));
        }
    }
    public  List<Map<String,String>> queryUserArriveCity(String accountID){
        List<Map<String,String>>  map = mapDao.queryUserArriveCity(accountID);
        return map;
    }

}
