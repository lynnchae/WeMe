package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.dao.DataSourceContextHolder;
import com.daoke.mobileserver.common.dao.DataSourceType;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.dao.IMessageCentreDao;
import com.daoke.mobileserver.user.entity.MessageCentre;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 2015/5/14.
 */
@Repository
public class MessageCentreDaoImpl extends BaseDao implements IMessageCentreDao {

    @Override
    public void insert(MessageCentre messageCentre) {
        this.insert("messageCentre.insert",messageCentre);
    }

    @Override
    public PageList<MessageCentre> queryMessageCentre(Map<String, Object> map,Integer toPage,Integer pageSize) {
        map.put("firstCount",(toPage-1)*pageSize);
        return this.queryForPageList("messageCentre.queryMessageCentre",map,toPage,pageSize);
    }

    @Override
    public void updateMessageIsRead(Integer messageID) {
        Map map = new HashMap(1);
        map.put("messageID",messageID);
        this.update("messageCentre.updateMessageIsRead",map);
    }

    @Override
    public void updateMessageIsAgree(Integer messageID,Character isAgree) {
        Map map = new HashMap(2);
        map.put("messageID",messageID);
        map.put("isAgree",isAgree);
        this.update("messageCentre.updateMessageIsAgree",map);
    }

    @Override
    public void removeMessageCentre(List<Integer> messageCentreIDList,String accountID) {
        Map map = new HashMap(2);
        map.put("messageCentreIDList",messageCentreIDList);
        map.put("accountID",accountID);
        this.update("messageCentre.removeMessageCentre",map);
    }

    @Override
    public int countNewMessageCentre(String accountID) {
        Map map = new HashMap(1);
        map.put("accountID",accountID);
       Integer count =  this.selectOne("messageCentre.countNewMessageCentre",accountID);
        return count;
    }
}
