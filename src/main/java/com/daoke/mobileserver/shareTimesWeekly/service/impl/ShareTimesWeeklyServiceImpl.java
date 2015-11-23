package com.daoke.mobileserver.shareTimesWeekly.service.impl;

import com.daoke.mobileserver.shareTimesWeekly.dao.IShareTimesWeeklyDao;
import com.daoke.mobileserver.shareTimesWeekly.entity.ShareTimesWeekly;
import com.daoke.mobileserver.shareTimesWeekly.service.IShareTimesWeeklyService;
import com.daoke.mobileserver.util.AbDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 每周分享次数Service
 * Created by chenmaomao on 2015/4/8.
 */
@Service
public class ShareTimesWeeklyServiceImpl implements IShareTimesWeeklyService {
    @Autowired
    private IShareTimesWeeklyDao IShareTimesWeeklyDao;
    @Override
    public ShareTimesWeekly queryByAccountID(String accountID) throws Exception {
        return IShareTimesWeeklyDao.queryByAccountID(accountID);
    }

    @Override
    public void insert(ShareTimesWeekly shareTimesWeekly)throws Exception{
        IShareTimesWeeklyDao.insert(shareTimesWeekly);
    }

    @Override
    public boolean judgeThisWeek(Timestamp firstShareTimeWeekly)throws Exception {
        String firstDayOfWeekStr = AbDateUtil.getFirstDayOfWeek("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date firstDayOfWeek =sdf.parse(firstDayOfWeekStr+" 00:00:00");
        if(firstDayOfWeek.getTime()<=firstShareTimeWeekly.getTime()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void update(ShareTimesWeekly shareTimesWeekly) throws Exception {
        IShareTimesWeeklyDao.update(shareTimesWeekly);
    }
}
