package com.daoke.mobileserver.channel.service.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.service.IServerChannelService;
import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Service
public class ServerChannelServiceImpl  implements IServerChannelService {
    @Autowired
    private IServerChannelDao serverChannelDao;
    @Override
    public PageList<ServerChannel> pageQueryServerChannel(@RequestParam Integer startPage, @RequestParam Integer pageCount) {
        return serverChannelDao.pageQueryServerChannel(startPage,pageCount);
    }

    @Override
    public Integer judgeIsJoinMcade(String accountID) {
        return serverChannelDao.judgeIsJoinMcade(accountID);
    }
}
