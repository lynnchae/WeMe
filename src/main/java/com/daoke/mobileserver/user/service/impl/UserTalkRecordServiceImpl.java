package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IUserTalkRecordDao;
import com.daoke.mobileserver.user.entity.UserTalkRecord;
import com.daoke.mobileserver.user.entity.UserTalkRecordVo;
import com.daoke.mobileserver.user.service.IUserTalkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 5/12 0012.
 */
@Service
public class UserTalkRecordServiceImpl implements IUserTalkRecordService {
    @Autowired
    private IUserTalkRecordDao userTalkRecordDao;
    @Override
    public void insert(UserTalkRecord userTalkRecord) {
        userTalkRecordDao.insert(userTalkRecord);
    }

    @Override
    public UserTalkRecordVo queryTalkRecord(Integer id,String accountID, String friendAccountID) {
        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("accountID",accountID);
        map.put("friendAccountID",friendAccountID);
        map.put("id",id);
//        Timestamp oneMonthAgo = new Timestamp(System.currentTimeMillis()-30*6400000);
//        map.put("oneMonthAgo",oneMonthAgo);
        return userTalkRecordDao.queryTalkRecord(map);
    }
}
