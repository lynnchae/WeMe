package com.daoke.mobileserver.channel.service.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.dao.IServerMenuDao;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.channel.service.IServerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Service
public class ServerMenuServiceImpl implements IServerMenuService {
    @Autowired
    private IServerMenuDao serverMenuDao;

    @Override
    public List<ServerMenu> queryServerMenu(Integer serverChannelID) {
        List<ServerMenu> serverMenuList = new ArrayList<ServerMenu>();
        List<ServerMenu> list = serverMenuDao.queryServerMenu(serverChannelID);
        for(ServerMenu serverMenu :list){
            if(serverMenu.getParentID()==0){
                serverMenu.setChildMenuList(serverMenuDao.queryServerMenuByParentID(serverMenu.getId()));
                serverMenuList.add(serverMenu);
            }
        }
        return serverMenuList;
    }
}
