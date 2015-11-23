package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.user.entity.UserRankInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2015/5/11.
 */
public interface IUserRankInfoDao {
    public List<String> searchById(Integer id);
    public List<Map<String,Object>> getRankFriend(String accountID)   ;
    public List<Map<String,Object>> getRankListInfo(List accountIDs,int RandType);
    public List<Map<String,Object>> getRankListInfoByShellAll(List accountIDs);
    public List<Map<String,Object>> getRankListInfoByShell(List accountIDs);
    public List<Map<String,Object>> getMileageSumRankList (List accountIDs);
    public Map<String,Object> getMyselfRankInfoThisMonth (String accountID,int RankType);
    public Map<String,Object> getRankMyselfInfoByShellAll(String accountID);
    public Map<String,Object> getRankMyselfInfoByShell(String accountID);

    public List<Map<String,Object>> getRankChannelMember(String accountID);
}
