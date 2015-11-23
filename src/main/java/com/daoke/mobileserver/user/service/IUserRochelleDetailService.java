package com.daoke.mobileserver.user.service;


import com.daoke.mobileserver.user.entity.UserRochelleDetail;

import java.util.List;
import java.util.Map;

/**
 * 用户谢尔值明细Service
 * Created by chenmaomao on 2015/4/1.
 */
public interface IUserRochelleDetailService {

   /**
    * 添加用户谢尔明细
    * @param accountID
    * @param imei
    * @param rochelle
    * @param ruleCode
    * @throws Exception
    */
   public void insertUserRochelleDetail(String accountID, String imei, Integer rochelle, String ruleCode) throws Exception;

   /**
    * 根据用户ID和规则码查询谢尔明细
    * @param accountID
    * @param ruleCode
    * @return
    * @throws Exception
    */
   public List<UserRochelleDetail> queryByAccountIDAndRuleCode(String accountID, String ruleCode)throws Exception;
    /**
     * 獲取用戶首頁數據詳情
     * @param
     * @return
     * @throws Exception
     */
    public Map<String,Object> getDetailUserList(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception;

    /**
     * 根据记录号查询
     * @param recordID
     * @return
     */
    public  UserRochelleDetail getUserRochelleDetailByRecordID(Integer recordID) throws Exception;
}
