package com.daoke.mobileserver.shareTimesWeekly.service;

import com.daoke.mobileserver.shareTimesWeekly.entity.ShareTimesWeekly;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 每周分享次数Service
 * Created by chenmaomao on 2015/4/8.
 */
public interface IShareTimesWeeklyService {
    /**
     * 根据accountID查询分享次数
     * @param accountID
     * @return
     * @throws Exception
     */
    public ShareTimesWeekly queryByAccountID(String accountID)throws Exception;

    /**
     * 插入
     * @param shareTimesWeekly
     * @throws Exception
     */
    public void insert(ShareTimesWeekly shareTimesWeekly)throws Exception;

    /**
     * 判断是否为本周
     * @param firstShareTimeWeekly
     * @return
     * @throws Exception
     */
    public boolean judgeThisWeek(Timestamp firstShareTimeWeekly)throws Exception;

    /**
     * 更新
     * @param shareTimesWeekly
     * @throws Exception
     */
    public void update(ShareTimesWeekly shareTimesWeekly)throws Exception;
}
