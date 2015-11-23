package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IFriendSettingDao;
import com.daoke.mobileserver.user.dao.IUserFriendDao;
import com.daoke.mobileserver.user.entity.FriendSetting;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserFriend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Repository
public class FriendSettingDaoImpl extends BaseDao implements IFriendSettingDao {


    @Override
    public int updateFriendSetting(FriendSetting friendSetting) {
        return this.update("friendSetting.updateFriendSetting",friendSetting);
    }

    @Override
    public FriendSetting queryByAccountID(Map<String, Object> map) {
        return this.selectOne("friendSetting.queryByAccountID",map);
    }

    @Override
    public void insert(FriendSetting friendSetting) {
        this.insert("friendSetting.insert",friendSetting);
    }

    @Override
    public List<UserDetailVo> queryUserSettingList(Map<String,Object> map) {
        if(map.get("list")==null||((List)map.get("list")).size()==0){
            return null;
        }
        return this.selectList("friendSetting.queryUserSettingList",map);
    }
}
