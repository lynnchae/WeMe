package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IUserTalkRecordDao;
import com.daoke.mobileserver.user.entity.UserTalkRecord;
import com.daoke.mobileserver.user.entity.UserTalkRecordVo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by chenmaomao on 5/12 0012.
 */
@Repository
public class UserTalkRecordDaoImpl extends BaseDao implements IUserTalkRecordDao {

    @Override
    public void insert(UserTalkRecord userTalkRecord) {
        this.insert("userTalkRecord.insert",userTalkRecord);
    }

    @Override
    public UserTalkRecordVo queryTalkRecord(Map<String, Object> map) {
        return this.selectOne("userTalkRecord.queryTalkRecord", map);
    }
}
