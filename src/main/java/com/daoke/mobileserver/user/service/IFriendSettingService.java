package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.FriendSetting;
import com.daoke.mobileserver.user.entity.UserGrade;

import java.util.List;

/**
 * 好友设置service
 * Created by chenmaomao on 2015/5/9.
 */
public interface IFriendSettingService {

    /**
     *修改好友设置
     * @param accountID
     * @param isAllowedOpinion
     * @param isVerifyOpinion
     * @param isReceiveNotifyOpinion
     */
    public void updateFriendSetting(String accountID, Integer isAllowedOpinion, Integer isVerifyOpinion, Integer isReceiveNotifyOpinion);

    /**
     * 查询个人设置
     * @param accountID
     * @return
     */
    public FriendSetting queryFriendSetting(String accountID);
}
