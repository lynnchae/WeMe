package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.UserTalkRecord;
import com.daoke.mobileserver.user.entity.UserTalkRecordVo;

import java.util.List;

/**
 * Created by chenmaomao on 5/12 0012.
 */
public interface IUserTalkRecordService {

    /**
     * 添加用户聊天记录
     * @param userTalkRecord
     */
    public void insert(UserTalkRecord userTalkRecord);

    /**
     * 查询最新聊天记录
     * @param accountID
     * @param friendAccountID
     * @return
     */
    public UserTalkRecordVo queryTalkRecord(Integer id,String accountID, String friendAccountID);
}
