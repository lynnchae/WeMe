package com.daoke.mobileserver.shareTimesWeekly.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.shareTimesWeekly.dao.IShareTimesWeeklyDao;
import com.daoke.mobileserver.shareTimesWeekly.entity.ShareTimesWeekly;
import org.springframework.stereotype.Repository;

/**
 * 每周分享次数Dao
 * Created by chenmaomao on 2015/4/8.
 */
@Repository
public class ShareTimesWeeklyDaoImpl extends BaseDao implements IShareTimesWeeklyDao {
    @Override
    public ShareTimesWeekly queryByAccountID(String accountID) {
        ShareTimesWeekly shareTimesWeekly = this.selectOne("shareTimesWeekly.queryByAccountID",accountID);
        return shareTimesWeekly;
    }

    @Override
    public void insert(ShareTimesWeekly shareTimesWeekly) {
        this.insert("shareTimesWeekly.insert",shareTimesWeekly);
    }

    @Override
    public void update(ShareTimesWeekly shareTimesWeekly) {
        this.update("shareTimesWeekly.update",shareTimesWeekly);
    }
}
