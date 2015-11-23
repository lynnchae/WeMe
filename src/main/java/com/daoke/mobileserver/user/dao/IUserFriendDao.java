package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.entity.UserFriend;

import java.util.List;
import java.util.Map;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public interface IUserFriendDao {
    /**
     * 获取已添加的好友
     * @param accountID
     * @return
     */
    public List<UserFriend> getAllFriends(String accountID);
    /**
     * 分页获取已添加的好友
     * @param accountID
     * @return
     */
    public PageList<UserFriend> pageQueryFriends(String accountID,Integer startPage,Integer pageCount);

    /**
     * 添加好友
     * @param map
     */
    public void insert(Map<String, Object> map);

    /**
     * 修改是否同意
     * @param map
     */
    public void updateIsAgree(Map<String, Object> map);

    /**
     * 查询是否为好友
     * @param map
     * @return
     */
    public int queryIsFriend(Map<String, Object> map);

    /**
     * 删除好友
     */
    public void removeUserFriend(Map<String, Object> map);
}
