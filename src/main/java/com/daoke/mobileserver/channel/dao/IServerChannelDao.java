package com.daoke.mobileserver.channel.dao;

import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.common.page.PageList;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerChannelDao {
    /**
     * 分页查询服务频道
     * @param startPage
     * @param pageCount
     * @return
     */
    public PageList<ServerChannel> pageQueryServerChannel(Integer startPage, Integer pageCount);

    /**
     * 判断是否加入车队
     * @param accountID
     * @return
     */
    public Integer judgeIsJoinMcade(String accountID);
}
