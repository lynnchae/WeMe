package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IUserRankDetailDao;
import com.daoke.mobileserver.user.entity.UserRankDetail;
import com.daoke.mobileserver.user.service.IUserRankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Service
public class UserRankDetailServiceImpl implements IUserRankDetailService {
    @Autowired
    private IUserRankDetailDao userRankDetailDao;
    @Override
    public UserRankDetail getCurrentRank(String accountID) {
        return userRankDetailDao.getCurrentRank(accountID);
    }
}
