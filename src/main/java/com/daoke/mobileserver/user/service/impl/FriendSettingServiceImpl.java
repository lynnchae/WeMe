package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.common.service.ServiceException;
import com.daoke.mobileserver.user.dao.IFriendSettingDao;
import com.daoke.mobileserver.user.entity.FriendSetting;
import com.daoke.mobileserver.user.service.IFriendSettingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 好友设置Service
 * Created by chenmaomao on 2015/5/9.
 */
@Service
public class FriendSettingServiceImpl implements IFriendSettingService {

    Logger logger =  Logger.getLogger(this.getClass());
    @Autowired
    private IFriendSettingDao friendSettingDao;
    @Override
    public void updateFriendSetting(String accountID, Integer isAllowedOpinion, Integer isVerifyOpinion, Integer isReceiveNotifyOpinion) {
        Map<String,Object> map = new HashMap<String, Object>(1);
        map.put("accountID",accountID);
       FriendSetting friendSetting =  friendSettingDao.queryByAccountID(map);
        if(friendSetting!=null){
            friendSetting.setIsAllowedOpinion(isAllowedOpinion==null?friendSetting.getIsAllowedOpinion():isAllowedOpinion);
            friendSetting.setIsVerifyOpinion(isVerifyOpinion==null?friendSetting.getIsVerifyOpinion():isVerifyOpinion);
            friendSetting.setIsReceiveNotifyOpinion(isReceiveNotifyOpinion==null?friendSetting.getIsReceiveNotifyOpinion():isReceiveNotifyOpinion);
            int count = friendSettingDao.updateFriendSetting(friendSetting);
            if(count!=1){
                logger.error("修改个人设置失败");
                throw new ServiceException("修改个人设置失败");
            }
        }else{
            friendSetting = new FriendSetting();
            friendSetting.setAccountID(accountID);
            friendSetting.setIsAllowedOpinion(isAllowedOpinion);
            friendSetting.setIsVerifyOpinion(isVerifyOpinion);
            friendSetting.setIsReceiveNotifyOpinion(isReceiveNotifyOpinion);
            friendSetting.setIsValid(1);
            friendSettingDao.insert(friendSetting);
        }
    }

    @Override
    public FriendSetting queryFriendSetting(String accountID) {
        Map<String,Object> map = new HashMap<String, Object>(1);
        map.put("accountID",accountID);
        FriendSetting friendSetting = friendSettingDao.queryByAccountID(map);
        if(friendSetting==null){
            friendSetting = new FriendSetting();
            friendSetting.setIsVerifyOpinion(0);
            friendSetting.setIsValid(1);
            friendSetting.setIsAllowedOpinion(1);
            friendSetting.setIsReceiveNotifyOpinion(1);
            friendSetting.setAccountID(accountID);
            friendSetting.setCreateTime(new Timestamp(System.currentTimeMillis()));
            friendSettingDao.insert(friendSetting);
        }
        return friendSetting;
    }
}
