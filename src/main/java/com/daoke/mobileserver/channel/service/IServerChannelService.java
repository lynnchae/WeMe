package com.daoke.mobileserver.channel.service;

import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenmaomao on 5/26 0026.
 */
public interface IServerChannelService {
    /**
     * 分页查询
     * @param startPage
     * @param pageCount
     * @return
     */
    public PageList<ServerChannel> pageQueryServerChannel(@RequestParam Integer startPage,@RequestParam Integer pageCount);

    /**
     * 判断是否加入车队
     * @param accountID
     * @return
     */
    public Integer judgeIsJoinMcade(String accountID);
}
