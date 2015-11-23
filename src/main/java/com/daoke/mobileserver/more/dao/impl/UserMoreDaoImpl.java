package com.daoke.mobileserver.more.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.more.dao.UserMoreDao;
import com.daoke.mobileserver.more.entity.UserMore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cailingfeng on 2015/5/29.
 */
@Repository
public class UserMoreDaoImpl extends BaseDao implements UserMoreDao {

    @Override
    public List<UserMore> getMoreList() {
        return this.selectList("userMore.queryMoreList");
    }

    @Override
    public Map getAd() {
        return this.selectOne("userMore.getAd");
    }
}
