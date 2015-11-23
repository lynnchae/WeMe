package com.daoke.mobileserver.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.user.dao.IUserRankInfoDao;
import com.daoke.mobileserver.user.service.IUserRankInfoService;
import com.daoke.mobileserver.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangzp on 2015/5/12.
 */
@Service
public class UserRankInfoServiceImpl implements IUserRankInfoService {

    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    IUserRankInfoDao userRankInfoDao;
    @Override
    public  List<Map<String,Object>> getRankMember(String accountID,int RandMemberType,int RandType,String appKey,String getSecretchannelRelationByUserkey) throws Exception{

        List<Map<String,Object>> resultList =  this.getAccountIDWithRandMemberTypeV2(accountID, RandMemberType, appKey,getSecretchannelRelationByUserkey);

        List<Map<String,Object>> lis = null;
        if(resultList ==null)
            return null;
        lis =  userRankInfoDao.getRankListInfo(resultList,RandType);
        if(lis ==null)
            return null;
        for (Map<String,Object> li:lis){
            li.put("star",RankingStarsTool.getRuleTextWithRankType(RandType,(int)Double.parseDouble(li.get("itemValue").toString())));
        }
            return  toAddLogicUnit(RankingStarsTool.toPairKey(lis, resultList, "accountID"), RandType);
    }

    @Override
    public List<Map<String,Object>> getMileageSumRankList (String accountID,int RandMemberType,String appKey,String getSecretchannelRelationByUserkey) throws Exception {
      return   userRankInfoDao.getMileageSumRankList(this.getAccountIDWithRandMemberTypeV2(accountID, RandMemberType, appKey, getSecretchannelRelationByUserkey));
    }

//    public   List<Map<String,Object>> getAccountIDWithRandMemberType(String accountID,int RandMemberType){
//        List<Map<String,Object>> map1 = null;
//        List<Map<String,Object>> map2 = null;
//        List<Map<String,Object>> resultList = null;
//        switch (RandMemberType)
//        {
//            case 1:
//                resultList = userRankInfoDao.getRankFriend(accountID);
//                break;
//            case 2:
//                resultList =  userRankInfoDao.getRankChannelMember(accountID);
//                break;
//            case 3:
////                Long time1 = System.currentTimeMillis()/1000;
//                map2 =  userRankInfoDao.getRankChannelMember(accountID);
////                Long time2 = System.currentTimeMillis()/1000;
////                logger.info("1："+(time2  -  time1)+"s");
//
//                map1 = userRankInfoDao.getRankFriend(accountID);
////                Long time3 = System.currentTimeMillis()/1000;
////                logger.info("2："+(time3  -  time2)+"s");
//
//
//                map1.addAll(map2);
////                Long time4 = System.currentTimeMillis()/1000;
////                logger.info("好友列表获取耗时："+(time4  -  time3)+"s");
//
//                resultList= map1;
//                break;
//        }
//        return resultList;
//    }



    public static List<Map<String,Object>> toAddLogicUnit(List<Map<String,Object>> maps,int RankType){
        if(maps==null)
            return null;
        for (Map<String,Object> m : maps){
//            if (m.get("fType").toString().equals("1") ||m.get("fType").toString().equals("2") ){
//                m.put("fromChannel",("来自群聊频道 "+m.get("fromChannel").toString()));
//            }else{
//                m.put("fromChannel","来自微密好友");
//            }

                  switch (RankType){
                      case 1:
                          m.put("itemValue",(((Integer)Integer.parseInt(m.get("itemValue").toString())))+"公里");
                          break;
                      case 2:
                          m.put("itemValue",(m.get("itemValue").toString())+"天");
                          break;
                      case 3:
                          m.put("itemValue",(Math.round(Double.valueOf(m.get("itemValue").toString())))+"密点");
                          break;
                      case 4:
                          m.put("itemValue",(m.get("itemValue").toString())+"天");
                          break;
                      case 5:
                          m.put("itemValue",(m.get("itemValue").toString())+"分");
                          break;
                      case 6:
                          m.put("itemValue",(m.get("itemValue").toString()));
                          break;
                      case 7:
                          m.put("itemValue",(m.get("itemValue").toString()));
                          break;
                      case 8:
                          m.put("itemValue",(m.get("itemValue").toString())+"次");
                          break;
                  }
        }
        return maps;

    }
    @Override
    public Map<String,Object> getRankMyselfInfo(String accountID,int RankType){
      Map<String,Object> m =  getDistanceTask(userRankInfoDao.getMyselfRankInfoThisMonth(accountID, RankType), RankType);
        if (m==null){
           return null;
        }
        int itemValue =0;
        if(RankType == 3){
            m.put("itemValue", Math.round(Double.valueOf(m.get("itemValue").toString())));
        }

        itemValue = (int) Double.parseDouble(m.get("itemValue").toString());

        m.put("star",RankingStarsTool.getRuleTextWithRankType(RankType, itemValue));
     return m;
    }

    @Override
    public Map<String,Object> getRankMyselfInfoByShellAll(String accountID){
        Map<String,Object> m = userRankInfoDao.getRankMyselfInfoByShellAll(accountID);
        m.put("nextRochelle",Integer.parseInt(m.get("nextRochelle") == null ? "0" : m.get("nextRochelle").toString()));
        return  m;
    }

    @Override
    public Map<String, Object> getRankMyselfInfoByShell(String accountID) {
        return  userRankInfoDao.getRankMyselfInfoByShell(accountID);

    }

    private Map<String,Object>  getDistanceTask(Map<String,Object> map,int RankType){
        if (map == null){
            return null;
        }
        Integer taskGrade = (int)(Float.parseFloat(map.get("itemValue").toString()));
        Integer result = 0;
            switch (RankType){
                case 1:
                    result =3500- (taskGrade);
                    break;
                case 2:
                    taskGrade =  (Integer)map.get("mileageSum");
                    result =300 - (taskGrade) ;
                    if(result > 0){
                        map.put("mileageSum","未达标");
                    }
                    break;
                case 3:
                    result =4000 - taskGrade ;
                    break;
                case 4:
                    result =25 - taskGrade ;
                    break;
                case 5:
                    result =95 - taskGrade ;
                    break;
                case 6:
                    result = 0;
                    break;
                case 7:
                    result = (int)Long.parseLong(map.get("restRule").toString())-taskGrade;
                    break;
                case 8:
                    result = 100-taskGrade;
                    break;

            }
        if(result<=0 && (RankType != 6)){
            map.put("distanceTask","已达标");
        }else{
            map.put("distanceTask",result);
        }
        return map;

    }
   @Override
   public List<Map<String,Object>> getRankListInfoByShellAll(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception {
       List<Map<String,Object>> resultList = getAccountIDWithRandMemberTypeV2(accountID, 3, appKey, getSecretchannelRelationByUserkey);
       List<Map<String,Object>> lis = userRankInfoDao.getRankListInfoByShellAll(resultList);
       return   RankingStarsTool.toPairKey(lis,resultList,"accountID");
   }
    @Override
    public List<Map<String,Object>> getRankListInfoByShell(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception {
        Long time1 = System.currentTimeMillis()/1000;
        List<Map<String,Object>> resultList = getAccountIDWithRandMemberTypeV2(accountID, 3, appKey,getSecretchannelRelationByUserkey);
//        Long time2 = System.currentTimeMillis()/1000;
//        logger.info("getAccountIDWithRandMemberType 耗时"+(time2-time1)+"s");
        List<Map<String,Object>> lis = userRankInfoDao.getRankListInfoByShell(resultList);
//        Long time3 = System.currentTimeMillis()/1000;
//        logger.info("getRankListInfoByShell 耗时"+(time3-time2)+"s");
        List<Map<String,Object>> list =  RankingStarsTool.toPairKey(lis,resultList,"accountID");
//        Long time4 = System.currentTimeMillis()/1000;
//        logger.info("toPairKey 耗时"+(time4-time3)+"s");
        return   list;
    }


    public List<Map<String,Object>> getAccountIDWithRandMemberTypeV2(String accountID,int RandMemberType,String appKey,String getSecretchannelRelationByUserkey) throws Exception {
        List<Map<String,Object>> map1 = null;
        List<Map<String,Object>> map2 = null;
        List<Map<String,Object>> resultList = null;
        switch (RandMemberType)
        {
            case 1:
                resultList = userRankInfoDao.getRankFriend(accountID);
                break;
            case 2:
                resultList =  this.getRankChannelMemberV2(accountID, appKey,getSecretchannelRelationByUserkey);
                break;
            case 3:
                map2 =  this.getRankChannelMemberV2(accountID, appKey,getSecretchannelRelationByUserkey);
                map1 = userRankInfoDao.getRankFriend(accountID);
                map1.addAll(map2);
                resultList= map1;
                break;
        }
        return resultList;
    }

    public List<Map<String,Object>> getRankChannelMemberV2(String accountID,String appKey,String getSecretchannelRelationByUserkey){

     // return  this.userRankInfoDao.getRankChannelMember(accountID);

        String appkey = ConstantsUtil.getAppKey(appKey);
        String secret = ConstantsUtil.getSecret(appKey);
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, String> param = new HashMap<String, String>(6);
            param.put("appKey", appkey);
            param.put("secret",secret);
            param.put("accountID", accountID);
            param.put("pageCount", "300");
            param.put("startPage", "1");
            //签名
            String sign = SHASignature.sign(ParameterUtil.getDaokeSignData(param));
            param.put("sign", sign);
            HttpRequester requester = new HttpRequester();
            requester.setContentType("application/x-www-form-urlencoded; charset=utf-8");
            //后端获取渠道用户列表
            HttpRespons respons = requester.sendPost(getSecretchannelRelationByUserkey, param);
            if (respons.getCode() == ConstantsUtil.HttpStatusCode.OK) {
                String content = respons.getContent();
                CommonJsonResult jsonResult = (CommonJsonResult)JsonMapper.fromJson(content,CommonJsonResult.class);
                if(jsonResult.getERRORCODE().equals("0")){
                    Map<String,Object> map1 = (Map)jsonResult.getRESULT();
                      List<Map> list1 = ( List<Map>)map1.get("userList");
                    if(list1 != null && list1.size() > 0){
                        for(int i =0;i < list1.size() ; i++){
                            Map map2 = list1.get(i);
                            HashMap<String,Object> map = new HashMap<String, Object>();
                            map.put("accountID",map2.get("accountID"));
                            map.put("fromChannel",map2.get("channelName"));
                            map.put("fType",map2.get("actionType"));
                            resultList.add(map);
                        }
                    }
                }
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return resultList;
    }

}
