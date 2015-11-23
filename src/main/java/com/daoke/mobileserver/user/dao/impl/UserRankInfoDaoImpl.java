package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.dao.DataSourceContextHolder;
import com.daoke.mobileserver.common.dao.DataSourceType;
import com.daoke.mobileserver.user.dao.IUserRankInfoDao;
import com.daoke.mobileserver.user.entity.UserRankInfo;
import com.daoke.mobileserver.util.RankingStarsTool;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2015/5/11.
 */
@Repository
public class UserRankInfoDaoImpl extends BaseDao implements IUserRankInfoDao{

    @Override
    public java.util.List<String> searchById(Integer id) {
        return this.selectOne("");
    }



    @Override
    public List<Map<String,Object>> getRankFriend(String accountID) {
     //   DataSourceContextHolder.setDataSourceType(DataSourceType.MOBILE_WEME);
        return (List)this.selectList("getRankFriend",accountID);
    }
    @Override
    public List<Map<String,Object>> getRankListInfo(List accountIDs,int RandType) {
        if  (accountIDs==null||accountIDs.size()==0){
            return null;
        }
        //DataSourceContextHolder.setDataSourceType(DataSourceType.MOBILE_WEME);
       String sRankType =  RankingStarsTool.getColumNameWithRankType(RandType);
            Map paramMap = new HashMap( );
            paramMap.put("rankType",sRankType);
            paramMap.put("list1",accountIDs);
            return (List)this.selectList("getRankListInfo",paramMap);
    }



    @Override
    public List<Map<String,Object>> getRankListInfoByShell(List accountIDs) {
        if (accountIDs==null || accountIDs.size()==0)
            return null;
       return (List) this.selectList("getRankListInfoByShell",accountIDs);
    }
    @Override
    public List<Map<String,Object>> getRankListInfoByShellAll(List accountIDs) {
        if (accountIDs==null||accountIDs.size()==0){
             return null;
        }
        return (List) this.selectList("getRankListInfoByShellAll",accountIDs);

    }

    @Override
    public List<Map<String,Object>> getMileageSumRankList (List accountIDs) {
        return (List) this.selectList("getMileageSumRankList",accountIDs);
    }
    @Override
    public Map<String,Object> getMyselfRankInfoThisMonth (String accountID,int RankType){
        String firstDayofMonth = new SimpleDateFormat("yyyy-MM-01").format(new Date());
        Map map = new HashMap();
        map.put("accountID",accountID);
        map.put("rankType", RankingStarsTool.getColumNameWithRankType(RankType));
        map.put("firstDay",firstDayofMonth);
        Map<String,Object> resultMap = (Map) this.selectOne("getMyselfRankInfoThisMonth", map);
        if(resultMap == null ){
            resultMap = (Map) this.selectOne("getSelfInfo",map);
            if(resultMap == null){
                resultMap = new HashMap<String, Object>();
                resultMap.put("title", "微密新手" );
                resultMap.put("grade", "0" );
                resultMap.put("nickName", "" );
                resultMap.put("isAllowedOpinion", 0 );
                resultMap.put("isVerifyOpinion", 0 );
                resultMap.put("userArea", "" );
                resultMap.put("userHeadName", "" );
            }
            resultMap.put("itemValue", 0 );
            resultMap.put("precent", "0%" );
            String rankTypeName = RankingStarsTool.getColumNameWithRankType(RankType);
            if("mileageCostTime".equals(rankTypeName)){
                resultMap.put("mileageSum", 0 );
            }else if("environmentalIndex".equals(rankTypeName)){
                resultMap.put("totalTravelCount", 0 );
            }else if("taskIndex".equals(rankTypeName)){
                resultMap.put("restRule", 200 );
            }
        }
        return  resultMap;
    }

    @Override
    public Map<String, Object> getRankMyselfInfoByShellAll(String accountID) {
         return (Map) this.selectOne("getRankMyselfInfoByShellAll", accountID);
    }

    @Override
    public Map<String, Object> getRankMyselfInfoByShell(String accountID) {
        Map map = new HashMap();
        map.put("accountID",accountID);
         return (Map) this.selectOne("getRankMyselfInfoByShell", map);
    }

    @Override
    public List<Map<String,Object>> getRankChannelMember(String accountID) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.WEME_Custom);
        List<Map<String,Object>>  lis = this.selectList("getRankChannelMember",accountID);
        DataSourceContextHolder.clearDataSourceType();
        return lis;
    }


}
