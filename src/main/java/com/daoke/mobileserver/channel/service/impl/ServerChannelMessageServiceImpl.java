package com.daoke.mobileserver.channel.service.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.dao.IServerChannelMessageDao;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerChannelMessage;
import com.daoke.mobileserver.channel.service.IServerChannelMessageService;
import com.daoke.mobileserver.channel.service.IServerChannelService;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Service
public class ServerChannelMessageServiceImpl implements IServerChannelMessageService {
    @Autowired
    private IServerChannelMessageDao serverChannelMessageDao;
    @Override
    public PageList<ServerChannelMessage> pageQueryServerChannelMessage(Integer serverChannelID,Integer startPage,  Integer pageCount) {
        return serverChannelMessageDao.pageQueryServerChannelMessage(serverChannelID,startPage,pageCount);
    }
}
