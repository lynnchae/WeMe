package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IUserRankInfoDao;
import com.daoke.mobileserver.user.dao.IUserRochelleDetailDao;
import com.daoke.mobileserver.user.entity.UserRochelleDetail;
import com.daoke.mobileserver.user.service.IUserRankInfoService;
import com.daoke.mobileserver.user.service.IUserRochelleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户谢尔值明细Service
 * Created by chenmaomao on 2015/4/1.
 */
@Service
public class UserRochelleDetailServiceImpl implements IUserRochelleDetailService {

    @Autowired
    private IUserRochelleDetailDao userRochelleDetailDao;

    @Autowired
    private IUserRankInfoDao userRankInfoDao;

    @Autowired
    private UserRankInfoServiceImpl userRankInfoServiceImpl;

    @Override
    public void insertUserRochelleDetail(String accountID, String imei, Integer rochelle, String ruleCode) throws Exception {
        UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
        userRochelleDetail.setAccountID(accountID);
        userRochelleDetail.setImei(imei);
        userRochelleDetail.setIsValid(1);
        userRochelleDetail.setRochelle(rochelle);
        userRochelleDetail.setRuleCode(ruleCode);
        userRochelleDetailDao.insertUserRochelleDetail(userRochelleDetail);
    }


    @Override
    public List<UserRochelleDetail> queryByAccountIDAndRuleCode(String accountID, String ruleCode) throws Exception {
        UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
        userRochelleDetail.setAccountID(accountID);
        userRochelleDetail.setRuleCode(ruleCode);
        return userRochelleDetailDao.queryByAccountIDAndRuleCode(userRochelleDetail);
    }

    @Override
    public Map<String, Object> getDetailUserList(String accountID,String appKey,String getSecretchannelRelationByUserkey) throws Exception{
        List accountList = userRankInfoServiceImpl.getAccountIDWithRandMemberTypeV2(accountID, 3, appKey, getSecretchannelRelationByUserkey);
        final Map<String, Object> detailUserList = userRochelleDetailDao.getDetailUserList(accountID,accountList);

        return detailUserList;
    }

    @Override
    public UserRochelleDetail getUserRochelleDetailByRecordID(Integer recordID) throws Exception {
        UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
        userRochelleDetail.setRecordID(recordID);
       List<UserRochelleDetail>  list = userRochelleDetailDao.queryByAccountIDAndRuleCode(userRochelleDetail);
        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }


}
