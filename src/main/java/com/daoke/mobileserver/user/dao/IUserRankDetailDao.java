package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.user.entity.UserRankDetail;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public interface IUserRankDetailDao {
    /**
     * 获取用户的当前排名
     * @param accountID
     * @return
     */
    UserRankDetail getCurrentRank(String accountID);
}
