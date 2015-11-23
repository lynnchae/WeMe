package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.entity.MessageCentre;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 2015/5/9.
 */
public interface IMessageCentreDao {
    /**
     * 添加
     * @param messageCentre
     */
    public void insert(MessageCentre messageCentre);
    /**
     * 查询消息中心
     * @param map
     */
    public PageList<MessageCentre> queryMessageCentre(Map<String, Object> map,Integer toPage,Integer pageSize);

    /**
     * 修改为已读信息
     * @param messageID
     */
    public void updateMessageIsRead(Integer messageID);
    /**
     * 修改同意拒绝状态
     * @param messageID
     */
    public void updateMessageIsAgree(Integer messageID,Character isAgree);

    /**
     * 清空消息中心
     * @param messageCentreIDList
     */
    public void removeMessageCentre(List<Integer> messageCentreIDList,String accountID);

    /**
     * 查询新消息数
     * @param accountID
     * @return
     */
    public int countNewMessageCentre(String accountID);
}
