package com.daoke.mobileserver.user.service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2015/5/12.
 */
public interface IUserRankInfoService {
    public List<Map<String,Object>> getRankMember (String accountID,int RandMemberType,int RandType,String appKey,String getSecretchannelRelationByUserkey) throws Exception;
    public List<Map<String,Object>> getMileageSumRankList (String accountIDs,int RandMemberType,String appKey,String getSecretchannelRelationByUserkey) throws Exception;
    public Map<String,Object> getRankMyselfInfo(String accountID,int RankType);
    public List<Map<String,Object>> getRankListInfoByShellAll(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception;
    public List<Map<String,Object>> getRankListInfoByShell(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception;
    public Map<String,Object> getRankMyselfInfoByShellAll (String accountID);
    public Map<String,Object> getRankMyselfInfoByShell (String accountID);
}
