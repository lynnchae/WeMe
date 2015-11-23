package com.daoke.mobileserver.channel.dao.impl;

import com.daoke.mobileserver.channel.dao.IServerChannelDao;
import com.daoke.mobileserver.channel.entity.ServerChannel;
import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.dao.DataSourceContextHolder;
import com.daoke.mobileserver.common.dao.DataSourceType;
import com.daoke.mobileserver.common.page.PageList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenmaomao on 5/26 0026.
 */
@Repository
public class ServerChannelDaoImpl extends BaseDao implements IServerChannelDao {

    @Override
    public PageList<ServerChannel> pageQueryServerChannel(Integer startPage, Integer pageCount) {
        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("firstCount",(startPage-1)*pageCount);
        return  this.queryForPageList("serverChannel.pageQueryServerChannel",map,startPage,pageCount);
    }

    @Override
    public Integer judgeIsJoinMcade(String accountID) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.app_MCade);
        Map<String,Object> map = new HashMap<String, Object>(1);
        map.put("accountID",accountID);
        Integer flag = (Integer)this.selectOne("serverChannel.countJoinMcadeMember",map)>=1?1:0;
        DataSourceContextHolder.clearDataSourceType();
        return flag;
    }
}
