package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.user.entity.UserTalkRecord;
import com.daoke.mobileserver.user.entity.UserTalkRecordVo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 5/12 0012.
 */
public interface IUserTalkRecordDao {

    /**
     * 添加记录
     * @param userTalkRecord
     */
    public void insert(UserTalkRecord userTalkRecord);

    /**
     * 查询聊天记录
     * @param map
     * @return
     */
    public UserTalkRecordVo queryTalkRecord(Map<String, Object> map);
}
