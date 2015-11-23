package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangzp on 2015/5/23.
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {
    @Override
    public int insertIdentifyingCode(Map<String, Object> map) {
        return this.insert("insertIdentifyingCode",map);
    }
    @Override
    public int findValidCode(Map<String, Object> map){
        return (Integer)this.selectOne("findValidCode",map);
    }
}
