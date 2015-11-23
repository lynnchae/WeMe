package com.daoke.mobileserver.channel.service;

import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.channel.entity.ServerMenu;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerMenuService {

    /**
     * 查询菜单
     * @param serverChannelID
     * @return
     */
    public List<ServerMenu> queryServerMenu(Integer serverChannelID);

}
