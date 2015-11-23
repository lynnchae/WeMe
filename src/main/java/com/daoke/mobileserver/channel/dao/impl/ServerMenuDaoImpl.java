package com.daoke.mobileserver.channel.dao.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.dao.IServerMenuDao;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Repository
public class ServerMenuDaoImpl extends BaseDao implements IServerMenuDao {

    @Override
    public List<ServerMenu> queryServerMenu(Integer serverChannelID) {
        Map<String,Object> map = new HashMap<String, Object>(1);
        map.put("serverChannelID",serverChannelID);
        return this.selectList("serverMenu.queryServerMenu",map);
    }

    @Override
    public List<ServerMenu> queryServerMenuByParentID(Integer parentID) {
        Map<String,Object> map = new HashMap<String, Object>(1);
        map.put("parentID",parentID);
        return this.selectList("serverMenu.queryServerMenuByParentID",map);
    }
}
