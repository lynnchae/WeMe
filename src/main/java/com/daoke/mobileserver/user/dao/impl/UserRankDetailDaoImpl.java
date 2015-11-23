package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IUserRankDetailDao;
import com.daoke.mobileserver.user.entity.UserRankDetail;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Repository
public class UserRankDetailDaoImpl extends BaseDao implements IUserRankDetailDao {
    @Override
    public UserRankDetail getCurrentRank(String accountID) {
        return this.selectOne("getRankingByAccountID",accountID);
    }
}
