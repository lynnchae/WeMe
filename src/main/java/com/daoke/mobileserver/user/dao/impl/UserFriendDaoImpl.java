package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.user.dao.IUserFriendDao;
import com.daoke.mobileserver.user.entity.UserFriend;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Repository
public class UserFriendDaoImpl extends BaseDao implements IUserFriendDao {

    @Override
    public List<UserFriend> getAllFriends(String accountID) {
        return this.selectList("userFriend.getAllFriends",accountID);
    }

    @Override
    public PageList<UserFriend> pageQueryFriends(String accountID,Integer startPage,Integer pageCount) {
        Map<String,Object> map = new HashMap<String, Object>(4);
        map.put("accountID",accountID);
        map.put("firstCount",(startPage-1)*pageCount);
        return this.queryForPageList("userFriend.pageQueryFriends",map,startPage,pageCount);
    }

    @Override
    public void insert(Map<String, Object> map) {
        this.insert("userFriend.insert",map);
    }

    @Override
    public void updateIsAgree(Map<String, Object> map) {
        this.update("userFriend.updateIsAgree",map);
    }

    @Override
    public int queryIsFriend(Map<String, Object> map) {
        Integer count = this.selectOne("userFriend.queryIsFriend",map);
        return count;
    }

    @Override
    public void removeUserFriend(Map<String, Object> map) {
        this.update("userFriend.removeUserFriend",map);
    }

}
