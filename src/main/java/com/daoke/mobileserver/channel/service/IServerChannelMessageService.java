package com.daoke.mobileserver.channel.service;

import com.daoke.mobileserver.channel.entity.ServerChannelMessage;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.common.page.PageList;

import java.util.List;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerChannelMessageService {

    /**
     * 查询菜单
     * @param startPage
     * @param pageCount
     * @return
     */
    public PageList<ServerChannelMessage> pageQueryServerChannelMessage(Integer serverChannelID,Integer startPage,  Integer pageCount);

}
