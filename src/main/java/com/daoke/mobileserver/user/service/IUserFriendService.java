package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.entity.UserFriend;

import java.util.List;

/**
 * Created by chenmaomao on 5/11 0011.
 */
public interface IUserFriendService {

    /**
     * 获取所有好友
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
     * @param accountID
     * @param friendAccountID
     * @param isVerifyOpinion
     */
    public Boolean addFriend(String accountID, String friendAccountID,Integer isVerifyOpinion);

    /**
     *同意添加好友
     * @param accountID
     * @param friendAccountID
     * @param messageID
     */
    public Boolean agreeAddFriend(String accountID, String friendAccountID,Integer messageID);
    /**
     *	不同意添加好友
     * @param messageID
     */
    public void disAgreeAddFriend(Integer messageID);

    /**
     * 删除好友
     * @param accountID
     * @param friendAccountID
     */
    public void removeUserFriend(String accountID, String friendAccountID);
}
