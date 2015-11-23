package com.daoke.mobileserver.map.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.dao.DataSourceContextHolder;
import com.daoke.mobileserver.common.dao.DataSourceType;
import com.daoke.mobileserver.map.dao.IMapDao;
import com.daoke.mobileserver.map.entity.PowerOffHotVo;
import com.daoke.mobileserver.user.entity.UserDriveInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 5/19 0019.
 */
@Repository
public class MapDaoImpl extends BaseDao implements IMapDao {
    @Override
    public List<Map<String, String>> queryUserArriveCity(String accountID) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.DATA_Center);
        List<Map<String, String>> lis = this.selectList("map.queryUserArriveCity",accountID);
        DataSourceContextHolder.clearDataSourceType();
        return  lis;

    }

    @Override
    public List<PowerOffHotVo> queryPowerOffHot(Map<String,Object> map) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.DATA_Center);
        List<PowerOffHotVo> list = this.selectList("map.queryPowerOffHot",map);
        DataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public List<String> queryCityCode(Map<String, Object> map) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.DATA_Center);
        List<String> list = this.selectList("map.queryCityCode",map);
        DataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public UserDriveInfoVo queryUserDriveInfoVo(Map<String, Object> map) {
        return  this.selectOne("map.queryUserDriveInfoVo",map);
    }
    @Override
    public  List<Map<String,String>> queryMapCityCode() {
        return  this.selectList("map.queryMapCityCode");
    }
}
