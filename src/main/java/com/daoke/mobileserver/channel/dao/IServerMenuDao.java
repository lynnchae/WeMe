package com.daoke.mobileserver.channel.dao;

import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.common.page.PageList;

import java.util.List;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerMenuDao {
    /**
     * 查询菜单
     * @param serverChannelID
     * @return
     */
    public  List<ServerMenu> queryServerMenu(Integer serverChannelID);

    /**
     * 根据父菜单查询
     * @param parentID
     * @return
     */
    public  List<ServerMenu> queryServerMenuByParentID(Integer parentID);
}
