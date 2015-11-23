package com.daoke.mobileserver.channel.dao;

import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerChannelMessage;
import com.daoke.mobileserver.common.page.PageList;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerChannelMessageDao {
    /**
     * 分页查询服务频道消息
     * @param serverChannelID
     * @param startPage
     * @param pageCount
     * @return
     */
    public PageList<ServerChannelMessage> pageQueryServerChannelMessage(Integer serverChannelID,Integer startPage, Integer pageCount);
}
