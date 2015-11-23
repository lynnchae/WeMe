package com.daoke.mobileserver.shareTimesWeekly.dao;

import com.daoke.mobileserver.shareTimesWeekly.entity.ShareTimesWeekly;

/**
 * 每周分享次数Dao
 * Created by chenmaomao on 2015/4/8.
 */
public interface IShareTimesWeeklyDao {
    /**
     * 根据用户ID查询本周分享次数
     * @param accountID
     * @return
     */
    public ShareTimesWeekly queryByAccountID(String accountID);

    /**
     * 添加
     * @param shareTimesWeekly
     */
    public void insert(ShareTimesWeekly shareTimesWeekly);

    /**
     * 修改
     * @param shareTimesWeekly
     */
    public void update(ShareTimesWeekly shareTimesWeekly);
}
