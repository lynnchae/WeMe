package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.entity.FriendSetting;
import com.daoke.mobileserver.user.entity.UserDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 2015/5/9.
 */
public interface IFriendSettingDao{
    /**
     * 修改好友设置
     * @param friendSetting
     * @return
     */
   public int updateFriendSetting(FriendSetting friendSetting);

    /**
     * 根据用户ID查询用户设置
     * @param map
     * @return
     */
    public FriendSetting queryByAccountID(Map<String, Object> map);

    /**
     * 添加个人设置
     * @param friendSetting
     */
    public void insert(FriendSetting friendSetting);

    /**
     * 查询列表
     * @param list
     * @return
     */
    public List<UserDetailVo> queryUserSettingList(Map<String,Object> list);
}
