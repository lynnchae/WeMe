package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.dao.IMessageCentreDao;
import com.daoke.mobileserver.user.dao.IUserFriendDao;
import com.daoke.mobileserver.user.entity.UserFriend;
import com.daoke.mobileserver.user.service.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友设置Service
 * Created by chenmaomao on 2015/5/9.
 */
@Service
public class UserFriendServiceImpl implements IUserFriendService {

    @Autowired
    private IUserFriendDao userFriendDao;
    @Autowired
    private IMessageCentreDao messageCentreDao;
    @Override
    public List<UserFriend> getAllFriends(String accountID) {
        return userFriendDao.getAllFriends(accountID);
    }

    @Override
    public PageList<UserFriend> pageQueryFriends(String accountID, Integer startPage, Integer pageCount) {
        return userFriendDao.pageQueryFriends(accountID,startPage,pageCount);
    }

    @Override
    public Boolean addFriend(String accountID, String friendAccountID, Integer isVerifyOpinion) {
        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("accountID",accountID);
        map.put("friendAccountID",friendAccountID);
        if(0==isVerifyOpinion){
            map.put("isAgree",1);
        }else{
            map.put("isAgree",0);
        }
        //查询是否为好友
        int count = userFriendDao.queryIsFriend(map);
        if(count<1){
            //添加
            userFriendDao.insert(map);
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean agreeAddFriend(String accountID, String friendAccountID,Integer messageID) {
        messageCentreDao.updateMessageIsAgree(messageID,'1');
        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("accountID",accountID);
        map.put("friendAccountID",friendAccountID);
        map.put("isAgree",1);
        //查询是否为好友
        int count = userFriendDao.queryIsFriend(map);
        if(count<1){
            //如果不是好友,同意
            userFriendDao.updateIsAgree(map);
            return true;
        }else {
            //如果是好友,返回已存在
            return  false;
        }

    }
    @Override
    public void disAgreeAddFriend(Integer messageID) {
        messageCentreDao.updateMessageIsAgree(messageID,'0');
    }

    @Override
    public void removeUserFriend(String accountID, String friendAccountID) {
        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("accountID",accountID);
        map.put("friendAccountID",friendAccountID);
        userFriendDao.removeUserFriend(map);
    }

}
