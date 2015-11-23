package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.UserRankDetail;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public interface IUserRankDetailService {
    /**
     * 获取当前用户排名
     */
    UserRankDetail getCurrentRank(String accountID);
}
