package com.daoke.mobileserver.map.dao;

import com.daoke.mobileserver.map.entity.PowerOffHotVo;
import com.daoke.mobileserver.user.entity.UserDriveInfoVo;
import com.daoke.mobileserver.user.entity.UserGrade;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 5/19 0019.
 */
public interface IMapDao {
    /**
     * 查询热度
     * @param map
     * @return
     */
    public List<PowerOffHotVo> queryPowerOffHot(Map<String,Object> map);

    /**
     * 查询城市编码
     * @param map
     * @return
     */
    public  List<String> queryCityCode(Map<String,Object> map);

    /**
     *查询用户驾驶信息
     * @param map
     * @return
     */
    public UserDriveInfoVo queryUserDriveInfoVo(Map<String, Object> map);

    /**
     *查询所有城市code
     * @param
     * @return
     */
    public  List<Map<String,String>> queryMapCityCode();
    public  List<Map<String,String>> queryUserArriveCity(String accountID);
}
