package com.daoke.mobileserver.channel.dao.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.dao.IServerChannelMessageDao;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerChannelMessage;
import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Repository
public class ServerChannelMessageDaoImpl extends BaseDao implements IServerChannelMessageDao {

    @Override
    public PageList<ServerChannelMessage> pageQueryServerChannelMessage(Integer serverChannelID,Integer startPage, Integer pageCount) {
        Map<String,Object> map = new HashMap<String, Object>(4);
        map.put("firstCount",(startPage-1)*pageCount);
        map.put("serverChannelID",serverChannelID);
        return  this.queryForPageList("serverChannelMessage.pageQueryServerChannelMessage",map,startPage,pageCount);
    }
}
